package com.task.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * demo demo
 *
 * @author shenjg
 * @date 2019/03/30
 **/
@Component
public class DemoTask {

    /**
     * 每隔1秒执行, 单位：ms。
     */
    @Scheduled(fixedRate = 1000)
    public void testFixRate() {
        System.out.println("每隔1秒执行一次：" + System.currentTimeMillis());
    }
}
