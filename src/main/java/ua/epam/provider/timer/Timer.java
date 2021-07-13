package ua.epam.provider.timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@WebListener
public class Timer implements ServletContextListener {
        private ScheduledExecutorService scheduler;
        @Override
        public void contextInitialized(ServletContextEvent servletContextEvent) {
                this.scheduler = Executors.newScheduledThreadPool(1);
                this.scheduler.scheduleAtFixedRate(new Task(), 0 , 50, TimeUnit.SECONDS);
        }

        @Override
        public void contextDestroyed(ServletContextEvent servletContextEvent) {

        }
}

