package com.practice.homework.commons.utils;


import com.google.common.collect.Iterables;
import com.practice.homework.commons.dto.custom.PageLimit;
import com.practice.homework.commons.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ValidationUtils {

    private static boolean isNullOrEmpty(Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof String) {
            return StringUtils.isEmpty((String) value);
        }
        if (value instanceof Collection) {
            return ((Collection) value).isEmpty();
        }
        return false;
    }

    public static boolean isNotEmpty(Object value) {
        return !isNullOrEmpty(value);
    }

    public static boolean isNotEmpty(List<?> value) {
        return value != null && !value.isEmpty();
    }

    public static boolean isNotEmpty(Set<?> value) {
        return value != null && !value.isEmpty();
    }

    public static boolean isNotEmpty(Iterable<?> value) {
        return !Iterables.isEmpty(value);
    }

    public static boolean isNotEmptyAll(Object... values) {
        for (Object value : values) {
            if (isNullOrEmpty(value)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(Object value) {
        return isNullOrEmpty(value);
    }

    public static boolean isBetween(BigDecimal value, BigDecimal value1, BigDecimal value2) {
        return (value.compareTo(value1) >= 0 && value.compareTo(value2) <= 0);
    }

    public static boolean isNotBetween(BigDecimal value, BigDecimal value1, BigDecimal value2) {
        return !isBetween(value, value1, value2);
    }

    public static boolean isCorrectEmailFormat(String email) {
        // RFC2822 Email Validation
        String regex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        return email.matches(regex);
    }

    public static boolean isCorrectPasswordFormat(String password) {
        // Including Uppercase, Lowercase and numeric.
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
        return password.matches(regex);
    }

    public static boolean isOnlyThaiAlphabet(String text) {
        // Only Thai alphabet is allowed.
        String regex = "[ก-๏\\s]+$";
        return text.matches(regex);
    }

    public static boolean isOnlyEnglishAlphabet(String text) {
        // Only English alphabet is allowed.
        String regex = "[A-Za-z\\s]+$";
        return text.matches(regex);
    }

    public static void requiredLimit(PageLimit pageLimit) throws ValidationException {
        requiredNotWhen(isNotEmpty(pageLimit), MessageCode.E00001, "limit");

        requiredNotWhen(isNotEmpty(pageLimit.getPageSize()), MessageCode.E00001, "limit.pageSize");
        requiredNotWhen(pageLimit.getPageSize() > 0, MessageCode.E00003, "limit.pageSize");

        requiredNotWhen(isNotEmpty(pageLimit.getPageNumber()), MessageCode.E00001, "limit.pageNumber");
        requiredNotWhen(pageLimit.getPageNumber() > 0, MessageCode.E00003, "limit.pageNumber");
    }

    public static void requiredNotWhen(boolean result, MessageCode errorCode, String... errorMessage) throws ValidationException {
        if (!result) {
            String finalMessage = MessageUtils.buildMessage(errorCode, errorMessage);
            throw new ValidationException(finalMessage);
        }
    }

    public static void requiredNotWhen(boolean result, String errorCode, String... errorMessage) throws ValidationException {
        if (!result) {
            String finalMessage = MessageUtils.buildMessage(errorCode, errorMessage);
            throw new ValidationException(finalMessage);
        }
    }

    public static void validate(List<String> allErrors, boolean result, MessageCode errorCode, String... errorMessage) {
        if (!result) {
            String finalMessage = MessageUtils.buildMessage(errorCode, errorMessage);
            allErrors.add(finalMessage);
        }
    }

    /*
    public static void requiredNotWhen(boolean result, MessageCode errorCode, String... errorMessage) throws ValidationException {
        requiredNotWhen(result, errorCode.getCode(), errorMessage);
    }

    public static void requiredNotWhen(boolean result, String errorCode, String... errorMessage) throws ValidationException {
        if (!result) {
            if (errorMessage == null) {
                throw new ValidationException(errorCode + " : " + MessageUtils.getMessage(errorCode));
            } else {
                throw new ValidationException(errorCode + " : " + MessageUtils.getMessage(errorCode, errorMessage));
            }
        }
    }
    */
}
