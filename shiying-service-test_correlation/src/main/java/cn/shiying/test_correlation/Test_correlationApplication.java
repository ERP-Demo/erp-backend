package cn.shiying.test_correlation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@MapperScan({"cn.shiying.common.mapper.TestProjects","cn.shiying.test_correlation.mapper"})
@ComponentScan(basePackages = {"cn.shiying.config","cn.shiying.test_correlation"})
@SpringBootApplication
public class Test_correlationApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Test_correlationApplication.class, args);
    }

}