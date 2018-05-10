package cn.nj.storm.persistence.mybatis.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * <数据源配置>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/8]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
@MapperScan("cn.nj.storm.**.mapper")
@EnableTransactionManagement
public class DataSourceConfig
{
    @Autowired
    private DataSource dataSource;

    @Autowired
    private Environment env;
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
        throws Exception
    {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        //设置数据源
        fb.setDataSource(dataSource);
        //配置分页插件
//        fb.setPlugins(new Interceptor[] {pageInterceptor});
        fb.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        fb.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        return fb.getObject();
    }

//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory)
//    {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
    
//    @Bean
//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager()
//    {
//        return new DataSourceTransactionManager(dataSource);
//    }
}
