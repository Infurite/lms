package com.temchik.lms.dto.dictionary;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public abstract class DictionaryNamedDTO {

    private Long id;

    @NotEmpty
    private String name;
}
