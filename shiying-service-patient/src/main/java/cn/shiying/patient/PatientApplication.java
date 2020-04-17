package cn.shiying.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.patient"})
@SpringBootApplication
public class PatientApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(PatientApplication.class, args);
    }

}
