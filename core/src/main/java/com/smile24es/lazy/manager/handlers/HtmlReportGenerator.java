package com.smile24es.lazy.manager.handlers;

import com.smile24es.lazy.common.ErrorCodes;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.reports.LazyReport;
import com.smile24es.lazy.utils.TemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Repository
public class HtmlReportGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlReportGenerator.class);

    public void setRequestBodyFromJsonTemplate(String templateFilePath, LazyReport lazyReport, String htmlFilePath) throws LazyCoreException {
        if (StringUtils.isBlank(templateFilePath)) {
            String error = "Template file path should not be blank";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_FILE_PATH, error);
        }

        if (StringUtils.isBlank(htmlFilePath)) {
            String error = "HTML file path should not be blank";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_FILE_PATH, error);
        }

        if (lazyReport == null) {
            String error = "Lazy Report path should not be blank";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.HTML_REPORT_GENERATION_ERROR, error);
        }

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("report", lazyReport);

        if (CollectionUtils.isEmpty(templateData)) {
            String error = "Template file path should not be blank";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_TEMPLATE_DATA_OBJECT, error);
        }
        String htmlContent = TemplateUtil.manipulateTemplate(templateFilePath, templateData);

        try {
            try (FileWriter myWriter = new FileWriter(htmlFilePath)) {
                myWriter.write(htmlContent);
            }
            LOGGER.info("Successfully wrote the HTML report");
        } catch (IOException e) {
            String error = "Error occurred while writing HTML file";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_TEMPLATE_DATA_OBJECT, error);
        }
    }
}
