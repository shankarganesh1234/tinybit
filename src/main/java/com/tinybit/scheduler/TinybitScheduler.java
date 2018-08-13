package com.tinybit.scheduler;

import com.tinybit.constants.Constants;
import com.tinybit.service.crypto.CryptoServiceImpl;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TinybitScheduler {

    private static SchedulerFactory schedulerFactory;
    private static Scheduler scheduler;

    public static void begin(int repeatInterval) throws SchedulerException {

        if (schedulerFactory == null) {
            schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();
        }

        scheduler.start();

        JobDetail jobDetail = JobBuilder.newJob(CryptoServiceImpl.class).build();

        Trigger trigger = TriggerBuilder.newTrigger().startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(repeatInterval)
                .repeatForever()).withIdentity(TriggerKey.triggerKey(Constants.TRIGGER_KEY)).build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

}
