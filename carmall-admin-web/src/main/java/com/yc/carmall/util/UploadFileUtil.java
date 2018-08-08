/*
 * Copyright (c) 2018, Yue Chang and/or its affiliates. All rights reserved.
 * Yue Chang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.yc.carmall.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: UploadFileUtil
 * @description: 文件上传工具类
 * @date 2018年08月07日 17:43
 */
public class UploadFileUtil {

    private static Logger logger = LoggerFactory.getLogger(UploadFileUtil.class);

    private UploadFileUtil() {}

    public static FtpClient getFtpClient(String url, int port, String username, String password){
        FtpClient ftp = null;
        try {
            // 创建地址
            SocketAddress addr = new InetSocketAddress(url, port);
            // 连接
            ftp = FtpClient.create();
            ftp.connect(addr);
            // 登陆
            ftp.login(username, password.toCharArray());
            ftp.setBinaryType();
        } catch (FtpProtocolException | IOException e) {
            logger.info("getFtpClient failure.", e);
        }
        return ftp;
    }


    public static void upload(String localFile, String ftpFile, FtpClient ftp) {

        logger.info("localFile:{}, ftpFile:{}", localFile, ftpFile);
        OutputStream os = null;
        FileInputStream fis = null;
        try {
            // 将ftp文件加入输出流中。输出到ftp上
            os = ftp.putFileStream(ftpFile);
            File file = new File(localFile);

            // 创建一个缓冲区
            fis = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int c;
            while ((c = fis.read(bytes)) != -1) {
                os.write(bytes, 0, c);
            }
            logger.info("upload success!");
        } catch (FtpProtocolException | IOException e) {
            logger.info("upload failure.", e);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                logger.info("close inputStream failure.", e);
            }
        }
    }


    public static void main(String[] args) {


        FtpClient ftpClient = getFtpClient("serverhost", 21, "kevin", "yuechang");

        System.out.println(ftpClient);

        upload("C:\\Users\\yuechang\\Pictures\\maxresdefault.jpg", "/home/kevin/temp.jpg", ftpClient);

    }

}
