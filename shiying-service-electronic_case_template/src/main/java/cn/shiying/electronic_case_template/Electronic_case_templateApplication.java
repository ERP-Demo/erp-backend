package cn.shiying.electronic_case_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.electronic_case_template"})
@SpringBootApplication
public class Electronic_case_templateApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Electronic_case_templateApplication.class, args);
    }

}