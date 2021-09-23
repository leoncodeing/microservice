package com.common.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utils.ThreadPoolUtil;

import java.util.concurrent.Callable;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args){
        SpringApplication.run(ConsumerApplication.class,args);

        new Thread(() -> {
            try {
                Thread.sleep(4*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadPoolUtil.submitCallable(() -> {
                int sum = 0;
                for (int i = 1 ;i<100;i++){
                  sum+=i;
                }
                return sum;
            });
        }).start();
    }
}
