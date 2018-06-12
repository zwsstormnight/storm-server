package cn.nj.storm.common.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * <IO工具类>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/2/23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class IOUtil
{
    /**
     * 根据文件路径读取文件内容
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String readFile(String filePath)
        throws IOException
    {
        File file = new File(filePath);
        if (file.exists() && file.isFile())
        {
            BufferedReader in = null;
            String line = null;
            try
            {
                in = new BufferedReader(new FileReader(file));
                StringBuilder sb = new StringBuilder();
                while ((line = in.readLine()) != null)
                {
                    sb.append(line).append('\n');
                }
                return sb.toString();
            }
            catch (Exception e)
            {
                throw new IOException(e);
            }
            finally
            {
                IOUtils.closeQuietly(in);
            }
            
        }
        return null;
    }
    
    /**
     *
     * Description: <br>
     *
     * @author yang.zhipeng <br>
     * @taskId <br>
     * @param content
     * <br>
     * @param file
     * <br>
     */
    public static void writeFile(byte[] content, File file)
        throws Exception
    {
        if (file != null)
        {
            BufferedOutputStream out = null;
            try
            {
                out = new BufferedOutputStream(new FileOutputStream(file));
                out.write(content);
                out.flush();
            }
            catch (Exception e)
            {
                throw new IOException(e);
            }
            finally
            {
                IOUtils.closeQuietly(out);
            }
        }
    }
    
    /**
     * Description: writeFile<br>
     *
     * @author yang.zhipeng <br>
     * @param contents
     * <br>
     * @param file
     * <br>
     */
    public static void writeFile(String contents, File file)
        throws Exception
    {
        if (file != null)
        {
            BufferedWriter out = null;
            try
            {
                out = new BufferedWriter(new FileWriter(file));
                out.write(contents);
                out.flush();
            }
            catch (Exception e)
            {
                throw new IOException(e);
            }
            finally
            {
                IOUtils.closeQuietly(out);
            }
        }
    }
}
