/* PairSum225.java
   CSC 225 - SPRING 2023

   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java PairSum input_file
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;

public class PairSum{
	/* PairSum225()
		The input array A will contain non-negative integers. The function
		will search the input array A for two elements which sum to 225.
		If such a pair is found, return true. Otherwise, return false.
		The function may modify the array A.
	*/
	public static boolean PairSum225(int[] A) {
		int target = 225;
		int[] buf = new int[target + 1];

		for (int i = 0; i < A.length; i++) {
			int compliment = target - A[i];
			if (compliment < 0 || compliment > 255) continue;
			if (buf[compliment] == 1) return true;
			buf[A[i]] = 1;
		}

		return false;
	}

	/* main()
	   Contains code to test the PairSum225 function.
	*/
	public static void main(String[] args){
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
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		Vector<Integer> inputVector = new Vector<Integer>();

		int v;
		while(s.hasNextInt() && (v = s.nextInt()) >= 0)
		inputVector.add(v);

		int[] array = new int[inputVector.size()];

		for (int i = 0; i < array.length; i++)
		array[i] = inputVector.get(i);

		System.out.printf("Read %d values.\n",array.length);


		long startTime = System.currentTimeMillis();

		boolean pairExists = PairSum225(array);

		long endTime = System.currentTimeMillis();

		double totalTimeSeconds = (endTime-startTime)/1000.0;

		System.out.printf("Array %s a pair of values which add to 225.\n",pairExists? "contains":"does not contain");
		System.out.printf("Total Time (seconds): %.4f\n",totalTimeSeconds);
	}
}
