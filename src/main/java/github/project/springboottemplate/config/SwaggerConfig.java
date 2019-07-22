package github.project.springboottemplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Value("${service.swagger.enable}")
  private boolean enable;

  @Value("${service.swagger.base-package}")
  private String basePackage;

  @Value("${service.swagger.group-name}")
  private String groupName;

  @Value("${service.swagger.application-information.title}")
  private String title;

  @Value("${service.swagger.application-information.description}")
  private String description;

  @Value("${service.swagger.application-information.version}")
  private String version;

  @Value("${service.swagger.application-information.service-url}")
  private String serviceUrl;

  @Value("${service.swagger.application-information.license}")
  private String license;

  @Value("${service.swagger.application-information.license-url}")
  private String licenseUrl;

  /**
   * Describe Swagger Configuration. The "enable" flag is used to enable or disable the swagger-ui
   *
   * @return Docket
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName(groupName)
        .select()
        .apis(RequestHandlerSelectors.basePackage(basePackage))
        .paths(PathSelectors.any())
        .build()
        .enable(enable)
        .apiInfo(apiInfo());
  }

  /**
   * Describe custom information about the Application.
   *
   * @return ApiInfo
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(title)
        .description(description)
        .termsOfServiceUrl(serviceUrl)
        .license(license)
        .licenseUrl(licenseUrl)
        .version(version)
        .build();
  }

}
