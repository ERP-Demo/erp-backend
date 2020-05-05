package cn.shiying.electronic_case_model_catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.electronic_case_model_catalog"})
@SpringBootApplication
public class Electronic_case_model_catalogApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Electronic_case_model_catalogApplication.class, args);
    }

}