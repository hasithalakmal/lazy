package com.smile.lazy.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class TemplateUtil {

    public static String manipulateTemplate(String jsonFilePath, Map<String, Object> templateData) {

        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassForTemplateLoading(TemplateUtil.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = null;
        try {
            template = cfg.getTemplate(jsonFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String responseString = null;
        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            responseString = out.getBuffer().toString();
            System.out.println(responseString);
            out.flush();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }
}
