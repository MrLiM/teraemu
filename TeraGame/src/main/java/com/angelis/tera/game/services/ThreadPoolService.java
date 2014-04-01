package com.angelis.tera.game.services;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javolution.util.FastMap;

import org.apache.log4j.Logger;

import com.angelis.tera.game.tasks.AbstractTask;
import com.angelis.tera.game.tasks.TaskTypeEnum;

public class ThreadPoolService extends AbstractService {

    /** INSTANCE */
    private static ThreadPoolService instance;
    
    /** LOGGER */
    private static Logger log = Logger.getLogger(ThreadPoolService.class.getName());
    
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private Map<AbstractTask<?>, ScheduledFuture<?>> tasks = Collections.synchronizedMap(new FastMap<AbstractTask<?>, ScheduledFuture<?>>());

    private ThreadPoolService() {
    }

    @Override
    public void onInit() {
        log.info("ThreadPoolService started");
    }

    @Override
    public void onDestroy() {}

    public void addTask(final AbstractTask<?> task) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        };
        
        ScheduledFuture<?> schedule = executor.schedule(runnable, task.getDelay(), TimeUnit.SECONDS);
        tasks.put(task, schedule);
    }

    public void cancelTask(Object linkedObject, TaskTypeEnum taskType) {
        final Iterator<Entry<AbstractTask<?>, ScheduledFuture<?>>> iterator = this.tasks.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<AbstractTask<?>, ScheduledFuture<?>> entry = iterator.next();
            if (entry.getKey().getLinkedObject().equals(linkedObject) && entry.getKey().getTaskType() == taskType) {
                entry.getValue().cancel(true);
                iterator.remove();
            }
        }
    }
    
    public void cancelAllTasksByLinkedObject(Object linkedObject) {
        final Iterator<Entry<AbstractTask<?>, ScheduledFuture<?>>> iterator = this.tasks.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<AbstractTask<?>, ScheduledFuture<?>> entry = iterator.next();
            if (entry.getKey().getLinkedObject().equals(linkedObject)) {
                entry.getValue().cancel(true);
                iterator.remove();
            }
        }
    }
    
    /** SINGLETON */
    public static ThreadPoolService getInstance() {
        if (instance == null) {
            instance = new ThreadPoolService();
        }
        return instance;
    }
}
