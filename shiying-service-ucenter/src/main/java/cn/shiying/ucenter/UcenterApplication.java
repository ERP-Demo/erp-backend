package cn.shiying.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"cn.shiying.config", "cn.shiying.ucenter"})
@SpringBootApplication
public class UcenterApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(UcenterApplication.class, args);
    }

}