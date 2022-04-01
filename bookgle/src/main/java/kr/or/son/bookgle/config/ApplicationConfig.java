package kr.or.son.bookgle.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "kr.or.son.bookgle.dao",  "kr.or.son.bookgle.service"})
@Import({ DBConfig.class, MyBatisConfig.class})
public class ApplicationConfig {

}