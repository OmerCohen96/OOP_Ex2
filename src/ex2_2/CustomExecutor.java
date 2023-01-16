package ex2_2;

import java.util.ArrayList;
import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {
    private ArrayList<Integer> mp ;
    public CustomExecutor() {
        super(Runtime.getRuntime().availableProcessors() / 2, Runtime.getRuntime().availableProcessors() - 1,
                300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());
        mp = new ArrayList<Integer>(10);
        for (int i = 0; i < 10; i++) {
            mp.add(0);
        }
    }
    
 
    public <T> Future<T> submit(Callable<T> c, TaskType t) {
    	return allSubmit(Task.createTask(c,t));
    }

    public <T> Future<T> submit(Callable<T> C) {

        

       
        return allSubmit(Task.createTask(C));
    }
    public <T> Future<T> submit(Task<T> t) {

        return allSubmit(t) ;
    }
    
    public void dwon() {
        shutdown();
    }
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        Future t1 = (Future) r;
        mp.set(t1.PriorteyOfQ() - 1, mp.get(t1.PriorteyOfQ() - 1) - 1);
    }
    public int getCurrentMax() {
        for (int y = 0; y < mp.size(); y++) {
            if(mp.get(y)>0) {
            	int index = y + 1;
            	return index;
            }
                
        }
        return 10;
    }
    public <T> Future<T> allSubmit(Task<T> task){
        Future<T> otherTask = new Future<>(task);
        mp.set(task.getPriorteyOfQ()-1, mp.get(task.getPriorteyOfQ()-1) + 1);
        execute(otherTask);
        return otherTask;
    }
}