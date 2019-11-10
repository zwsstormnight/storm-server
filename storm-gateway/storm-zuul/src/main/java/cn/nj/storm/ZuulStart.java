package cn.nj.storm;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * <ZuulStart>
 * <zuul网关启动类>
 *
 * @author zhengweishun
 * @version [版本号, 2017/8/12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@EnableDiscoveryClient
@EnableZuulProxy
@SpringCloudApplication
public class ZuulStart {

    public static void main(String[] args) {
        SpringApplication.run(ZuulStart.class, args);
    }
}


//Zuul大部分功能都是通过过滤器来实现的。Zuul中定义了四种标准过滤器类型，这些过滤器类型对应于请求的典型生命周期。
//
//        (1) PRE：这种过滤器在请求被路由之前调用。
// 我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
//TODO 安全验证、登陆验证、请求出入日志、请求加解密、服务选取
//
//        (2) ROUTING：这种过滤器将请求路由到微服务。
// 这种过滤器用于构建发送给微服务的请求，
// 并使用Apache HttpClient或Netfilx Ribbon请求微服务。
//  TODO dubbo服务调用的路由改造

//        (3) POST：这种过滤器在路由到微服务以后执行。
// 这种过滤器可用来为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
//
//        (4) ERROR：在其他阶段发生错误时执行该过滤器。

/**
 * 当外部HTTP请求到达API网关服务的时候，
 * 首先它会进入第一个阶段pre，在这里它会被pre类型的过滤器进行处理，
 * 该类型的过滤器主要目的是在进行请求路由之前做一些前置加工，比如请求的校验等。
 *
 * 在完成了pre类型的过滤器处理之后，
 * 请求进入第二个阶段routing，也就是之前说的路由请求转发阶段，请求将会被routing类型过滤器处理，
 * 这里的具体处理内容就是将外部请求转发到具体服务实例上去的过程，
 * 当服务实例将请求结果都返回之后，routing阶段完成，请求进入第三个阶段post，
 * 此时请求将会被post类型的过滤器进行处理，
 * 这些过滤器在处理的时候不仅可以获取到请求信息，还能获取到服务实例的返回信息，所以在post类型的过滤器中，
 * 我们可以对处理结果进行一些加工或转换等内容。
 *
 * 另外，还有一个特殊的阶段error，
 * 该阶段只有在上述三个阶段中发生异常的时候才会触发，
 * 但是它的最后流向还是post类型的过滤器，因为它需要通过post过滤器将最终结果返回给请求客户端
 */
//TODO 定制一种STATIC类型的过滤器，直接在Zuul中生成响应，而不将请求转发到后端的微服务。