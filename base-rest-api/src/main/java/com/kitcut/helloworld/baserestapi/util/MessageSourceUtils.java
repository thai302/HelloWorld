package com.kitcut.helloworld.baserestapi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceUtils {

    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        MessageSourceUtils.messageSource = messageSource;
    }

    public static String getMessage(String key, Object... args) {
        return String.format(messageSource.getMessage(key, null, LocaleContextHolder.getLocale()), args);
    }
}
