/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
package cn.nj.storm.persistence.shardng.mybatis;

//https://mp.weixin.qq.com/s/n2ynawt5g2znCK37PR_rKw
//使用ShardingJdbc的限制
//
//        ShardingJdbc中间件并非是万能，它还是有一些SQL和JDCB接口的使用限制，其最大的使用限制在于如下几点：
//
//        （1）对于DataSource接口不支持超时相关的操作。
//
//        （2）对于Connection接口不支持存储过程、游标、函数、savePoint、自定义类型映射等。
//
//        （3）对于Statement和PreparedStatement接口，不支持返回多结果集的语句和国际化操作。
//
//        （4）对于ResultSet接口，不支持对于结果集指针位置判断；不支持通过非next方法改变结果指针位置；不支持修改结果集内容。
//
//        （5）SQL语句限制：不支持HAVING；不支持OR，UNION 和 UNION ALL；不支持特殊INSERT，尤其是是批量插入的SQL语句；不支持DISTINCT聚合；不支持dual虚拟表；不支持SELECT LASTINSERTID()；不支持CASE WHEN。
//
