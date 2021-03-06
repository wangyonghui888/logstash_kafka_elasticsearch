package com.friday.thread.dispatcher.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.friday.thread.TaskSource;
import com.friday.thread.dispatcher.TaskDispatch;
import com.friday.thread.task.AlertTask;
import com.friday.thread.task.DatabaseOpTask;
import com.friday.thread.task.PreLogicTask;

public class SyncTaskDispatch implements TaskDispatch {
    private static final Logger LOG = LoggerFactory.getLogger(SyncTaskDispatch.class);
   
    public void dispatchTask(TaskSource taskSrc) throws RuntimeException {
        switch (taskSrc.getTaskType()) {
        case PreLogicTask:
            runPreLogicTask(taskSrc);
            break;
        case AlertTask:
            runAlertTask(taskSrc);
            break;
        case DbOpTask:
            runDbOpTask(taskSrc);
            break;
        case DelayValueNotifyTask:
        		LOG.warn("No implementation yet.");
            break;
        }
        LOG.info(String.format("Dispatched task successfully, taskType [%s]", taskSrc.getTaskType().toString()));
    }

    public void runPreLogicTask(TaskSource taskSrc) {
        new PreLogicTask(taskSrc).run();
    }

    public void runAlertTask(TaskSource taskSrc) {
        new AlertTask(taskSrc).run();
    }

    public void runDbOpTask(TaskSource taskSrc) {
        new DatabaseOpTask(taskSrc).run();
    }
}