package cn.nj.storm.others.concurrent.singleton;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DbConnection
{
    public enum DataSourceEnum
    {
        DATASOURCE;
        
        private DbConnection connection = null;
        
        private DataSourceEnum()
        {
            connection = new DbConnection();
        }
        
        public DbConnection getConnection()
        {
            return connection;
        }
    }
    
    public static DbConnection getConnection()
    {
        return DataSourceEnum.DATASOURCE.getConnection();
    }
}
