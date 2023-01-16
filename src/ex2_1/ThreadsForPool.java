package ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadsForPool implements Callable<Object> {

    private static AtomicLong total_lines = new AtomicLong();

    private String fileName;

    public ThreadsForPool (String fileName) {
        if (fileName == null) throw new NullPointerException("insert valid file name");

        this.fileName = fileName;
    }

    public static AtomicLong getTotal_lines (){
        return total_lines;
    }

    @Override
    public Object call() throws Exception {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while((bufferedReader.readLine()!=null)) {
                total_lines.getAndIncrement();
            }

            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
