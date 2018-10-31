package cn.nj.storm.token.annotation;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/10/29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public @interface FormToken {

    /**
     * 需要防重复功能的表单入口URL对应的controller方法需要添加的注解，用于生成token（默认为uuid）
     * @return
     */
    boolean save() default false;
    /**
     * 防重复表单提交表单到后台对应的URL的controller方法需要添加的注解，用于第一次成功提交后remove掉token
     * @return
     */
    boolean remove() default false;
    /**
     * 是否让token防重复拦截器放过token校验，为true时一般用于ajax提交放过到controller中处理，此功能可以在提交失败后恢复token
     * 如果拦截到位表单重复提交是否放过让controller处理,默认被拦截器处理返回false;
     * @return
     */
    boolean pass() default false;

}
