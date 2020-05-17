package com.zqc.fileprocess.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioClientConfig {
    /**
     * minio host
     */
    private String url;

    /**
     * 接入账号
     */
    private String accessKey;

    /**
     * 接入口令
     */
    private String secretKey;

    /**
     * 文件存放桶名
     */
    private String bocket;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBocket() {
        return bocket;
    }

    public void setBocket(String bocket) {
        this.bocket = bocket;
    }
}
