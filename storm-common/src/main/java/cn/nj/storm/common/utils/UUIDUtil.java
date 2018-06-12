package cn.nj.storm.common.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * <构建UUID工具类>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/12/20]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UUIDUtil
{
    public static String[] chars = new String[] {"a", "j", "0", "b", "c", "d", "1", "f", "g", "e", "h", "i", "k", "l",
        "m", "n", "o", "p", "q", "r", "3", "s", "t", "u", "v", "w", "x", "y", "z", "2", "4", "R", "S", "6", "7", "8",
        "9", "A", "5", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "T", "U", "V",
        "W", "X", "Y", "Z"};
    
    /**
     * <构建常规32位UUID>
     * @return
     */
    public static String ordinaryUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    /**
     * <构建固定位数的UUID>
     * @return
     */
    public static String createUUIDInHalf()
    {
        String resultUUID = "";
        String uuid = ordinaryUUID();
        for (int i = 0; i < 16; i++)
        {
            String str = uuid.substring(i * 2, i * 2 + 2);
            int x = Integer.parseInt(str, 16);
            resultUUID += chars[x % 0x3E];
        }
        return resultUUID;
    }
    
    /**
     * <业务加密算法的UUID>
     * @return
     */
    public static String encryptionUUID(String uuid)
    {
        List<String> charList = Arrays.asList(chars);
        int maxIndex = charList.size() - 1;
        String resultUUID = "";
        uuid = StringUtils.isNotBlank(uuid) ? uuid : ordinaryUUID();
        char[] uuids = uuid.toCharArray();
        for (int i = 0; i < uuid.length(); i++)
        {
            String uuidword = String.valueOf(uuids[i]);
            int index = charList.indexOf(uuidword);
            if (i < 6)
            {
                if (index == 0)
                {
                    resultUUID += charList.get(maxIndex);
                }
                else
                {
                    resultUUID += charList.get(index - 1);
                }
            }
            else
            {
                if (index == maxIndex)
                {
                    resultUUID += charList.get(0);
                }
                else
                {
                    resultUUID += charList.get(index + 1);
                }
            }
        }
        return resultUUID;
    }
    
    /**
     * <业务解密算法的UUID>
     * @return
     */
    public static String decryptUUID(String uuid)
    {
        if (StringUtils.isBlank(uuid))
        {
            return uuid;
        }
        List<String> charList = Arrays.asList(chars);
        int maxIndex = charList.size() - 1;
        String resultUUID = "";
        char[] uuids = uuid.toCharArray();
        for (int i = 0; i < uuid.length(); i++)
        {
            String uuidword = String.valueOf(uuids[i]);
            int index = charList.indexOf(uuidword);
            if (i < 6)
            {
                if (index == maxIndex)
                {
                    resultUUID += charList.get(0);
                }
                else
                {
                    resultUUID += charList.get(index + 1);
                }
                
            }
            else
            {
                if (index == 0)
                {
                    resultUUID += charList.get(maxIndex);
                }
                else
                {
                    resultUUID += charList.get(index - 1);
                }
            }
        }
        return resultUUID;
    }
    
    public static void main(String[] args)
    {
        String uuid = ordinaryUUID();
        System.out.println(uuid + ":" + uuid.length());
        uuid = createUUIDInHalf();
        System.out.println(uuid + ":" + uuid.length());
        uuid = encryptionUUID(uuid);
        System.out.println(uuid + ":" + uuid.length());
        uuid = decryptUUID(uuid);
        System.out.println(uuid + ":" + uuid.length());
    }
}
