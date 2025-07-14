package com.lunsir.entity;

import lombok.Data;

/**
 * @author lunSir
 * @create 2024-09-12 12:49
 */
@Data
public class FileInfo {
    private String fileName;
    private Boolean directoryFlag;
    private String etag;
}
