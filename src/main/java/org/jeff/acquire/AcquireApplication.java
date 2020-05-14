package org.jeff.acquire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AcquireApplication {
    public static void main(String args[]) {
        SpringApplication.run(AcquireApplication.class,args);
    }
}
