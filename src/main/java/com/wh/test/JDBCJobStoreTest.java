//package com.wh.test;
//
//import java.util.List;
//
//import org.quartz.CronTrigger;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.JobKey;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.SchedulerFactory;
//import org.quartz.SimpleScheduleBuilder;
//import org.quartz.SimpleTrigger;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.TriggerKey;
//import org.quartz.impl.StdSchedulerFactory;
//
//public class JDBCJobStoreTest {
//    public static void JobRun() throws SchedulerException, InterruptedException {
//
//        SchedulerFactory factory = new StdSchedulerFactory();
//
//        Scheduler scheduler = factory.getScheduler();
//
//        //触发器类型
//        SimpleScheduleBuilder builder = SimpleScheduleBuilder
//                // 设置执行次数
//                .repeatSecondlyForTotalCount(10, 3);
////                .withRepeatCount(10);
//
//
//        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
//                .withIdentity("job_1", "group_1")
//                .build();
//
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("trigger_1", "group_1")
//                .startNow()
//                .withSchedule(builder)
////                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
////                         .withIntervalInSeconds(3)
////                          .withRepeatCount(10))
//                .build();
//
//        scheduler.scheduleJob(jobDetail, trigger);
//
//        scheduler.start();
//
//        Thread.sleep(10000);
//
//        scheduler.shutdown();
//
//    }
//
//    public static void restoreJob() throws SchedulerException, InterruptedException{
//        SchedulerFactory factory = new StdSchedulerFactory();
//        Scheduler scheduler = factory.getScheduler();
//
//         JobKey jobKey = new JobKey("job_1", "group_1");
//         List<? extends Trigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey);
//
//         if(triggersOfJob.size()>0){
//             for(Trigger t:triggersOfJob){
//                 if(t instanceof SimpleTrigger || t instanceof CronTrigger){
//
//                     scheduler.resumeJob(jobKey);
//                 }
//             }
//         }
//
//        scheduler.start();
//    }
//
//
//    public static void main(String[] args) throws SchedulerException, InterruptedException {
//        //先注释第二个方法，执行第一个，看结果 之后注释第一个方法 执行第二个方法
//        JobRun();
////        restoreJob();
//
//    }
//}
