package cn.shiying.supplier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@MapperScan({"cn.shiying.common.mapper.supplier","cn.shiying.drugsSupplier.mapper"})
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.supplier"})
@SpringBootApplication
public class SupplierApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SupplierApplication.class, args);
    }

}
