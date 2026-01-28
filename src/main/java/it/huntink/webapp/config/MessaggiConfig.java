package it.huntink.webapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class MessaggiConfig {

    @Bean(name = "validator")
    LocalValidatorFactoryBean validator(){

        LocalValidatorFactoryBean b = new LocalValidatorFactoryBean();
        b.setValidationMessageSource(messageSource());

        return b;
    }

    @Bean
    ResourceBundleMessageSource messageSource(){

        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasenames("message");
        resourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
        resourceBundleMessageSource.setDefaultLocale(Locale.forLanguageTag("it"));
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setAlwaysUseMessageFormat(true);

        return resourceBundleMessageSource;
    }

}
