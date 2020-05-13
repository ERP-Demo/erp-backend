package cn.shiying.requirements;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@MapperScan({"cn.shiying.common.mapper","cn.shiying.requirements.mapper"})
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.requirements"})
@SpringBootApplication
public class RequirementsApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RequirementsApplication.class, args);
    }

}
