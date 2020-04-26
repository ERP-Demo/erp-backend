package cn.shiying.test_correlationaffiliate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@MapperScan({"cn.shiying.common.mapper","cn.shiying.test_correlationaffiliate.mapper"})
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.test_correlationaffiliate"})
@SpringBootApplication
public class Test_correlationaffiliateApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Test_correlationaffiliateApplication.class, args);
    }

}