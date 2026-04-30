package CodePractice;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CodePractice {

	public static void main(String[] args) {

		hashset();
		repeatingElement();
		checkSum();
		frequentNumbers(); // hashmap
		firstUniqueValueFind();
		balancedBrackets();
		missingIntegerSet();
		missingIntegerBoolean();
		tapeEquilibrium();
		permutationCheck();
		countDistinct();
		oddOccurrence();
		oddOccurrenceMap();
		frogJump();
		missingElement();
		maxCounters();
		passingCars();
		maxProfit();
		triangle();
		binaryGap();
		cyclicRotation();
		maxProductOfThree();
		countDiv();
		dominator();
		frogRiverOne();
		genomicRangeQuery();
		solution2(100);
	}

	/*
	 * | Problem | Use | | -------------- | ------- | | Permutation | HashSet | |
	 * Count Distinct | HashSet | | Odd Occurrence | XOR |
	 */
	
	public static int[] solution2(int N) {
	    for (int a = 1; a < N; a++) {
	        int b = N - a;

	        if (a % 10 != 0 && b % 10 != 0) {
	            return new int[] { a, b };
	        }
	    }

	    return new int[] { -1, -1 };
	}
	
	private static int[] genomicRangeQuery() {

	    String S = "CAGCCTA";

	    int[] P = {2, 5, 0};
	    int[] Q = {4, 5, 6};

	    int N = S.length();

	    int[] prefixA = new int[N + 1];
	    int[] prefixC = new int[N + 1];
	    int[] prefixG = new int[N + 1];

	    for (int i = 0; i < N; i++) {

	        prefixA[i + 1] = prefixA[i];
	        prefixC[i + 1] = prefixC[i];
	        prefixG[i + 1] = prefixG[i];

	        char c = S.charAt(i);

	        if (c == 'A') {
	            prefixA[i + 1]++;
	        } else if (c == 'C') {
	            prefixC[i + 1]++;
	        } else if (c == 'G') {
	            prefixG[i + 1]++;
	        }
	    }

	    int[] result = new int[P.length];

	    for (int i = 0; i < P.length; i++) {

	        int start = P[i];
	        int end = Q[i] + 1;

	        if (prefixA[end] - prefixA[start] > 0) {
	            result[i] = 1;
	        } else if (prefixC[end] - prefixC[start] > 0) {
	            result[i] = 2;
	        } else if (prefixG[end] - prefixG[start] > 0) {
	            result[i] = 3;
	        } else {
	            result[i] = 4;
	        }
	    }

	    System.out.println(Arrays.toString(result));
	    return result;
	}
	private static int frogRiverOne() {
	    int X = 5;
	    int[] A = {1, 3, 1, 4, 2, 3, 5, 4};

	    Set<Integer> positions = new HashSet<>();

	    for (int i = 0; i < A.length; i++) {
	        positions.add(A[i]);

	        if (positions.size() == X) {
	            System.out.println("Earliest time: " + i);
	            return i;
	        }
	    }

	    System.out.println("Never crosses");
	    return -1;
	}
	private static int dominator() {
	    int[] A = {3, 4, 3, 2, 3, -1, 3, 3};

	    Map<Integer, Integer> map = new HashMap<>();

	    for (int i = 0; i < A.length; i++) {
	        map.put(A[i], map.getOrDefault(A[i], 0) + 1);

	        if (map.get(A[i]) > A.length / 2) {
	            System.out.println("Dominator value: " + A[i] + " at index: " + i);
	            return i;
	        }
	    }

	    System.out.println("No dominator");
	    return -1;
	}
	private static int countDiv() {
	    int A = 6, B = 11, K = 2;

	    int result = (B / K) - ((A - 1) / K);

	    System.out.println("Count divisible: " + result);
	    return result;
	}
	private static int maxProductOfThree() {

	    int[] A = {-3, 1, 2, -2, 5, 6}; // change test case here

	    Arrays.sort(A);

	    int n = A.length;

	    int option1 = A[n - 1] * A[n - 2] * A[n - 3];
	    int option2 = A[0] * A[1] * A[n - 1];

	    int result = Math.max(option1, option2);

	    System.out.println("Max product of three: " + result);

	    return result;
	}
	
	private static int[] cyclicRotation() {
		
	    int[] A = {3, 8, 9, 7, 6};
	    int K = 3;

	    if (A.length == 0) {
	        return A;
	    }

	    int[] result = new int[A.length];

	    for (int i = 0; i < A.length; i++) {
	        int newIndex = (i + K) % A.length;
	        result[newIndex] = A[i];
	    }

	    System.out.println(Arrays.toString(result));
	    return result;
	}
	
	private static int binaryGap() {
	    int N = 529; // binary: 1000010001

	    String binary = Integer.toBinaryString(N);

	    int maxGap = 0;
	    int currentGap = 0;
	    boolean counting = false;

	    for (char c : binary.toCharArray()) {
	        if (c == '1') {
	            if (counting) {
	                maxGap = Math.max(maxGap, currentGap);
	            }
	            counting = true;
	            currentGap = 0;
	        } else if (counting) {
	            currentGap++;
	        }
	    }

	    System.out.println("Binary: " + binary);
	    System.out.println("Max binary gap: " + maxGap);
	    return maxGap;
	}
	private static int triangle() {

		int[] A = { 10, 2, 5, 1, 8, 20 };

		Arrays.sort(A);

		for (int i = 0; i < A.length - 2; i++) {
			if ((long) A[i] + A[i + 1] > A[i + 2]) {
				System.out.println("Triangle exists ✅");
				return 1;
			}
		}

		return 0;
	}

	private static int maxProfit() {

		int[] A = { 23171, 21011, 21123, 21366, 21013, 21367 };

		int min = A[0];
		int profit = 0;

		for (int i = 1; i < A.length; i++) {
			profit = Math.max(profit, A[i] - min);
			min = Math.min(min, A[i]);
		}

		System.out.println("Max profit: " + profit);
		return profit;
	}

	private static int passingCars() {

		int[] A = { 0, 1, 0, 1, 1 };

		int east = 0;
		int pairs = 0;

		for (int num : A) {
			if (num == 0) {
				east++;
			} else {
				pairs += east;
			}
		}

		System.out.println("Pairs: " + pairs);
		return pairs;
	}

	private static int[] maxCounters() {

		int N = 5;
		int[] A = { 3, 4, 4, 6, 1, 4, 4 };

		int[] counters = new int[N];

		int max = 0;
		int base = 0;

		for (int op : A) {

			if (op >= 1 && op <= N) {

				if (counters[op - 1] < base) {
					counters[op - 1] = base;
				}

				counters[op - 1]++;
				max = Math.max(max, counters[op - 1]);

			} else {
				base = max;
			}
		}

		for (int i = 0; i < N; i++) {
			if (counters[i] < base) {
				counters[i] = base;
			}
		}

		System.out.println(Arrays.toString(counters));
		return counters;
	}

	private static int missingElement() {

		int[] A = { 2, 3, 1, 5 };

		long n = A.length + 1;
		long expected = n * (n + 1) / 2;

		long sum = 0;
		for (int num : A)
			sum += num;

		int result = (int) (expected - sum);

		System.out.println("Missing element: " + result);
		return result;
	}

	private static int frogJump() {

		int X = 10, Y = 85, D = 30;

		int jumps = (Y - X + D - 1) / D;

		System.out.println("Jumps needed: " + jumps);
		return jumps;
	}

	private static int oddOccurrenceMap() {

		int[] A = { 9, 3, 9, 3, 9, 7, 9 };

		Map<Integer, Integer> map = new HashMap<>();

		for (int num : A) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		for (int num : map.keySet()) {
			if (map.get(num) % 2 != 0) {
				System.out.println("Odd occurrence is: " + num);
				return num;
			}
		}

		return -1;
	}

	private static int oddOccurrence() {

		int[] A = { 9, 3, 9, 3, 9, 7, 9 }; // change test case here

		int result = 0;

		for (int num : A) {
			result ^= num;
		}

		System.out.println("Odd occurrence is: " + result);
		return result;
	}

	private static int countDistinct() {

		int[] A = { 1, 2, 2, 3 }; // change test case here

		Set<Integer> set = new HashSet<>();

		for (int num : A) {
			set.add(num);
		}

		System.out.println("Distinct count: " + set.size());
		return set.size();
	}

	private static int permutationCheck() {

		int[] A = { 1, 2, 3, 4 }; // change test case here

		Set<Integer> set = new HashSet<>();

		for (int num : A) {
			set.add(num);
		}

		for (int i = 1; i <= A.length; i++) {
			if (!set.contains(i)) {
				System.out.println("Not a permutation ❌");
				return 0;
			}
		}

		System.out.println("Valid permutation ✅");
		return 1;
	}

	private static int tapeEquilibrium() {

		int[] A = { 3, 1, 2, 4, 3 };

		int totalSum = 0;

		for (int num : A) {
			totalSum += num;
		}

		int leftSum = 0;
		int minDifference = Integer.MAX_VALUE;

		// stop at A.length - 1 because both sides must be non-empty
		for (int i = 0; i < A.length - 1; i++) {

			leftSum += A[i];

			int rightSum = totalSum - leftSum;

			int difference = Math.abs(leftSum - rightSum);

			System.out.println("Split after index " + i + " | leftSum = " + leftSum + " | rightSum = " + rightSum
					+ " | difference = " + difference);

			if (difference < minDifference) {
				minDifference = difference;
			}
		}

		System.out.println("Minimum difference is: " + minDifference);

		return minDifference;
	}

	private static int missingIntegerBoolean() {

		int[] A = { 1, 3, 6, 4, 1, 2 }; // test case

		boolean[] seen = new boolean[A.length + 2];

		// Step 1: mark seen numbers
		for (int num : A) {
			if (num > 0 && num < seen.length) {
				seen[num] = true;
			}
		}

		// Step 2: find first missing
		for (int i = 1; i < seen.length; i++) {
			if (!seen[i]) {
				System.out.println("Missing integer is: " + i);
				return i;
			}
		}

		System.out.println("Fallback result: 1");
		return 1;
	}

	private static int missingIntegerSet() {

		int[] A = { 1, 3, 6, 4, 1, 2 }; // test case

		Set<Integer> set = new HashSet<>();

		// Step 1: store positive numbers
		for (int num : A) {
			if (num > 0) {
				set.add(num);
			}
		}

		// Step 2: find smallest missing
		for (int i = 1; i <= A.length + 1; i++) {
			if (!set.contains(i)) {
				System.out.println("Missing integer is: " + i);
				return i;
			}
		}

		System.out.println("Fallback result: 1");
		return 1;
	}

	private static int balancedBrackets() {

		String S = "{[()()]}"; // test string

		Deque<Character> stack = new ArrayDeque<>();

		for (char c : S.toCharArray()) {

			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					System.out.println("Invalid: closing bracket with no match → " + c);
					return 0;
				}

				char top = stack.pop();

				if (c == ')' && top != '(') {
					System.out.println("Mismatch: expected '(' but found '" + c + "'");
					return 0;
				}
				if (c == ']' && top != '[') {
					System.out.println("Mismatch: expected '[' but found '" + c + "'");
					return 0;
				}
				if (c == '}' && top != '{') {
					System.out.println("Mismatch: expected '{' but found '" + c + "'");
					return 0;
				}
			}
		}

		if (stack.isEmpty()) {
			System.out.println("Valid bracket structure ✅");
			return 1;
		} else {
			System.out.println("Invalid: unmatched opening brackets left → " + stack);
			return 0;
		}

	}

	private static void firstUniqueValueFind() {

		int[] D1 = { 4, 10, 5, 4, 2, 10, 5 }; // expected 2
		int[] D2 = { 6, 6, 7, 7, 8 }; // expected 8
		int[] D3 = { 1, 1, 2, 2 }; // expected -1
		int[] D4 = { -1, 2, -1, 3, 2 }; // expected 3

		System.out.println(firstUniqueValue(D1)); // 2
		System.out.println(firstUniqueValue(D2)); // 8
		System.out.println(firstUniqueValue(D3)); // -1
		System.out.println(firstUniqueValue(D4)); // 3

	}

	private static int firstUniqueValue(int[] d1) {
		Map<Integer, Integer> map = new HashMap<>();

		// Step 1: count frequency
		for (int num : d1) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		// Step 2: find first unique
		for (int num : d1) {
			if (map.get(num) == 1) {
				return num;
			}
		}

		// Step 3: none found
		return -1;
	}

	private static int frequentNumbers() {

		int[] arr = { 1, 2, 2, 3, 3, 3 };

		Map<Integer, Integer> map = new HashMap<>();

		for (int num : arr) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		int mostFrequent = arr[0];
		int highestCount = 0;

		for (int num : map.keySet()) {
			int count = map.get(num);

			if (count > highestCount) {
				highestCount = count;
				mostFrequent = num;
			}
		}

		return mostFrequent;
	}

	private static int checkSum() {
		int[] A = { 1, 4, 5, 3 };
		int K = 7;

		Set<Integer> set = new HashSet<>();

		for (int num : A) {

			int target = K - num;

			if (set.contains(target)) {
				return 1;
			}

			set.add(num);
		}
		return 0;
	}

	private static int repeatingElement() {

		int[] a = { 3, 1, 4, 2, 3 };

		Set<Integer> seen = new HashSet<>();

		for (int num : a) {

			if (seen.contains(num)) {

				return num;
			}

			seen.add(num);
		}

		return -1;
	}

	private static int hashset() {

		Set<Integer> set = new HashSet<>();

		int[] a = { 3, 1, 4, 2, 3 };

		for (int num : a) {

			if (set.contains(num)) {

				return 1;
			}

			set.add(num);
		}
		return 0;
	}

}
