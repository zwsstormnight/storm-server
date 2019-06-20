package extt;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/12/20]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CommandImpl extends CommandAdaptor<Long> implements CommandChild<Long> {

    @Override
    public String excute(Long adb){
        return adb.toString();
    }
}
