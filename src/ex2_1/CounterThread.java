package ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CounterThread extends Thread {

    private final String fileName;

    private AtomicInteger counter;

    public CounterThread (String fileName){

        if (fileName == null) throw new NullPointerException("insert valid file name");

        this.fileName = fileName;
        counter = new AtomicInteger();
    }

    public int getCounter (){
        return counter.get();
    }


    @Override
    public void run (){

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while((bufferedReader.readLine()!=null)) {
                counter.getAndIncrement();
            }

            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
