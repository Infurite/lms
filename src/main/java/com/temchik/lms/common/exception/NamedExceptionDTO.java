package com.temchik.lms.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NamedExceptionDTO {

    private String code;

    private String message;
}
