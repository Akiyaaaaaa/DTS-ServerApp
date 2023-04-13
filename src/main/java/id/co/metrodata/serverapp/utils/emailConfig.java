package id.co.metrodata.serverapp.utils;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

@Configuration
public class emailConfig {

  // @Bean
  // public SpringTemplateEngine springTemplateEngine() {
  // final SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
  // springTemplateEngine.addTemplateResolver(emailTemplateResolver());
  // return springTemplateEngine;
  // }

  @Bean
  public TemplateEngine emailTemplateEngine() {
    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.addTemplateResolver(emailTemplateResolver());
    return templateEngine;
  }

  public ClassLoaderTemplateResolver emailTemplateResolver() {
    final ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
    emailTemplateResolver.setOrder(Integer.valueOf(2));
    emailTemplateResolver.setResolvablePatterns(Collections.singleton("html/*"));
    emailTemplateResolver.setPrefix("templates/");
    emailTemplateResolver.setSuffix(".html");
    emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
    emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
    emailTemplateResolver.setCacheable(false);
    return emailTemplateResolver;
  }
}
