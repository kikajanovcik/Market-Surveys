package io.kikajanovcik.maketsurveys;

import io.kikajanovcik.maketsurveys.entities.Request;
import io.kikajanovcik.maketsurveys.repositories.SurveyRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyService {

    @Autowired private SurveyRepository surveys;
    private static final Logger log = LogManager.getLogger("[LOG]");
    private static final Logger schedulingLogger = LogManager.getLogger(log.getName() + ".[scheduling]");

    private final ThreadPoolTaskScheduler taskExecutor;

    public static final String DAILY = "0 0 12 1/1 * ? *";
    public static final String WEEKLY = "0 0 12 ? * MON *";
    public static final String MONTHLY = "0 0 12 1 1/1 ? *";
    public static final CronTrigger MONTHLYY = new CronTrigger("0 0 12 1 1/1 ? *");

    public SurveyService() {

        log.info("Initializing executor");

        taskExecutor = new ThreadPoolTaskScheduler();
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setErrorHandler(t -> schedulingLogger.error("Unknown error occurred while executing task.", t));
        //taskExecutor.setRejectedExecutionHandler((r, e) -> schedulingLogger.error("Execution of task {} was rejected for unknown reasons.", r));
        taskExecutor.initialize();
    }

    public void subscribe(Request request) {

        System.out.println("starting task");

        request.setResponse(getSurveys(request.getQuery()));

        //taskExecutor.scheduleWithFixedDelay(request, 10);
        taskExecutor.schedule(request, new CronTrigger(MONTHLY));
    }

    private List<Object> getSurveys(Object query) {



        return surveys.findAll().stream().collect(Collectors.toList());
    }

    private void sendSurveys(List<Object> surveys) {
    }
}
