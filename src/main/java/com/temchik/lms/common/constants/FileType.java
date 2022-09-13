package com.temchik.lms.common.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum FileType {

    IMAGE(new HashSet<>(Arrays.asList("jpeg", "jpg", "png", "svg"))),
    DOCUMENT(new HashSet<>(Arrays.asList("docs", "doc", "pdf")));

    private Set<String> extensions;

    FileType(Set<String> extensions) {
        this.extensions = extensions;
    }

    public Set<String> getExtensions() {
        return extensions;
    }
}
