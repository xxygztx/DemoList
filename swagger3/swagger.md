#swagger配置需要注意的几个问题

###需要启用swagger 
使用注解 @EnableOpenAi

###我们可能可能会报错

failed to start bean 'documentationpluginsbootstrapper';

我们需要在配置文件中配置

```yaml
  spring:
    mvc:
      pathmatch:
        matching-strategy: ant_path_matcher
```

###我们可能访问不到我们的页面，需要配置
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
                addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/")
                .setViewName("forward:/swagger-ui/index.html");
    }
}
```
给我们的页面放行

###swagger3的访问路径和2版本相比发生了变化
http://localhost:8080/swagger-ui/index.html
8080是我们的端口号，我们需要换为我们自己的端口号