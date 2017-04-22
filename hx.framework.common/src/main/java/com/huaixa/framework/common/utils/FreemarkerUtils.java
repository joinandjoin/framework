package com.huaixa.framework.common.utils;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class FreemarkerUtils {
    private static final Log logger = LogFactory.getLog(FreemarkerUtils.class);
    
    private static FreemarkerUtils instance;
    
    @Autowired
    private Configuration configuration;
    
    @PostConstruct
    public void init() {
        instance = this;
        instance.configuration = this.configuration;
    }
    
    public static String getContent(String templateName, Map<String, Object> model) {  
        try {  
            Template t = instance.configuration.getTemplate(templateName);  
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, model);  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            logger.error(CommonUtils.printException(ex));  
            try {  
                Template t = instance.configuration.getTemplate(templateName);  
                return FreeMarkerTemplateUtils.processTemplateIntoString(t, model);  
            } catch (Exception e) {  
                e.printStackTrace();  
                logger.error(CommonUtils.printException(e));  
            }  
        }  
  
        return null;  
    }  
}
