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

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author Yue Chang
 * @version 1.0
 * @className: FtpUtil
 * @description: FTP文件上传工具类
 * @date 2018年08月08日 14:21
 */
public class FTPUtil {

    private static Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    /**
     * 初始化ftp服务器
     * @param hostname
     * @param username
     * @param password
     * @param port
     */
    public static FTPClient initFtpClient(String hostname,String username,String password,Integer port) {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            //连接ftp服务器
            ftpClient.connect(hostname, port);
            //登录ftp服务器
            ftpClient.login(username, password);
            //是否成功登录服务器
            int replyCode = ftpClient.getReplyCode();
            if(FTPReply.isPositiveCompletion(replyCode)){
                logger.info("login ftp server success");
            }
        }catch (IOException e) {
            logger.info("initFtpClient failure", e);
        }
        return ftpClient;
    }

    /**
     * 上传文件
     * @param ftpClient FTP客户端
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public static boolean uploadFile(FTPClient ftpClient, String pathname, String fileName,InputStream inputStream){
        boolean flag = false;
        try{
            logger.info("start upload file...");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            createDirecroty(ftpClient, pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.setControlEncoding("utf-8");
            ftpClient.storeFile(fileName, inputStream);
            logger.info("upload file end...");
            inputStream.close();
            ftpClient.logout();
            flag = true;
            logger.info("upload file success...");

        }catch (Exception e) {
            logger.info("upload file failure", e);
        }finally{
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    logger.info("disconnect ftpClient failure", e);
                }
            }
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.info("close inputStream failure", e);
                }
            }
        }
        return flag;
    }

    /**
     * 改变FTPClient目录路径
     * @param ftpClient FTP客户端
     * @param directory 目录
     * @return
     */
    public static boolean changeWorkingDirectory(FTPClient ftpClient,String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                logger.info("change working directory to {} success.", directory);
            } else {
                logger.info("change working directory to {} failure. start create directory", directory);
            }
        } catch (IOException e) {
            logger.info("changeWorkingDirectory failure ", e);
        }
        return flag;
    }

    /**
     * 创建多层目录文件，如果有ftp服务器已存在该文件，则不创建;如果无，则创建;
     * @param ftpClient FTP客户端
     * @param remote 目录
     * @return
     * @throws IOException
     */
    public static boolean createDirecroty(FTPClient ftpClient, String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(ftpClient, directory)) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("UTF-8"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(ftpClient, path)) {

                    if (!makeDirectory(ftpClient, subDirectory)) {
                        logger.info("create directory [{}] failure", subDirectory);
                    }
                }
                changeWorkingDirectory(ftpClient, subDirectory);

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    /**
     * 判断ftp服务器文件是否存在
     * @param ftpClient FTP客户端
     * @param path FTP服务器文件路径
     * @return
     * @throws IOException
     */
    public static boolean existFile(FTPClient ftpClient,String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 创建目录
     * @param ftpClient FTP客户端
     * @param dir 目录
     * @return
     */
    public static boolean makeDirectory(FTPClient ftpClient,String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                logger.info("create direction {} success", dir);
            } else {
                logger.info("create direction {} failure", dir);
            }
        } catch (Exception e) {
            logger.info("makeDirectory failure", e);
        }
        return flag;
    }

    /**
     * 下载文件
     * @param ftpClient FTP客户端
     * @param pathname FTP服务器文件目录
     * @param filename 文件名称
     * @param localpath 下载后的文件路径
     * @return
     */
    public static  boolean downloadFile(FTPClient ftpClient,String pathname, String filename, String localpath){
        boolean flag = false;
        OutputStream os=null;
        try {
            logger.info("start download file...");
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    File localFile = new File(localpath + "/" + file.getName());
                    os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            flag = true;
            logger.info("file download success");

        } catch (Exception e) {
            logger.info("file download failure", e);
        } finally{
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    logger.info("disconnect ftpClient failure", e);
                }
            }
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    logger.info("close inputStream failure", e);
                }
            }
        }
        return flag;
    }

    /**
     * 删除文件
     * @param ftpClient FTP客户端
     * @param pathname FTP服务器保存目录
     * @param filename 要删除的文件名称
     * @return
     */
    public static boolean deleteFile(FTPClient ftpClient,String pathname, String filename){
        boolean flag = false;
        try {
            logger.info("start delete file...");
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
            logger.info("delete file success");
        } catch (Exception e) {
            logger.info("delete file failure", e);
        } finally {
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    logger.info("disconnect ftpClient failure", e);
                }
            }
        }
        return flag;
    }


    public static void main(String[] args) throws FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\yuechang\\Desktop\\temp.txt"));

        FTPClient ftpClient = initFtpClient("serverhost", "kevin", "yuechang", 21);
        FTPUtil.uploadFile(ftpClient, "/home/kevin/upload","temp.txt",fileInputStream);
    }
}
