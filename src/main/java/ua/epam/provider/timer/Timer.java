package ua.epam.provider.timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class Timer implements ServletContextListener
         {
        private ScheduledExecutorService scheduler;
        @Override
        public void contextInitialized(ServletContextEvent servletContextEvent) {
                ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault().normalized());
                ZonedDateTime nextRun = now.withYear(2021).withMonth(7).withDayOfMonth(27)
                        .withHour(11).withMinute(00).withSecond(0);
                Duration duration = Duration.between(now, nextRun);
                long initialDelay = duration.getSeconds();
                this.scheduler = Executors.newScheduledThreadPool(1);
                this.scheduler.scheduleAtFixedRate(new Task(), 1 , 5, TimeUnit.MINUTES);
        }

        @Override
        public void contextDestroyed(ServletContextEvent servletContextEvent) {

        }
}

