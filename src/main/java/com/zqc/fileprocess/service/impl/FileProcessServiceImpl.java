package com.zqc.fileprocess.service.impl;

import com.zqc.fileprocess.config.MinioClientConfig;
import com.zqc.fileprocess.service.FileProcessService;
import com.zqc.fileprocess.utils.FileNameUtils;
import com.zqc.fileprocess.utils.MinioClientUtils;
import com.zqc.fileprocess.utils.TimeUtils;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

/**
 * @Author: 张全成
 * @Date: 2020/5/17 13:24
 * @Version 1.0
 */
@Service
public class FileProcessServiceImpl implements FileProcessService {

    @Autowired
    MinioClientConfig minioClientConfig;

    @Override
    public String fileUpload(MultipartFile file) {
        // 文件名
        String fileName = "";
        // 文件格式
        String prefix = "";
        // 服务器上文件的相对路径
        String filePath = "";
        // 上传成功标识
        boolean flag = false;
        // 获取文件前缀
        int index= file.getOriginalFilename().indexOf(".")+1;
        prefix = file.getOriginalFilename().substring(index);
        fileName = FileNameUtils.getNextId(prefix);
        // 文件相对路径我这边设置成了/桶名/年/月/日/唯一文件名
        LocalDate localDate = LocalDate.now();
        filePath =  new StringBuilder()
                .append("/").append(TimeUtils.getCode(localDate,"00"))
                .append("/").append(TimeUtils.getCode(localDate,"01"))
                .append("/").append(TimeUtils.getCode(localDate,"02"))
                .append("/").append(fileName).append(".").append(prefix).toString();
        flag = MinioClientUtils.fileUpload(file,filePath,prefix,minioClientConfig);
        if (flag){
            return filePath;
        } else {
            return null;
        }
    }

    @Override
    public boolean fileDown(String fileName, String filePatch) {
        boolean flag = false;
        // 首先我们要知道我们要下载的文件在文件服务器上所在桶的位置(路径)
        // 我们是哪文件的后缀作为桶名，因此获取文件后缀
        int index= fileName.indexOf(".")+1;
        String bucketName = fileName.substring(index);
        flag = MinioClientUtils.fileDownload(minioClientConfig,bucketName,filePatch,fileName);
        return flag;
    }
}
