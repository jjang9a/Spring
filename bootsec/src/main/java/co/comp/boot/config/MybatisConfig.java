package co.comp.boot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "co.comp.boot.**.mapper")
public class MybatisConfig {

}
