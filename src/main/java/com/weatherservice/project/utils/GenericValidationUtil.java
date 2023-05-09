package com.weatherservice.project.utils;

import java.util.List;

public class GenericValidationUtil {
    private GenericValidationUtil() {
    }

    public static boolean isFieldEmpty(String field) {
        return field != null && !field.isEmpty();
    }

    public static boolean isListEmpty(List<Object> objects) {
        return !objects.isEmpty();
    }

    public static boolean isObjectEmpty(Object obj) {
        return obj != null;
    }

}
