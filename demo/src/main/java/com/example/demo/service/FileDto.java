package com.example.demo.service;

import lombok.Getter;

@Getter
public class FileDto {
    private String fileName;
    private String contentType;

    public FileDto() {}

    public FileDto(String fileName, String contentType) {
        this.fileName = fileName;
        this.contentType = contentType;
        System.out.println(contentType);
    }
}
