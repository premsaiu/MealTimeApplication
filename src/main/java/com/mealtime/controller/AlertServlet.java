package com.mealtime.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class AlertServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(AlertServlet.class);
	public void init() throws ServletException {
    	//public static void main(String args[]){
    	System.out.println("AlertServlet init method initialized.....");
    	logger.info("AlertServlet init method initialized.....");
		JobKey jobKey = new JobKey("alertJOB", "group1");
    	JobDetail job = JobBuilder.newJob(AlertJob.class)
				.withIdentity(jobKey).build();

    	Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("alertJOB", "group1")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("1 * * * * ?"))
				//CronScheduleBuilder.cronSchedule("0 0 12 * * ?"))
				.build();
    	
    	Scheduler scheduler;
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
	    	scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/*public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
{
    	System.out.println("AlertServlet Get method initialized.....");
    	logger.info("AlertServlet Get method initialized.....");
		JobKey jobKey = new JobKey("alertJOB", "group1");
    	JobDetail job = JobBuilder.newJob(AlertJob.class)
				.withIdentity(jobKey).build();

    	Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("alertJOB", "group1")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("1 * * * * ?"))
				//CronScheduleBuilder.cronSchedule("0 0 12 * * ?"))
				.build();
    	
    	Scheduler scheduler;
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
	    	scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}*/

}
