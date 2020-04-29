package cn.shiying.icd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.icd"})
@SpringBootApplication
public class IcdApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(IcdApplication.class, args);
    }

}