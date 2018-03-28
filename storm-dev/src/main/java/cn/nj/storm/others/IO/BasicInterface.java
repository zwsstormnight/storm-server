package cn.nj.storm.others.IO;

import java.io.FileReader;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/2/4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BasicInterface
{
    public String fileReaderHandler(String file)
    {
        String result = "";
        char[] chars = new char[1024];
        try
        {
            FileReader fileReader = new FileReader(file);
            
            while (fileReader.read(chars) > 0)
            {
                result += chars;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public static void main(String[] args)
    {
        
    }
}
