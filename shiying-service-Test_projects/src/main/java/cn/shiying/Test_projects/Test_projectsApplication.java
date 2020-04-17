package cn.shiying.Test_projects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.Test_projects"})
@SpringBootApplication
public class Test_projectsApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Test_projectsApplication.class, args);
    }

}