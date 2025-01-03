package com.iambishal.spring_boot_rest_api_boilerplate.shared.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * This service class provides a convenient way to retrieve localized messages from the
 * Spring MessageSource. It handles cases where a message is not found and provides
 * default behavior.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MessageSourceService {

    /**
     * Injects the MessageSource bean provided by Spring.
     */
    private final MessageSource messageSource;

    /**
     * Retrieves a localized message from the MessageSource.
     *
     * @param code   the message code
     * @param params optional parameters for the message
     * @param locale the desired locale
     * @return the localized message, or the message code itself if not found
     */
    public String getMessage(final String code, final Object[] params, final Locale locale) {
        try {
            return messageSource.getMessage(code, params, locale);
        } catch (NoSuchMessageException exception) {
            log.warn("Translation message not found ({}): {}", locale, code);
            return code;
        }
    }

    /**
     * Retrieves a localized message from the MessageSource using the current locale.
     *
     * @param code   the message code
     * @param params optional parameters for the message
     * @return the localized message, or the message code itself if not found
     */
    public String getMessage(final String code, final Object[] params) {
        return getMessage(code, params, LocaleContextHolder.getLocale());
    }

    /**
     * Retrieves a localized message from the MessageSource using the current locale
     * and no parameters.
     *
     * @param code the message code
     * @return the localized message, or the message code itself if not found
     */
    public String getMessage(final String code, final Locale locale) {
        return getMessage(code, new Object[0], locale);
    }

    /**
     * Retrieves a localized message from the MessageSource using the current locale
     * and no parameters.
     *
     * @param code the message code
     * @return the localized message, or the message code itself if not found
     */
    public String getMessage(final String code) {
        return getMessage(code, new Object[0], LocaleContextHolder.getLocale());
    }
}
