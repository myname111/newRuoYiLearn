package com.ruoyi.web.core.config;

import com.ruoyi.common.config.Global;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2的接口配置
 */
@Configuration
@EnableSwagger2//启动swagger
public class SwaggerConfig {
    /**
     * 创建API
     * @return
     */
    @Bean
    public Docket createRestApi(){
         return new Docket(DocumentationType.SWAGGER_2)
                 //详细定制
                 .apiInfo(apiInfo())
                 .select()
                 .apis(RequestHandlerSelectors.basePackage("com.ruoyi.web.controller.tool"))
                 .paths(PathSelectors.any())
                 .build();
    }
    private ApiInfo apiInfo(){
       return new ApiInfoBuilder()
               .title("标题:接口文档")
               .description("管理xxx")
               .contact(new Contact(Global.getName(),null,null))
               .version("版本号:"+ Global.getVersion())
               .build();
    }
}
