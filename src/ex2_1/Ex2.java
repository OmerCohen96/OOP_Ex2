package ex2_1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

        private static long linesCounter(BufferedReader file){
            long lines = 0;

            try {
                while (file.readLine()!=null){
                    lines++;
                }
            }catch (IOException o){
                throw new RuntimeException();
            }

            return lines;

        }

        public static long getNumOfLines(String[] fileNames) {

            BufferedReader bf;
            long numberOfFiles = fileNames.length;
            long total_lines = 0;
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

        public long getNumOfLinesThreads1(String[] fileNames) {

            AtomicLong total_lines = new AtomicLong();

            for (String names  : fileNames){
                CounterThread current = new CounterThread(names);
                current.start();

                try {
                    current.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                total_lines.addAndGet(current.getCounter());
            }

            return total_lines.get();
        }

        public Long getNumOfLinesThreads2(String[] fileNames) {

            AtomicLong total_lines = new AtomicLong();

            List<CounterThread> list = new ArrayList<>();

            int n = fileNames.length;

            for (String names : fileNames){
                list.add(new CounterThread(names));
            }

            for (CounterThread current : list){
                current.start();
                try {
                    current.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            for (CounterThread current : list){
                total_lines.addAndGet(current.getCounter());
            }



            return total_lines.get();
        }

        public Long getNumOfLinesThreads3(String[] fileNames){

            AtomicLong total_lines = new AtomicLong();

            List<CounterThread> threads = new ArrayList<>();

            CounterThread t;

            for (String file : fileNames){
                t = new CounterThread(file);
                t.start();
                threads.add(t);
            }

            ListIterator<CounterThread> prev = threads.listIterator(threads.size());

            while (prev.hasPrevious()){
                try {
                    prev.previous().join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            for (CounterThread curr : threads){
                total_lines.addAndGet(curr.getCounter());
            }



            return total_lines.get();


        }

        public Long getNumOfLinesThreads4(String[] fileNames){

            AtomicLong total_lines = new AtomicLong();

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

        public long getNumOfLinesThreadPool(String[] fileNames){

            int length = fileNames.length;

            ExecutorService pool = Executors.newFixedThreadPool(length);



            for (String name : fileNames){
                pool.submit(new ThreadsForPool(name));
            }

            pool.shutdown();

            return ThreadsForPool.getTotal_lines().get();

        }



    }



}






