package com.angelis.tera.game.tasks;

public abstract class AbstractTask<T> {

    protected final T linkedObject;
    protected final TaskTypeEnum taskType;
    protected final int delay;
    protected final boolean repeat = false;

    public AbstractTask(T linkedObject, TaskTypeEnum taskType, int delay) {
        this.linkedObject = linkedObject;
        this.taskType = taskType;
        this.delay = delay;
    }

    public T getLinkedObject() {
        return linkedObject;
    }

    public TaskTypeEnum getTaskType() {
        return taskType;
    }

    public int getDelay() {
        return delay;
    }
    
    public abstract void execute();
}
