package kr.co.bctt.ssh.listener;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Quartz 를 사용한 CronScheduler 구현
 * 
 * @author digimon1740
 * */
public class JobScheduler {
 
	String type;            
    String cronExp;                    
    Class<? extends Job> clazz;
    
    public JobScheduler(Class<? extends Job> clazz, String cronExp, String type) {
    	this.type = type;
        this.clazz = clazz;
        this.cronExp = cronExp;
    }
 
    public void triggerJob() {
        
        Scheduler sch = null;
        SchedulerFactory schFactory = null;
        
        // schedule the job
        //    this.clazz 는 기본적으로 JobExecuter 클래스이다.
        JobDetail job = JobBuilder.newJob(this.clazz).withIdentity(this.clazz.getName()).build();
 
        // batch.properties 에서 크론 표현식을 가져와 Trigger를 생성
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("crontrigger", this.type)
                .withSchedule(CronScheduleBuilder.cronSchedule(this.cronExp))
                .build();
 
        try {
            schFactory = new StdSchedulerFactory();
            sch = schFactory.getScheduler();
            sch.start();    //    JobExecuter.class Start
            sch.scheduleJob(job, cronTrigger);
        } catch (SchedulerException e) {
        }
        
    }
}
