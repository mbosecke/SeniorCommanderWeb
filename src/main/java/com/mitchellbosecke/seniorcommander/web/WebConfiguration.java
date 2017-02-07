package com.mitchellbosecke.seniorcommander.web;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.spring4.extension.SpringExtension;
import com.mitchellbosecke.seniorcommander.web.ui.PebbleExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class WebConfiguration {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    @Bean
    public SpringExtension springExtension() {
        return new SpringExtension();
    }

    @Bean
    public PebbleEngine pebbleEngine() {
        ClasspathLoader loader = new ClasspathLoader();
        loader.setCharset("UTF-8");

        PebbleEngine.Builder builder = new PebbleEngine.Builder();
        builder.loader(loader);
        builder.extension(springExtension(), new PebbleExtension());
        return builder.build();
    }
}
