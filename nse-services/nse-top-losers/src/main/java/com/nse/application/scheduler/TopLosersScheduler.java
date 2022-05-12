package com.nse.application.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Configuration
@EnableScheduling
public class TopLosersScheduler implements SchedulingConfigurer {

    @Autowired
    private TopLoserServiceScheduler topLoserServiceScheduler;

    @Bean
    public ThreadPoolTaskScheduler taskExecutor() {
        return new ThreadPoolTaskScheduler();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());

        // call top losers for GIDXNIFTY100
        taskRegistrar.addTriggerTask( () -> topLoserServiceScheduler.start("GIDXNIFTY100", "TOP_LOSERS", 20), context -> {
            Optional<Date> lastCompletionTime =
                    Optional.ofNullable(context.lastCompletionTime());
            Instant nextExecutionTime =
                    lastCompletionTime.orElseGet(Date::new).toInstant()
                            .plusMillis(topLoserServiceScheduler.getInterval());
            return Date.from(nextExecutionTime);
        });

        // call top losers for GIDXNIFTY500
        taskRegistrar.addTriggerTask( () -> topLoserServiceScheduler.start("GIDXNIFTY500", "TOP_LOSERS", 20), context -> {
            Optional<Date> lastCompletionTime =
                    Optional.ofNullable(context.lastCompletionTime());
            Instant nextExecutionTime =
                    lastCompletionTime.orElseGet(Date::new).toInstant()
                            .plusMillis(topLoserServiceScheduler.getInterval());
            return Date.from(nextExecutionTime);
        });

        //call top losers for GIDXNIFSMCP100
        taskRegistrar.addTriggerTask( () -> topLoserServiceScheduler.start("GIDXNIFSMCP100", "TOP_LOSERS", 20), context -> {
            Optional<Date> lastCompletionTime =
                    Optional.ofNullable(context.lastCompletionTime());
            Instant nextExecutionTime =
                    lastCompletionTime.orElseGet(Date::new).toInstant()
                            .plusMillis(topLoserServiceScheduler.getInterval());
            return Date.from(nextExecutionTime);
        });

    }
}
