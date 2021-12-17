package cn.tedu.sp06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Sp06ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp06ZuulApplication.class, args);
    }

}
