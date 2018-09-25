package com.dome.test;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import java.io.File;
import java.io.FileInputStream;


public class FTPTest {
    public static void main(String[] args) throws Exception{
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("192.168.1.81",21);
        //服务器地址和端口
        ftpClient.login("test","test");
        //登录的用户名和密码
        //读取本地文件，给出的是本地文件地址
        FileInputStream inputStream = new FileInputStream(new File("D:\\log\\2321314234.jpg"));
        //设置上传路径
        ftpClient.changeWorkingDirectory("\\test\\ftp\\");
        //设置文件类型
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //1.服务器端保存的文件名，2.上传文件的inputstream
        ftpClient.storeFile("test.png",inputStream);
        ftpClient.logout();

    }
}
