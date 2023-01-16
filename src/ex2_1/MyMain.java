package ex2_1;

import java.io.*;


public class MyMain {

    public static void delete_from_main(int n) {
        String to[] = new String[n];

        File s;
        for (int i = 0; i < n; i++) {
            s = new File("file_" + i + ".txt");
            s.delete();
        }
    }

    public  static String[] generate_Text_File_Array (int n){
        String [] list = new String[n];
        for (int i = 0 ; i < n ; i++){
            list[i] = "file_"+i+".txt";
        }

        return list;
     }

    public static void main(String[] args) throws IOException {



        int n = 500;

        String[] s = generate_Text_File_Array(n);
//
        Ex2.Ex2_1 test2 = new Ex2.Ex2_1();
//
//
////        delete_from_main(1000);

//        String[] s = Ex2.Ex2_1.createTextFiles(n, (int)(System.currentTimeMillis()/100000) , 3500000);


// ---------------------------------------------------------------------------------------------------------------------

        long one = System.currentTimeMillis();

        int x = Ex2.Ex2_1.getNumOfLines(s);

        long two = System.currentTimeMillis();

        System.out.println("[NO THREADS] \nlines: "+x+" time: "+((two-one)/1000.0)+" seconds");



// ---------------------------------------------------------------------------------------------------------------------

        long one1 = System.currentTimeMillis();

        int y = test2.getNumOfLinesThreads(s);

        long two1 = System.currentTimeMillis();

        System.out.println("[USE THREADS] \nlines: "+y+" time: "+((two1-one1)/1000.0)+" seconds");

// ---------------------------------------------------------------------------------------------------------------------

//        long one2 = System.currentTimeMillis();
//
//        Long d = test2.getNumOfLinesThreads3(s);
//
//        long two2 = System.currentTimeMillis();
//
//        System.out.println("getNumOfLinesThreads3 --------------- lines: "+d+"-----------------\n"+(double)((two2-one2)/1000.0)+" seconds");

// ---------------------------------------------------------------------------------------------------------------------

//        long one3 = System.currentTimeMillis();
//
//        Long p = test2.getNumOfLinesThreads4(s);
//
//        long two3 = System.currentTimeMillis();
//
//        System.out.println("getNumOfLinesThreads4 ----------------lines: "+p+"-----------------\ntime: "+(double)((two3-one3)/1000.0)+" seconds");
//
//// ---------------------------------------------------------------------------------------------------------------------

        long one4 = System.currentTimeMillis();

        int z = test2.getNumOfLinesThreadPool(s);

        long two4 = System.currentTimeMillis();

        System.out.println("[THREAD_POOL] \nlines: "+z+" time: "+((two4-one4)/1000.0)+" seconds");
// ---------------------------------------------------------------------------------------------------------------------

//      delete_from_main(n);

    }
}