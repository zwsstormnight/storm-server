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
public interface Command<T> {
    String excute(T t);
}
