package cn.shiying.activiti;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@ComponentScan(basePackages = {"cn.shiying.config", "cn.shiying.activiti"})
@SpringBootApplication
public class ActivitiApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ActivitiApplication.class, args);
    }

}
