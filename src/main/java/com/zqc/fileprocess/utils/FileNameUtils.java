package com.zqc.fileprocess.utils;

import java.time.LocalDate;

/**
 * @Author: 张全成
 * 该工具类目前只是生成文件在服务器上唯一文件名
 * 后续会采用雪花算法生成唯一标识，
 * 项目中建议大家自己或者借助平台生成的唯一ID
 * @Date: 2020/5/17 12:22
 * @Version 1.0
 */
public class FileNameUtils {
    public static String getNextId (String prefix) {
        String fileName = "";
        // 生成6位数随机数
        String random = String.valueOf((int) ((Math.random()*9+1)*100000));
        fileName = new StringBuilder().append(prefix).append(LocalDate.now()).append(random).toString();
        return fileName;
    }
}
