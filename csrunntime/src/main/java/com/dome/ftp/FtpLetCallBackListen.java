//package com.dome.ftp;
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.ftpserver.ftplet.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.IOException;
//
//public class FtpLetCallBackListen extends DefaultFtplet {
//    private static final Logger LOGGER = LoggerFactory.getLogger(FtpLetCallBackListen.class);
//
//    @Override
//    public void init(FtpletContext ftpletContext) throws FtpException {
//        FTPClient ftpClient = new FTPClient();
//        try {
//            ftpClient.connect("192.168.1.81",21);
//            //服务器地址和端口
//            boolean login=ftpClient.login("test","test");
//            System.out.println("login"+login);
//            System.out.println(ftpClient.isConnected());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public FtpletResult onLogin(FtpSession session, FtpRequest request) throws FtpException, IOException {
//        System.out.println("123123");
//        return super.onLogin(session, request);
//    }
//
//
//    @Override
//    public FtpletResult onUploadEnd(FtpSession session, FtpRequest request) throws FtpException{
//        LOGGER.error("1232131321456");
//
////        return super.onUploadEnd(session, request);
//        File workingDir = workingDir(session);
//        String fileName = request.getArgument();
//        LOGGER.debug(workingDir.getAbsolutePath());
//        LOGGER.debug(fileName);
//        String ftpFolder = "D:\\log";
//
//
//        String ftpDataPath = workingDir.getAbsolutePath();
//        String ftpDataRelativePath = ftpDataPath.substring(ftpFolder.length());
//        String sep = System.getProperty("file.separator");
//        // get rid of the separators in the front end of string.
//        while (ftpDataRelativePath.startsWith(sep)) {
//            ftpDataRelativePath = ftpDataRelativePath.substring(1);
//        }
//        if ("\\".equals(sep))
//            sep = "\\\\";
//        String[] pathInfo = ftpDataRelativePath.split(sep);
//        if (pathInfo.length < 3) {
//            LOGGER.error("parse the ftp data path failed. Check the camera ftp related settings.");
//            return FtpletResult.DEFAULT;
//        }
//        String captureDate = pathInfo[1];
//        LOGGER.debug(captureDate);
//        String cameraId = pathInfo[2];
//        return FtpletResult.DEFAULT;
//    }
//    private File workingDir(FtpSession session) throws FtpException {
//        LOGGER.error("#1232131321456");
//        FtpFile workingDirectory = session.getFileSystemView().getWorkingDirectory();
//        String home = session.getUser().getHomeDirectory();
//        String absolutePath = workingDirectory.getAbsolutePath().substring(1);
//        File workingDir = new File(new File(home), absolutePath);
//        return workingDir;
//    }
//}
//
