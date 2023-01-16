package ex2_2;

import java.util.concurrent.Callable;

public class Task<T> implements Callable<T>{
    private Callable<T> theTask;
    public int PriorteyOfQ;
    public TaskType type;
    
    public Callable<T> getTheTask() {
		return theTask;
	}
	public void setTheTask(Callable<T> theTask) {
		this.theTask = theTask;
	}
	public int getPriorteyOfQ() {
		return PriorteyOfQ;
	}
	public void setPriorteyOfQ(int priorteyOfQ) {
		PriorteyOfQ = priorteyOfQ;
	}
	public TaskType getType() {
		return type;
	}
	public void setType(TaskType type) {
		this.type = type;
	}
	public Task(Callable<T> theTask,TaskType type)
    {
        this.theTask = theTask;
        this.PriorteyOfQ = type.getPriorityValue();
        this.type = type;
        
    }
	public int compareTo(Task<T> e) {
        if(e.PriorteyOfQ == this.PriorteyOfQ){
            return 0;
        }
        else if (e.PriorteyOfQ < this.PriorteyOfQ){
            return 1;
        }
        else return -1;
    }
    @Override
	public String toString() {
		return "Task [theTask=" + theTask + ", PriorteyOfQ=" + PriorteyOfQ + ", type=" + type + "]";
	}
	public Task(Callable<T> theTask)
    {
        this.theTask = theTask;
        this.PriorteyOfQ = 3;
    }
    @Override
    public T call() throws Exception {
        return this.theTask.call();
    }
    public static Task createTask(Callable Taskrn ,TaskType t)
    {
        return new Task(Taskrn,t);
    }
    public static Task createTask(Callable Taskrn)
    {
        return new Task(Taskrn);
    }

    



}
