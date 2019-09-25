import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PrimeTester {
	static int[] primeNumbersTo1000 = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
			79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191,
			193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311,
			313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439,
			443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577,
			587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709,
			719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857,
			859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997 };

	public static void main(String[] args) {
		testA(primeNumbersTo1000);
		testB(primeNumbersTo1000, 3);
		testC(primeNumbersTo1000);
		testD(primeNumbersTo1000);
	}

	public static void testA(int[] primes) {
		// Difference Between Primes
		String[] strings = new String[primes.length];
		int iterator = 0;
		int[] workingArrayOld = new int[primes.length];
		int[] workingArray = new int[primes.length - 1];

		for (int i = 0; i < primes.length; i++) {
			workingArrayOld[i] = primes[i];
			strings[i] = String.valueOf(primes[i]);
		}

		for (int j = primes.length - 1; j > 0; j--) {
			iterator++;
			workingArray = new int[primes.length - iterator];
			for (int i = 0; i < workingArray.length; i++) {
				workingArray[i] = workingArrayOld[i] - workingArrayOld[i + 1];

			}

			for (int i = iterator; i < strings.length; i++) {
				strings[i] += "\t" + String.valueOf(workingArray[i - iterator]);
			}

			workingArrayOld = new int[primes.length - iterator];
			workingArrayOld = workingArray;
		}
		printToFile(strings, "Prime Test A", "PrimeDataA.txt");
	}

	public static void testB(int[] primes, int iterations) {
		// Each digit manipulated
		// +
		String[] strings = new String[primes.length];
		int[] addData = new int[primes.length];

		for (int n = 0; n < primes.length; n++) {
			strings[n] = String.valueOf(primes[n]);
			addData[n] = primes[n];
		}

		for (int x = 0; x < iterations; x++) {

			for (int j = 0; j < primes.length; j++) {
				int testAdd = 0;
				for (int i = 0; i < String.valueOf(addData[j]).length(); i++) {
					testAdd += Integer.parseInt(String.valueOf(String.valueOf(addData[j]).charAt(i)));
				}
				strings[j] += "\t" + testAdd;
				addData[j] = testAdd;
			}
		}

		printToFile(strings, "Prime Test B", "PrimeDataB.txt");

	}

	public static void testC(int[] primes) {
		// Each digit manipulated
		// *
		String[] strings = new String[primes.length];
		int[] multData = new int[primes.length];

		for (int j = 0; j < primes.length; j++) {
			int testMult = 1;
			for (int i = 0; i < String.valueOf(primes[j]).length(); i++) {
				testMult *= Integer.parseInt(String.valueOf(String.valueOf(primes[j]).charAt(i)));
			}
			strings[j] = primes[j] + "\t" + testMult;
			multData[j] = testMult;
		}

		printToFile(strings, "Prime Test C", "PrimeDataC.txt");

	}

	public static void testD(int[] primes) {
		String[] strings = new String[primes.length];
		int[] squareData = new int[primes.length];
		int[] counter = new int[primes.length];
		int it = 0;

		for (int n = 0; n < primes.length; n++) {
			strings[n] = String.valueOf(primes[n]);
		}

		for (int j = 0; j < primes.length; j++) {
			if (j != primes.length - 1)
				squareData[j] = (primes[j + 1] * primes[j + 1]) - (primes[j] * primes[j]);
			strings[j] += "\t" + String.valueOf(primes[j] * primes[j]);
		}

		for (int j = 1; j < primes.length; j++) {

			if (strings[j].charAt(strings[j].length() - 1) == '9' || strings[j].charAt(strings[j].length() - 1) == '1') {
				if(strings[j].charAt(strings[j].length() - 1) != strings[j-1].charAt(strings[j-1].length() - 1)) it++;
				
				if (strings[j].charAt(strings[j].length() - 1) == '1') {
					counter[it]++;
				} else {
					counter[it]++;
				}
			}

		}

		for (int j = 1; j < primes.length; j++) {
			strings[j] += "\t" + String.valueOf(squareData[j]) + "\t" + String.valueOf(counter[j-1]);
		}

		printToFile(strings, "Prime Test D", "PrimeDataD.txt");

	}

	public static void printToFile(String[] strings, String testName, String fileName) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {

			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			for (String s : strings) {
				bw.write(s + "\n");
			}
			System.out.println(testName + " Complete");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			try {

				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
