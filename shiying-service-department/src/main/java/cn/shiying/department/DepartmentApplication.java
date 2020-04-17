package cn.shiying.department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.department"})
@SpringBootApplication
public class DepartmentApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DepartmentApplication.class, args);
    }

}