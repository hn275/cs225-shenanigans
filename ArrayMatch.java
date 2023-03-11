/* 
 * CSC 225 - Assignment 3
 * Name: 
 * Student number:
 */

/* 
Algorithm analysis goes here.
*/


import java.io.*;
import java.util.*;

public class ArrayMatch {
    static private class PairArray {
        int[] arr_1;
        int[] arr_2;

        PairArray(int[] arr_1, int[] arr_2) {
            this.arr_1 = arr_1;
            this.arr_2 = arr_2;
        }
    }

    static boolean match(int[] a, int[] b){
        if (a.length != b.length) return false;

        // n is odd: n 
        if (a.length % 2 != 0) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) return false;
            }
            return true;
        }

        // split arrays: 2n
        PairArray a_split = split(a);
        int[] a_1 = a_split.arr_1;
        int[] a_2 = a_split.arr_2;

        PairArray b_split = split(b);
        int[] b_1 = b_split.arr_1;
        int[] b_2 = b_split.arr_2;

        // get pairs, recursion step: 4T(n/2)
        boolean a1_b1 = match(a_1, b_1);
        boolean a2_b2 = match(a_2, b_2);
        boolean a1_b2 = match(a_1, b_2);
        boolean a2_b1 = match(a_2, b_1);

        // match checking: 3 - const ignore
        boolean[] results = {
            two_match(a1_b1, a2_b2),
            two_match(a1_b1, a1_b2),
            two_match(a2_b1, a2_b2)
        };

        for (boolean s : results) {
            if (s) return true;
        }

        return false;
    }

    static private boolean two_match(boolean cond_1, boolean cond_2) {
        if (!cond_1 || !cond_2) return false;
        return true;
    }

    static private PairArray split(int[] a) {
        int size = a.length;
        int[] a_1 = new int[size/2];
        int[] a_2 = new int[size/2];

        for (int i = 0; i < size/2; i++) {
            a_1[i] = a[i];
        }

        for (int i = size/2; i < size; i++) {
            a_2[i-size/2] = a[i];
        }

        return new PairArray(a_1, a_2);
    }


    public static void main(String[] args) {
        /* Read input from STDIN. Print output to STDOUT. Your class should be named ArrayMatch. 

    You should be able to compile your program with the command:

        javac ArrayMatch.java

    To conveniently test your algorithm, you can run your solution with any of the tester input files using:

        java ArrayMatch inputXX.txt

    where XX is 00, 01, ..., 13.
        */

        Scanner s;
    if (args.length > 0){
            try{
                s = new Scanner(new File(args[0]));
            } catch(java.io.FileNotFoundException e){
                System.out.printf("Unable to open %s\n",args[0]);
                return;
            }
            System.out.printf("Reading input values from %s.\n",args[0]);
        }else{
            s = new Scanner(System.in);
            System.out.printf("Reading input values from stdin.\n");
        }     

        int n = s.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for(int j = 0; j < n; j++){
            a[j] = s.nextInt();
        }

        for(int j = 0; j < n; j++){
            b[j] = s.nextInt();
        }

        System.out.println((match(a, b) ? "YES" : "NO"));
    }
}
