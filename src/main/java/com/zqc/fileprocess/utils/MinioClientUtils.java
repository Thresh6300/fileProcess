package com.zqc.fileprocess.utils;

import com.zqc.fileprocess.config.MinioClientConfig;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: 张全成
 * @Date: 2020/5/17 13:28
 * @Version 1.0
 */
public class MinioClientUtils {

    private static MinioClient minioClient;

    private static final Logger logger = LoggerFactory.getLogger(MinioClientUtils.class);

    public static Boolean fileUpload(MultipartFile file, String filePath, String suffix, MinioClientConfig minioClientConfig) {
        boolean flag = false;
        // 先判断桶是否存在,如果不存在先创建桶
        try {
            if (!getMinioClient(minioClientConfig).bucketExists(suffix)) {
                getMinioClient(minioClientConfig).makeBucket(suffix);
            }
            // 开始上传文件
            InputStream inputStream = file.getInputStream();
            getMinioClient(minioClientConfig).putObject(suffix,filePath,inputStream,inputStream.available(),file.getContentType());
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static Boolean fileDownload(MinioClientConfig minioClientConfig,String bucketName,String filePath,String fileName) {
        boolean flag = false;
        minioClient = getMinioClient(minioClientConfig);
        try {
            minioClient.getObject(bucketName,fileName,filePath);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }



    public static MinioClient getMinioClient(MinioClientConfig clientConfig) {
        if (minioClient == null) {
            try {
                minioClient = new MinioClient(clientConfig.getUrl(),clientConfig.getAccessKey(),clientConfig.getSecretKey());
            } catch (Exception e) {
                logger.info("creat minioClient fail");
                e.printStackTrace();
            }
        }
        return minioClient;
    }
}
