

package shenzhen.teamway.tms9000.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("shenzhen.teamway.tms9000.portal.mapper")
public class TMS9000Portal extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(TMS9000Portal.class, args);
    }

    /*
     Spring Boot的MVC默认配置中使用的 ViewResolver 为 ContentNegotiatingViewResolver，
     该视图解析器的功能是根据要请求的文档类型，来查找不同的视图以返回对应格式的文档。请求的文档类
     型要可以从请求头中的Accept中获取，也可以通过URI后缀名得到，如/login.html即为请求HTML格
     式的文档，这两种方式分别对应着两种不同的Strategy(策略)，默认为根据URI后缀名。
       因此，当用户请求 /login.html 时，spring会查找/login对应的控制器，并得到其返回的文档
       类型为application/json, 然后判断它与后缀名.html文档类型是否匹配，如果不匹配，就报
       HttpMediaTypeNotAcceptableException了
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
        super.configureContentNegotiation(configurer);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TMS9000Portal.class);
    }

}
