package com.zqc.fileprocess.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 张全成
 * @Date: 2020/5/17 13:23
 * @Version 1.0
 */
public interface FileProcessService {

    public String fileUpload(MultipartFile file);
}
