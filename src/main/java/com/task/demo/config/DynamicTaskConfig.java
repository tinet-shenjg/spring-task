package com.task.demo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Arrays;
import java.util.List;

/**
 * 动态定时任务
 *
 * @author shenjg
 * @date 2019/03/30
 **/
@Configuration
@EnableScheduling
public class DynamicTaskConfig implements SchedulingConfigurer {

    /**
     * 从数据库load所有定时任务
     */
    private List<Task> tasks = Arrays.asList(
            new Task(1, "任务1", "*/30 * * * * *"),
            new Task(2, "任务2", "*/30 * * * * *"),
            new Task(3, "任务3", "*/30 * * * * *"),
            new Task(4, "任务4", "*/30 * * * * *")
    );

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        tasks.forEach(task -> {

            //任务执行线程
            Runnable runnable = () -> {
                String taskName = task.getName();
                System.out.println(taskName);

            };

            //任务触发器
            Trigger trigger = triggerContext -> {
                //获取定时触发器，这里可以每次从数据库获取最新记录，更新触发器，实现定时间隔的动态调整
                CronTrigger cronTrigger = new CronTrigger(task.getCron());
                return cronTrigger.nextExecutionTime(triggerContext);
            };

            //注册任务
            scheduledTaskRegistrar.addTriggerTask(runnable, trigger);
        });

    }

    @Data
    @AllArgsConstructor
    static class Task {
        /**
         * 主键ID
         */
        private int id;
        /**
         * 任务名称
         */
        private String name;
        /**
         * cron表达式
         */
        private String cron;
    }
}
