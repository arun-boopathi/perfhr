package com.perficient.hr.scheduler;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import akka.actor.ActorSystem;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import scala.concurrent.duration.Duration;

public class PerficientListener implements ServletContextListener{
	


	public void contextDestroyed(ServletContextEvent arg0) {
				
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		ActorSystem actorSystem = ActorSystem.create( "MySystem" );
		
		// ***************For every 5 seconds*******************
		
        actorSystem.scheduler().schedule( Duration.create(1, TimeUnit.SECONDS),
                Duration.create(5, TimeUnit.SECONDS),
                new Runnable() {
                    public void run() {
                        System.out.println("\n\n\n\n========================>Every5sec=");
                    }
                },
                actorSystem.dispatcher()
        );
        
        // ****************For Particular Time(24 hours)******************
        
        int j=nextExecutionInSeconds(3, 30); // hour and minute
        actorSystem.scheduler().scheduleOnce(
		        Duration.create(j, TimeUnit.SECONDS),
		        new Runnable() {
		            public void run() {
		                System.out.println("\n\n\n=========================>Time");
		            }
		        },
		        actorSystem.dispatcher()
		);
        
        //******************For Month and Day*****************************
        
        try {
			if(monthDateCheck(1,30)==1)
			{
			
			actorSystem.scheduler().scheduleOnce(
			        Duration.create(10, TimeUnit.SECONDS),
			        new Runnable() {
			            public void run() {
			                System.out.println("\n\n\n=========================>Onstart");
			            }
			        },
			        actorSystem.dispatcher()
			);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
      //******************For particular date*****************************
        
        try {
			if(dateCheck("2016-08-10")==1){
			
			actorSystem.scheduler().scheduleOnce(
			        Duration.create(10, TimeUnit.SECONDS),
			        new Runnable() {
			            public void run() {
			                System.out.println("\n\n\n=========================>Onstart");
			            }
			        },
			        actorSystem.dispatcher()
			);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static int nextExecutionInSeconds(int hour, int minute){
        return Seconds.secondsBetween(
                new DateTime(),
                nextExecution(hour, minute)
        ).getSeconds();
    }

    public static DateTime nextExecution(int hour, int minute){
        DateTime next = new DateTime()
                .withHourOfDay(hour)
                .withMinuteOfHour(minute)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);

        return (next.isBeforeNow())
                ? next.plusHours(24)
                : next;
    }
    
    
    public static int monthDateCheck(int x, int y) throws ParseException
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int month=(c.get(Calendar.MONTH) + 1);
        int date=c.get(Calendar.DATE);
        if(x==month && y==date){
            return 1;
        }
        else
        	return 0;
    }
    
    public static int dateCheck(String x) throws ParseException
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date d1=c.getTime();
        String d2=sdf.format(d1);
        Date date1 = sdf.parse(d2);
        Date date2 = sdf.parse(x);
        
        if(date1.equals(date2)){
            return 1;
        }
        else
        	return 0;
    }
	
	
}
