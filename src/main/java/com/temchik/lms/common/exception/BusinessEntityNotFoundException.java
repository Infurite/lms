package com.temchik.lms.common.exception;

public class BusinessEntityNotFoundException extends NamedException {

    public BusinessEntityNotFoundException(Class className, String fieldName) {
        super("Entity ".concat(className.getSimpleName()).concat(" by '").concat(fieldName).concat("' not found"), BusinessEntityNotFoundException.class);
    }
}
