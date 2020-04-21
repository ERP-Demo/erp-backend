package cn.shiying.drugsSupplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.drugsSupplier"})
@SpringBootApplication
public class DrugsSupplierApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DrugsSupplierApplication.class, args);
    }

}
