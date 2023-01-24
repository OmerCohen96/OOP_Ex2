package ex2_1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Ex2 {

    public static class Ex2_1 {

        public static void delete (String[] files){
            File delete;

            try {

                for (String s : files) {
                    delete = new File(s);
                    delete.delete();
                }
            } catch (Exception o){
                throw new RuntimeException();
            }
        }

        public static String[] createTextFiles(int n, int seed, int bound) {

            String filesNames[] = new String[n];

            String text_massage = "you are the best checker in the world";

            Random random = new Random(seed);

            try {

                PrintWriter printFile;

                for (int i = 0; i < n; i++) {

                    String fileName = "file_" + i + ".txt";

                    printFile = new PrintWriter(fileName);

                    int x = random.nextInt(bound);

                    for (int j = 0; j < x; j++) {

                        printFile.println(text_massage);
                    }
                    printFile.close();

                    filesNames[i] = fileName;
                }


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            return filesNames;
        }

        private static int linesCounter(BufferedReader file){
            int lines = 0;

            try {
                while (file.readLine()!=null){
                    lines++;
                }
            }catch (IOException o){
                throw new RuntimeException();
            }

            return lines;

        }

        public static int getNumOfLines(String[] fileNames) {

            BufferedReader bf;
            int numberOfFiles = fileNames.length;
            int total_lines = 0;
            String name = "";

            for (String fileName : fileNames) {
                name = fileName;
                try {
                    bf = new BufferedReader(new FileReader(name));
                    total_lines += linesCounter(bf);

                } catch (FileNotFoundException e) {

                    throw new RuntimeException(e);
                }

            }
            return total_lines;
        }
        public int getNumOfLinesThreads(String[] fileNames){

            AtomicInteger total_lines = new AtomicInteger();

            List<CounterThread> threads = new ArrayList<>();

            CounterThread t;

            for (String file : fileNames){
                t = new CounterThread(file);
                t.start();
                threads.add(t);
            }

            for (CounterThread curr : threads) {

                try {
                    curr.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }


            for (CounterThread curr : threads){
                total_lines.addAndGet(curr.getCounter());
            }



            return total_lines.get();


        }
        public int getNumOfLinesThreadPool(String[] fileNames){

            int length = fileNames.length;

            ExecutorService pool = Executors.newFixedThreadPool(length);

            List<Future<AtomicInteger>> allTasks = new ArrayList<>();

            for (int i = 0 ; i < length ; i++){
                Future<AtomicInteger> curr = pool.submit(new ThreadsForPoolInteger(fileNames[i]));
                allTasks.add(curr);
            }

            pool.shutdown();

            AtomicInteger num = new AtomicInteger();

            for (Future<AtomicInteger> result : allTasks) {
                try {
                    num.addAndGet(result.get().get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }


            return num.get();
        }



    }



}






