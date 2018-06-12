package cn.nj.storm.common.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <FTP工具类>
 * <功能详细描述>
 *
 * @author chenchaofan
 * @version [版本号, 2017/12/20]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FtpUtils implements LoggerInitializer
{
    /**
     * 获取FTPClient对象
     *
     * @param ftpHost
     *            FTP主机服务器
     * @param ftpPassword
     *            FTP 登录密码
     * @param ftpUserName
     *            FTP登录用户名
     * @param ftpPort
     *            FTP端口 默认为21
     * @return
     */
    public static FTPClient getFTPClient(String ftpHost, String ftpUserName, String ftpPassword, int ftpPort)
        throws IOException, Exception
    {
        FTPClient ftpClient = null;
        String message = null;
        try
        {
            ftpClient = new FTPClient();
            // 连接FTP服务器
            ftpClient.connect(ftpHost, ftpPort);
            // 登陆FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))
            {
                ftpClient.disconnect();
                message = "FtpUtils.getFTPClient error [未连接到FTP，用户名或密码错误。] user_name:" + ftpUserName + ",pass_word:"
                    + ftpPassword;
                throw new Exception(message);
            }
            else
            {
                INTERFACE_LOGGER.info("{}|{}|{}", "FtpUtils.getFTPClient", "FTP连接成功。");
            }
        }
        catch (SocketException e)
        {
            message = "FtpUtils.getFTPClient error [FTP的IP地址可能错误，请正确配置。] ip:" + ftpHost;
            throw new IOException(message, e);
        }
        catch (IOException e)
        {
            message = "FtpUtils.getFTPClient error [FTP的端口错误,请正确配置。] port:" + ftpPort;
            throw new IOException(message, e);
        }
        return ftpClient;
    }
    
    /**
     * <从FTP服务器下载文件>
     * @param ftpClient FTP客户端
     * @param localPath 下载到本地的位置
     * @param fileName 文件名称
     * @return file 下载的文件
     */
    public static File downloadFtpFile(FTPClient ftpClient, String ftpPath, String localPath, String fileName)
        throws IOException
    {
        if (ftpClient == null)
        {
            return null;
        }
        File localFile = new File(localPath + File.separatorChar + fileName);
        String message = null;
        try
        {
            // 中文支持
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);
            OutputStream os = new FileOutputStream(localFile);
            ftpClient.retrieveFile(fileName, os);
            os.close();
            ftpClient.logout();
            return localFile;
        }
        catch (FileNotFoundException e)
        {
            message = "FtpUtils.downloadFtpFile error [没有找到指定文件。] ftp_path:" + ftpPath + ",file_name:" + fileName;
            throw new IOException(message, e);
        }
        catch (IOException e)
        {
            message = "FtpUtils.downloadFtpFile error [文件读取错误。] local_path:" + localPath + ",file_name:" + fileName;
            throw new IOException(message, e);
        }
        finally
        {
            ftpClient.disconnect();
        }
    }
    
    /**
     * <从FTP服务器批量下载文件>
     * @param ftpClient
     * @param ftpPath
     * @param localPath
     * @param fileName
     * @return
     * @throws IOException
     */
    public static List<File> downloadFtpFiles(FTPClient ftpClient, String ftpPath, String localPath, String fileName)
        throws IOException
    {
        if (ftpClient == null)
        {
            return null;
        }
        List<File> list = new ArrayList<>();
        String message = null;
        try
        {
            // 中文支持
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setBufferSize(1024);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);
            for (FTPFile ff : ftpClient.listFiles())
            {
                // 忽略大小写的写法
                Pattern pattern = Pattern.compile(fileName, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(ff.getName());
                // 查找字符串中是否有匹配正则表达式的字符/字符串
                if (matcher.find())
                {
                    File localFile = new File(localPath + File.separatorChar + ff.getName());
                    OutputStream os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(ff.getName(), os);
                    os.close();
                    list.add(localFile);
                }
            }
            ftpClient.logout();
            return list;
        }
        catch (FileNotFoundException e)
        {
            message = "FtpUtils.downloadFtpFile error [没有找到指定文件。] ftp_path:" + ftpPath + ",file_name:" + fileName;
            throw new IOException(message, e);
        }
        catch (IOException e)
        {
            message = "FtpUtils.downloadFtpFile error [文件读取错误。] local_path:" + localPath + ",file_name:" + fileName;
            throw new IOException(message, e);
        }
        finally
        {
            ftpClient.disconnect();
        }
    }
}
