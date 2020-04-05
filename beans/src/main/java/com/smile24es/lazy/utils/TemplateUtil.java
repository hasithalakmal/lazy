package com.smile24es.lazy.utils;

import com.smile24es.lazy.common.ErrorCodes;
import com.smile24es.lazy.exception.LazyCoreException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class TemplateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateUtil.class);

    private TemplateUtil() {
        //This is a private constructor
    }

    public static String manipulateTemplate(String templateFilePath, Map<String, Object> templateData) throws LazyCoreException {
        if (StringUtils.isBlank(templateFilePath)) {
            String error = "File path should not be blank";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_FILE_PATH, error);
        }
        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassForTemplateLoading(TemplateUtil.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = null;
        try {
            template = cfg.getTemplate(templateFilePath);
        } catch (IOException e) {
            String error = "Invalid template found";
            LOGGER.error(error, e);
            throw new LazyCoreException(ErrorCodes.INVALID_TEMPLATE, error);
        }

        String responseString = null;
        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            responseString = out.getBuffer().toString();
            out.flush();
        } catch (TemplateException e) {
            String error = "Template processing error";
            LOGGER.error(error, e);
            throw new LazyCoreException(ErrorCodes.TEMPLATE_PROCESSING_ERROR, error);
        } catch (IOException e) {
            String error = "Cannot read template file";
            LOGGER.error(error, e);
            throw new LazyCoreException(ErrorCodes.FILE_READING_ERROR, error);
        }

        return responseString;
    }
}
