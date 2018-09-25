package com.dome.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * ftp处理
 *
 */
@Component
public class FtpClient {
    protected static Logger logger = LoggerFactory.getLogger(FtpClient.class);
    /**
     * 本地字符编码
     */
    private static String LOCAL_CHARSET = "GBK";

    // FTP协议里面，规定文件名编码为iso-8859-1
    private static String SERVER_CHARSET = "ISO-8859-1";


    /**
     * Description: 从FTP服务器下载文件
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     */
    public boolean downFile(String url, int port, String username, String password, String remotePath, String fileName, String localPath) {
        String myCharset = System.getProperty("file.encoding");//查看系统编码
        logger.info("================file.encoding:"+myCharset);
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            boolean login = login(ftp, url, port, username, password);//登录
            if (login) {

                if (remotePath.startsWith("/") && remotePath.endsWith("/")) {
                    logger.info("login status {}",String.valueOf(login));
                    String p = new String(remotePath.getBytes("gbk"), ftp.DEFAULT_CONTROL_ENCODING);
                    logger.info("================remotePath:"+p);
                    System.out.println(p);
                    boolean path = ftp.changeWorkingDirectory(new String(remotePath.getBytes(LOCAL_CHARSET), ftp.DEFAULT_CONTROL_ENCODING));//转移到FTP服务器目录
                    if (!path) {
                        logger.error("ftp 服务器进入路径：" + remotePath + " 失败！");
                    } else {
                        logger.info("ftp 服务器进入路径：" + remotePath + " 成功！");
                        list(ftp, fileName, localPath);
                    }
                }
            }
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("ftp 服务器：" + e.getMessage());
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    logger.error(ioe.getMessage());
                }
            }
        }
        logger.info("outside文件获取成功！");
        try {
            disConnection(ftp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * 登陆FTP服务器
     *
     * @param ftp
     * @param host     FTPServer IP地址
     * @param port     FTPServer 端口
     * @param username FTPServer 登陆用户名
     * @param password FTPServer 登陆密码
     * @return 是否登录成功
     * @throws IOException
     */
    public  boolean login(FTPClient ftp, String host, int port, String username, String password) throws IOException {
        ftp.connect(host, port);
        if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
            if (ftp.login(username, password)) {
                if (FTPReply.isPositiveCompletion(ftp.sendCommand("OPTS UTF8", "ON"))) {// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
                    LOCAL_CHARSET = "UTF-8";
                }
                ftp.setControlEncoding(LOCAL_CHARSET);
                ftp.enterLocalActiveMode();// 设置主动模式
                return true;
            }
        }
        if (ftp.isConnected()) {
            ftp.disconnect();
        }
        return false;
    }

    /**
     * 关闭数据链接
     *
     * @param ftp
     * @throws IOException
     */
    public void disConnection(FTPClient ftp) throws IOException {
        if (ftp.isConnected()) {
            ftp.disconnect();
        }
    }
    private void list(FTPClient ftp, String fileName, String localPath) {
        try {
            ftp.printWorkingDirectory();
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                // 提取绝对地址的目录以及文件名
                logger.info("ftp get fire name ========================：" + ff.getName());
                // 进入文件所在目录，注意编码格式，以能够正确识别中文目录
                //排除前两层目录
                if (ff.getName().equals(".") || ff.getName().equals("..")) {
                    continue;
                } else if (ff.isFile()) {

                    logger.info(localPath + ff.getName());
                        File localFile = new File(localPath + ff.getName());
                        OutputStream is = new FileOutputStream(localFile);
                        ftp.retrieveFile(ff.getName(), is);
                        is.flush();
                        is.close();
                } else if (ff.isDirectory()) {
                    String file = ff.getName();
                    logger.info("ftp ff.getName().length()：" + file.length());

                        boolean path = ftp.changeWorkingDirectory(new String(file.getBytes(LOCAL_CHARSET), ftp.DEFAULT_CONTROL_ENCODING));//转移到FTP服务器目录
                        if (path){
                            list(ftp, fileName, localPath);
                        }else{
                            logger.error("ftp 服务器进入路径：" + ff.getName() + " 失败！");
                        }
                }
            }
        } catch (Exception e) {
            logger.error("ftp 服务器错误：" + e.getMessage());
        }
    }
}