package hueper.codejam2011.candysplitting;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

public class CandySplitting {
	
	private static int[] baseLookup = new int[255];
	
	static {
		baseLookup['Q'] = 1;
		baseLookup['W'] = 2;
		baseLookup['E'] = 4;
		baseLookup['R'] = 8;
		baseLookup['A'] = 16;
		baseLookup['S'] = 32;
		baseLookup['D'] = 64;
		baseLookup['F'] = 128;
	}
	
	public static void parseInput(String filenameIn, String filenameOut) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader(filenameIn));
		PrintWriter w = new PrintWriter(new FileWriter(filenameOut));
		
		if (r.ready()) {
			String numTestCasesLine = r.readLine();
			int numTestCases = Integer.parseInt(numTestCasesLine);
			for (int testCaseIndex = 0; testCaseIndex < numTestCases; testCaseIndex++) {
				// handle each case
				String testCaseLine = r.readLine();
				int numCandies = Integer.parseInt(testCaseLine);
				testCaseLine = r.readLine();
				String[] testCaseInput = testCaseLine.split(" ");
				long[] candies = new long[numCandies];
				for (int candiesIndex = 0; candiesIndex < numCandies; candiesIndex++) {
					candies[candiesIndex] = Long.parseLong(testCaseInput[candiesIndex]);
				}
				
				String result = "NO";
				
				/* it is only possible, if Patricks sum of all candies == 0 */
				long sumPatrick = calculateSumPatrick(candies);
				if (sumPatrick == 0) {
					// then the optimum distribution is all candies go to Sean, except the lowest one
					Arrays.sort(candies);
					result = Long.toString(calculateSumSean(candies) - candies[0]);
				}
				
				// ... and output result
				String outString = "Case #" + (testCaseIndex + 1) + ": ";
				//debug:
				/*
				outString += "N: " + numCandies + ", C: [ ";
				for (int candyIndex = 0; candyIndex < candies.length; candyIndex++) {
					outString += candies[candyIndex];
					if (candyIndex < candies.length - 1) {
						outString += ", ";
					}
				}
				outString += " ], result: ";
				*/
				
				outString += result;
				System.out.println(outString);
				w.println(outString);
			}
		}
		w.flush();
	}

	/* the sum of Patricks pile, as calculated by Patrick */
	private static long calculateSumPatrick(long[] candies) {
		// Patricks "adding" is actually just an xor
		long rval = 0;
		for (int i = 0; i < candies.length; i++) {
			rval ^= candies[i];
		}
		return rval;
	}

	/* the sum of Seans pile, as calculated by Sean */
	private static long calculateSumSean(long[] candies) {
		long rval = 0;
		for (int i = 0; i < candies.length; i++) {
			rval += candies[i];
		}
		return rval;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		CandySplitting.parseInput(args[0], args[1]);
	}

}
