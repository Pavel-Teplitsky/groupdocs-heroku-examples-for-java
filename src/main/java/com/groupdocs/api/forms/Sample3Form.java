package com.groupdocs.api.forms;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Sample3Form {
    protected CommonsMultipartFile fileData;

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
}
