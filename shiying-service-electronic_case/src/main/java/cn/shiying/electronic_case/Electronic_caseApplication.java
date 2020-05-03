package cn.shiying.electronic_case;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@MapperScan({"cn.shiying.common.mapper","cn.shiying.electronic_case.mapper"})
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.electronic_case"})
@SpringBootApplication
public class Electronic_caseApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Electronic_caseApplication.class, args);
    }

}
