package ex2_2;
import java.util.concurrent.FutureTask;

public class Future<T> extends FutureTask<T> implements Comparable<Future<T>> {
    private Task<T> T;
    private int PriorteyOfQ;
    public Future(Task<T> tk) {
        super(tk);
        this.T = tk;
        this.PriorteyOfQ = tk.PriorteyOfQ;
    }
    @Override
    public int compareTo(Future<T> other){
        return Integer.compare(T.getPriorteyOfQ(),other.getTask().getPriorteyOfQ());
    }
    public Task<T> getTask(){
        return T;
    }
    public int PriorteyOfQ()
    {
        return this.PriorteyOfQ;
    }
    
}