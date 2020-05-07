package cn.jxust.leave;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//// //测试时先禁用springSecurity
//@EnableAutoConfiguration(exclude = {
//        SecurityAutoConfiguration.class
//})
@MapperScan("cn.jxust.leave.dao")
@SpringBootApplication
public class LeaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeaveApplication.class, args);
    }

}
