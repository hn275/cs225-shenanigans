/* 
 * CSC 225 - Assignment 3
 * Name: Hal Nguyen
 * Student number: V00972322
 */

/* 
Algorithm analysis goes here.

T(n) = 1 if n = 1
T(n) = n if n & 2 != 0
T(n) = 3T(n/2) + cn otherwise

... math stuff

T(n) is O(3^(logn)) <= O(n^2)
*/


import java.io.*;
import java.util.*;

public class ArrayMatch {
    static private class PairArray {
        int[] arr1;
        int[] arr2;

        PairArray(int[] arr1, int[] arr2) {
            this.arr1 = arr1;
            this.arr2 = arr2;
        }
    }

    static boolean match(int[] a, int[] b){
        if (a.length != b.length) return false;

        // n is odd: n 
        if (a.length % 2 != 0) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) return false; // n, 1 if a.length = 1
            }
            return true;
        }

        // split arrays: n
        PairArray aSplit = split(a); // n/2
        int[] a1 = aSplit.arr1;
        int[] a2 = aSplit.arr2;

        PairArray bSplit = split(b); // n/2
        int[] b1 = bSplit.arr1;
        int[] b2 = bSplit.arr2;

        // get pairs, recursion step: 3T(n/2)
        if (!match(a1, b1)) {
            /*
            boolean a2_b1 = match(a2, b1); // T(n/2)
            boolean a2_b2 = match(a2, b2); // T(n/2)
             */
            

            // return a2_b1 == true && a2_b2 == true;

            if (match(a2, b1)) return match(a2, b2);
            return false;
        }

        if (match(a2, b2)) return true;
        return match(a1, b2);
    }

    static private PairArray split(int[] a) {
        int s = a.length / 2;
        int[] arr1 = new int[s];
        int[] arr2 = new int[s];

        for (int i = 0; i < s; i++) {
            arr1[i] = a[i];
            arr2[i] = a[i + s];
        }

        return new PairArray(arr1, arr2);
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
            try {
                s = new Scanner(new File(args[0]));
            } catch(java.io.FileNotFoundException e){
                System.out.printf("Unable to open %s\n",args[0]);
                return;
            }
            System.out.printf("Reading input values from %s.\n",args[0]);
        } else {
            s = new Scanner(System.in);
            System.out.printf("Reading input values from stdin.\n");
        }     

        int n = s.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for (int j = 0; j < n; j++) {
            a[j] = s.nextInt();
        }

        for (int j = 0; j < n; j++) {
            b[j] = s.nextInt();
        }

        System.out.println((match(a, b) ? "YES" : "NO"));
    }
}
