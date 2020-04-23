package cn.shiying.register;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.register"})
@MapperScan({"cn.shiying.common.mapper","cn.shiying.register.mapper"})
@SpringBootApplication
public class RegisterApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RegisterApplication.class, args);
    }

}
