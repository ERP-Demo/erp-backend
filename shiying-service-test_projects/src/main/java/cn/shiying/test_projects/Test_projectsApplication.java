package cn.shiying.test_projects;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.test_projects"})
@SpringBootApplication
public class Test_projectsApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Test_projectsApplication.class, args);
    }

}