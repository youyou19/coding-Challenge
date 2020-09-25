import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Class containing solutions for various Hackerrank/GeeksForGeeks coding
 * challenges
 *
 * @author Wilfrid Jules
 */
public class Solution {

    public static void main(String[] args) throws Exception {
        hackerRankStdInput();
    }

    /**
     * A dummy method to accept input from stdin in a format most commonly used
     * in challenge questions at Hackerrank
     *
     * @throws IOException
     */
    private static void hackerRankStdInput() throws IOException {

        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();
        System.out.println("Test cases are " + testCases);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // For each test case do following
        for (int j = 0; j < testCases; j++) {
            String str = br.readLine();
            // Get the length of the array and jump position index
            String[] splits = str.split(" ");

            int arrLen = Integer.parseInt(splits[0]);
            int jumpPos = Integer.parseInt(splits[1]);

            // Create an array and fill the elements from the line
            int[] arr = new int[arrLen];

            String arrLine = br.readLine();
            String[] elements = arrLine.split(" ");
            for (int i = 0; i < elements.length; i++) {
                arr[i] = Integer.parseInt(elements[i]);
            }

            // Solve the challenge for the particular problem
            // solveChallenge();

            // print the result
            System.out.println("Result");
            for (int eachObj : arr) {
                System.out.print(" " + eachObj);
            }
            System.out.println(" MOve position " + jumpPos);

            // continue
        }
    }

    /**
     * Find largest possible difference between any two elements within an array
     *
     * @param arr
     * @return
     */
    private static int findMaxDiff(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min)
                min = arr[i];

            if (arr[i] > max)
                max = arr[i];
        }
        return (max - min);
    }

    /**
     * Compute the largest possible difference between any two elements within
     * an array such that larger element always appears after the smaller
     * element.
     *
     * @param arr
     */
    private static int computeMaxDiff(int[] arr) {
        int diff, maxDiff, min = Integer.MAX_VALUE;
        diff = maxDiff = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min)
                min = arr[i];

            // diff = Math.abs(arr[i]) - Math.abs(min);
            diff = arr[i] - min;
            if (diff > maxDiff)
                maxDiff = diff;
        }
        return maxDiff;
    }

    /**
     * @param a1
     * @param a2
     */
    private static void merge2sortedArrays(int[] a1, int[] a2) {
        // int m = a1.length;
        // int n = a2.length;
        int[] A = { 4, 0, 6, 0, 8, 0 };
        int m = 3;
        int[] B = { 5, 7, 9 };
        int n = 3;

        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            A[k--] = (A[i] > B[j]) ? A[i--] : B[j--];
            // if(A[i] > B[j])
            // A[k--] = A[i--];
            // else
            // A[k--] = B[j--];
        }
        while (j >= 0)
            A[k--] = B[j--];

    }

    /**
     * You are playing a game on your cell phone. You are given an array of
     * length n, indexed from 0 to n-1. Each element of the array is either 0 or
     * 1. You can only move to an index which contains 0. At first, you are at
     * the 0th position. In each move you can do one of the following things:
     * <br>
     * <br>
     * Walk one step forward or backward. <br>
     * Make a jump of exactly m length forward. <br>
     * <br>
     * That means you can move from position x to x+1,x-1 or x+m in one move,
     * but at least one of the following conditions must be true: </br>
     * <br>
     * The new position contains 0. <br>
     * The new position is greater than n-1.<br>
     * <br>
     * You can't move backward from position . If you move to any position
     * greater than n-1, you win the game.
     *
     * @param m
     *            Jump length
     * @param arr
     *            Input array
     * @param i
     *            Starting position
     * @return
     */
    @SuppressWarnings("unused")
    private static boolean isSolvable(int[] array, int m, int i) {

        /* Base Cases */
        if (i < 0 || array[i] == 1) {
            return false;
        } else if (i + 1 >= array.length || i + m >= array.length) {
            return true;
        }

        array[i] = 1; // marks as visited

        /* Recursive Cases */
        return isSolvable(array, m, i - 1) || isSolvable(array, m, i + 1) || isSolvable(array, m, i + m);
    }

    /**
     * Given an array of positive integers. All numbers occur even number of
     * times except one number which occurs odd number of times. Find the number
     * in O(n) time & constant space
     *
     * @param arr
     * @return
     */
    private static int getOddOccurringElement(int[] arr) {
        int result = 0;
        // int res[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            // int num = arr[i];
            result ^= arr[i];
            // res[i] = result;
        }
        return result;
    }

    /**
     * Find all the palindromes from a given string. Given a string, find all
     * possible palindromic partitions of given string. Ex. "abba" --> bb, abba,
     * a, b
     *
     * @param input
     * @return no of combinations of all the palindrome substrings from a given
     *         string.
     */
    public static int palindromePermutations(final String str) {
        Set<String> palindromeOutputs = new HashSet<String>();
        int len = str.length();
        int i = 0;
        char[] charArr = new char[str.length()];
        charArr = str.toCharArray();
        for (i = 0; i < len; i++) {
            // Check with keeping current char at center
            if (i > 0)
                palindromeOutputs.addAll(centerCombinations(str, i));
            if (i < (len - 1))
                palindromeOutputs.addAll(rightCombinations(str, i));
        }

        for (char c : charArr) {
            palindromeOutputs.add(Character.toString(c));
        }

        return palindromeOutputs.size();
    }

    public static List<String> centerCombinations(String string, int center) {
        List<String> result = new ArrayList<>();

        while (((center - 1) >= 0) && ((center + 1) < string.length())
                && (string.charAt(center - 1) == string.charAt(center + 1))) {
            result.add(string.substring(center - 1, center + 2));
            center++;
        }
        return result;
    }

    public static List<String> rightCombinations(String string, int right) {
        List<String> result = new ArrayList<>();

        while (((right + 1) < string.length()) && (string.charAt(right) == string.charAt(right + 1))) {
            result.add(string.substring(right, right + 2));
            right++;
        }
        return result;
    }

    /**
     * Given an array with elements, can you sort this array in ascending order
     * using only one of the following operations?
     *
     * Swap two elements OR Reverse one sub-segment.
     *
     * @param arr
     * @param size
     */
    private static String almostSorted(int[] a, int size) {
        int i, j;
        List<Integer> unsortedList = new ArrayList<>();
        i = j = 0;
        int temp;
        int sorted = 0;
        int unSorted = 0;
        int[] unSortArr = new int[size];
        int broken = 0;
        int count = 0;
        while (i < size) {
            while (a[i] < a[i + 1]) {
                sorted++;
                i++;
            }
            j = i;
            while (a[i] > a[i + 1]) {
                unSorted++;
                unSortArr[i] = a[i];
                unsortedList.add(a[i]);
                if (i >= (size - 2))
                    break;
                else
                    i++;
            }
            // if (unSorted > 0)
            // unSorted++;
            while (((j + 1) != (i - 1)) && (unSorted > 0)) {
                if (a[j] > a[i]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                    unSorted -= 2;
                }
                j++;
                i--;
            }
            if (!unsortedList.isEmpty())
                unsortedList.add(a[i]);
            broken++;
            Collections.reverse(unsortedList);
            // for (int k = 0; k<size-1; k++) {
            // if (a[k] < a[k+1])
            // count++;
            // }
            int total = sorted + unSorted;
            if (size == count)
                return "YES";
        }
        return "NO";
    }

    /**
     * A Discrete Mathematics professor has a class of students. Frustrated with
     * their lack of discipline, he decides to cancel class if fewer than K
     * students are present when class starts.
     *
     * Given the arrival time of each student, determine if the class is
     * canceled.
     *
     * @param students
     * @param threshold
     */
    private static String angryProf(int[] students, int threshold) {
        List<Integer> earlyStudents = new ArrayList<>();
        List<Integer> lateStudents = new ArrayList<>();

        for (int i : students) {
            if (i > 0)
                lateStudents.add(i);
            else
                earlyStudents.add(i);
        }

        if (earlyStudents.size() < threshold)
            return "YES";
        return "NO";
    }

    /**
     * The Utopian Tree goes through 2 cycles of growth every year. Each spring,
     * it doubles in height. Each summer, its height increases by 1 meter.
     *
     * Laura plants a Utopian Tree sapling with a height of 1 meter at the onset
     * of spring. How tall will her tree be after growth cycles?
     *
     * @param cycles
     */
    private static int utopianTree(int cycles) {
        int height = 1;
        // Laura plants a Utopian Tree sapling with a height of 1 meter at the
        // onset of Spring. So, the initial height () of the tree remains
        // unchanged.
        if (cycles == 0)
            return 1;
        while (cycles > 0) {
            // Each spring it doubles it's height
            if (cycles > 0) {
                height *= 2;
                cycles--;
            }
            // Each summer it's height increases by 1m
            if (cycles > 0) {
                height += 1;
                cycles--;
            }
        }
        return height;
    }

    /**
     * Get all the possible combinations of words from a given string
     *
     * @param str
     */
    private static Set<String> stringPermutations(String str, int start, int end, Set<String> output) {
        if (start == end) {
            output.add(str);
            System.out.println(str);
        } else {
            for (int i = start; i <= end; i++) {
                str = swap(str, start, i);
                stringPermutations(str, start + 1, end, output);
                // str = swap(str, start, i);
            }
        }
        return output;
    }

    /**
     * Swap characters
     *
     * @param str
     * @param start
     *            position 1
     * @param i
     *            position 2
     * @return swapped string
     */
    private static String swap(String str, int start, int i) {
        char temp;
        char[] charArray = str.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[start];
        charArray[start] = temp;
        return String.valueOf(charArray);
    }

    /**
     * You are given a square map of size . Each cell of the map has a value
     * denoting its depth. We will call a cell of the map a cavity if and only
     * if this cell is not on the border of the map and each cell adjacent to it
     * has strictly smaller depth. Two cells are adjacent if they have a common
     * side (edge).
     *
     * You need to find all the cavities on the map and depict them with the
     * uppercase character X.
     */
    private static void cavityMap() {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int j = 0;
        String[] grid = new String[n];
        int[][] gridArr = new int[n][n];
        for (int grid_i = 0; grid_i < n; grid_i++) {
            grid[grid_i] = in.next();
            for (int i = 0; i < n; i++) {
                String parts = grid[grid_i].substring(i, i + 1);
                gridArr[j][i] = Integer.parseInt(parts);
                // System.out.println("Parts are"+parts);
            }
            j++;
        }

        for (int i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i != 0 && i != n - 1 && j != 0 && j != n - 1) {
                    if (gridArr[i][j] > gridArr[i + 1][j] && gridArr[i][j] > gridArr[i - 1][j]
                            && gridArr[i][j] > gridArr[i][j + 1] && gridArr[i][j] > gridArr[i][j - 1])
                        gridArr[i][j] = 111;
                }
            }
            // System.out.println();
        }

        String[][] outputStr = new String[n][n];
        int aa = outputStr.length;

        for (int i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                outputStr[i][j] = Integer.toString(gridArr[i][j]);
                // System.out.print(gridArr[i][j]+" ");
                if (gridArr[i][j] == 111)
                    outputStr[i][j] = "X";
            }
            // System.out.println();
        }

        for (int i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                System.out.print(outputStr[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Write a program which prints the time in words for the input given in the
     * format mentioned above.
     *
     * @param hh
     *            Represents the hours
     * @param mm
     *            Represents the minutes
     * @return Time in a human readable string
     */

    public static String getTime(int hh, int mm) {

        String[] lowDigits = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
        String[] tens = { "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
        // 678
        String output = "";
        if (mm == 15 || mm == 30 || mm == 45 || mm == 00) {
            switch (mm) {
                case 15:
                    output += "quarter past " + lowDigits[hh];
                    break;
                case 30:
                    output += "half past " + lowDigits[hh];
                    break;
                case 45:
                    output += "quarter to " + lowDigits[hh + 1];
                    break;
                case 00:
                    output += lowDigits[hh] += " o' clock";
                    break;
                default:
                    output += "ERROR";
                    break;
            }
        } else if (mm < 20) {
            int rmn = mm % 10;
            if (mm == 1)
                output += lowDigits[mm] + " minute past " + lowDigits[hh];
            else
                output += lowDigits[mm] + " minutes past " + lowDigits[hh];
        } else if (mm < 30 && mm > 20) {
            int div = mm / 10;
            if (div > 0)
                output += tens[div] + " ";
            int rmn = mm % 10;
            output += lowDigits[rmn];
            output += " minutes past " + lowDigits[hh];
        } else if (mm > 30 && mm < 60) {
            mm = 60 - mm;
            if (mm < 20) {
                int rmn = mm % 10;
                output += lowDigits[mm];
                output += " minutes to " + lowDigits[hh + 1];
                return output;
            }
            int div = mm / 10;
            if (div > 0)
                output += tens[div] + " ";
            int rmn = mm % 10;
            output += lowDigits[rmn];
            output += " minutes to " + lowDigits[hh + 1];
        }
        // System.out.println("Ouput is "+output);
        return output;
    }

    /**
     * Converts the given number to its readable English text in the range of
     * 1-1000.
     *
     * @param num
     */
    private static String convertNumberToWord(int num) {
        String[] lowDigits = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", " nine", " ten",
                " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen", " seventeen", " eighteen",
                " nineteen" };
        String[] tens = { "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
        // 678
        String output = "";
        if (num > 100 && num < 1000) {
            int div = num / 100;
            output = lowDigits[div] + " hundred ";
            int rem = num % (div * 100);
            int mo = rem / 10;
            if (mo > 0)
                output += tens[mo] + " ";

            int ld = rem % 10;
            output += lowDigits[ld];
            System.out.println(output);
        } else if (num < 100 && num > 0) {
            int div = num / 10;
            if (div > 1)
                output += tens[div] + " ";
            int rmn = num % 20;
            output += lowDigits[rmn] + " ";
            System.out.println("Output is " + output);
        } else if (num == 100 || num == 0) {
            switch (num) {
                case 100:
                    output += "one hundred";
                    break;
                case 0:
                    output += "zero";
                    break;
                default:
                    output += "none";
                    break;
            }
        }
        System.out.println("Output is " + output);
        return output;
    }

    /**
     * Sort the array using counting sort algorithm. <br>
     * Time complexity - O(n + k)
     *
     * @param arr
     *            Array to be sorted
     * @return
     */
    @SuppressWarnings("unused")
    private static int[] countingSort(int[] arr) {
        int max = 7;
        int size = arr.length;
        // Create an empty array to store indexes count
        int[] indexesCount = new int[max + 1];
        for (int i : indexesCount) {
            indexesCount[i] = 0;
        }

        for (int j = 0; j < arr.length; j++) {
            System.out.println(arr[j]);
            ++indexesCount[arr[j]];
        }

        // int[] sumCount = new int[max + 1];
        for (int k = 1; k < max + 1; k++) {
            // sumCount[k] = indexesCount[k] + sumCount[k - 1];
            indexesCount[k] += indexesCount[k - 1];
        }

        int[] outArr = new int[size];
        for (int l = 0; l < arr.length; l++) {
            System.out.print("--->Index is = " + indexesCount[arr[l]]);
            outArr[indexesCount[arr[l]] - 1] = arr[l];
            indexesCount[arr[l]]--;
        }
        int var = 4;
        System.out.println("Variable value is = " + Integer.toString(var));
        for (int m : outArr) {
            System.out.println(m);
        }

        return outArr;
    }

    /**
     * Gaming array challenge on HackerRank.
     *
     * @param arr
     *            Initial gaming array
     * @return False, if Andy wins the game <br>
     *         True, if Bob wins the game
     */
    @SuppressWarnings("unused")
    private static boolean gamingArray(Integer[] arr) {
        int maxSoFar = 0;
        int maxChange = 0;

        for (int k = 0; k < arr.length; k++) {
            if (arr[k] > maxSoFar) {
                maxSoFar = arr[k];
                // Count how many times the max has changed
                maxChange++;
            }
        }
        // System.out.println("Max changed thus far " + maxChange);
        return ((maxChange % 2 == 0) ? false : true);
    }

    /**
     * 2 <br>
     * 8 <br>
     * 5 1 2 3 7 8 6 4 <br>
     * 8 <br>
     * 1 2 5 3 7 8 6 4 <br>
     *
     * There are people queued up, and each person wears a sticker indicating
     * their initial position in the queue (i.e.: 1,2, .. n-1, n with the first
     * number denoting the frontmost position).
     *
     * Any person in the queue can bribe the person directly in front of them to
     * swap positions. If two people swap positions, they still wear the same
     * sticker denoting their original place in line. One person can bribe at
     * most two other persons.
     *
     * Fascinated by this chaotic queue, you decide you must know the minimum
     * number of bribes that took place to get the queue into its current state!
     *
     * @param arr
     *            Person queue
     * @return
     */

    private static int calcBribeCount(Integer[] arr) {
        int bribeCount = 0;
        int n = arr.length;
        int currentVal = n;

        while (currentVal != 1) {
            int j = currentVal - 1;
            // Find index of current value
            while (arr[j] != currentVal && (j >= 0)) {
                j--;
            }
            int cvIndex = j;
            int cvBribe = 0;
            // If current person out of place
            while (cvIndex != (currentVal - 1)) {
                // swap (arr[cvIndex], arr[cvIndex+1]);
                int temp;
                temp = arr[cvIndex];
                arr[cvIndex] = arr[cvIndex + 1];
                arr[cvIndex + 1] = temp;

                cvBribe++;
                cvIndex++;
                // One person can bribe at most two other persons.
                if (cvBribe > 2)
                    return -1; // Too chaotic
            }
            currentVal--;
            bribeCount += cvBribe;
        }
        return bribeCount;
    }

    /**
     * A binary gap within a positive integer N is any maximal sequence of
     * consecutive zeros that is surrounded by ones at both ends in the binary
     * representation of N. <br>
     *
     * For example, number 9 has binary representation 1001 and contains a
     * binary gap of length 2. <br>
     *
     * Write a function returns the length of its longest binary gap. The
     * function should return 0 if N doesn't contain a binary gap. <br>
     *
     * For example, given N = 1041 the function should return 5, <br>
     * because N has binary representation 10000010001 and so its longest binary
     * gap is of length 5. <br>
     *
     * <br>
     * Sample test cases: <br>
     * 328, 16, 1024, 6, 20, 51712, 561892, 5, 7, 9 <br>
     * <br>
     * Assume that: <br>
     *
     * N is an integer within the range [1..2,147,483,647]. <br>
     * <br>
     * Complexity: <br>
     *
     * expected worst-case time complexity is O(log(N)). <br>
     * expected worst-case space complexity is O(1). <br>
     *
     * @param num
     *            Input decimal number
     * @return length of the longest binary gap
     */
    @SuppressWarnings("unused")
    private static int binaryGap(int N) {

        String binRep = Integer.toBinaryString(N);
        int prevIndex, nextIndex, maxCount;
        int count = maxCount = prevIndex = nextIndex = 0;
        if (binRep.indexOf("0") != -1) {
            while (true) {
                // Find the next occurrence of "1" to calculate end point of
                // binary gap
                nextIndex = binRep.indexOf("1", prevIndex + 1);
                if (nextIndex != -1) {
                    count = nextIndex - prevIndex;
                    if (count > maxCount)
                        maxCount = count;
                } else
                    break;
                prevIndex = nextIndex;
            }
        }
        return ((maxCount != 0) ? --maxCount : maxCount);
    }

    // Extra Long Factorials
    public static BigInteger factorial(int n) {
        BigInteger inp = BigInteger.valueOf(n);
        if (inp.equals(BigInteger.ZERO))
            return BigInteger.ONE;
        // BigInteger one = new BigInteger("1");
        BigInteger prev = inp.subtract(BigInteger.ONE);
        return inp.multiply(factorial((prev.intValue())));
    }

    /**
     * Returns the minimum number of delete operations required to have all the
     * array's elements same.
     *
     * @param arr
     *            Input array
     * @return The number of deletions required
     */
    private static int equalizeArray(int[] arr) {

        TreeMap<Integer, Integer> freqMap = new TreeMap<Integer, Integer>();
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        List<Integer> arrList = new ArrayList<Integer>();
        Integer[] aa = new Integer[arrList.size()];
        Arrays.sort(arrList.toArray(aa));
        int maxCount = 0;
        int arrSize = arr.length;
        for (int i = 0; i < arrSize; i++) {
            int count = 0;
            if (freqMap.containsKey(arr[i]))
                count = freqMap.get(arr[i]);
            freqMap.put(arr[i], ++count);
            if (maxCount < count)
                maxCount = count;
        }
        // In order to equalize an array we would have to delete all the
        // elements except the highest occurring elements of the array
        int maxDel = arrSize - maxCount;
        return maxDel;
    }

    /**
     * Function to calculate the run length encoded string for the given string.
     *
     * @param input
     *            Input string
     * @return Run length encoded string
     */
    private static String runLengthEncoding(String str) {

        char first = str.charAt(0);
        StringBuffer sb = new StringBuffer();
        // System.out.println("Input String = " + str);
        int counter = 1;
        if (str.length() < 2) {
            sb.append(first);
            sb.append(counter);
            return sb.toString();
        }
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == first)
                ++counter;
            else {
                sb.append(str.charAt(i - 1));
                sb.append(counter);
                first = str.charAt(i);
                counter = 1;
            }
        }
        sb.append(str.charAt(str.length() - 1));
        sb.append(counter);

        return sb.toString();
    }

    /**
     * Returns a string lexicographically bigger than input string.
     *
     * @param str
     *            Input string
     * @return Lexicographically higher string
     */
    private static String findNextHighestPerm(String str) {
        // Consider input = d, k, h, c ==>> 4, 11, 8, 3
        char[] strChars = str.toCharArray();
        int end = strChars.length - 1;
        // Iterate the longest increasing suffix from the end
        // i.e. 11<--8<--3
        // Get the index of smallest character after the sequence such that
        // char[smallest] < char[smallest + 1]
        // i.e. 4 < 11
        while ((end > 0) && (strChars[end] <= strChars[end - 1])) {
            end--;
        }
        // If string is already in descending order then return
        if (end <= 0)
            return "no answer";

        // pivot becomes index of second smallest character i.e d or 4
        int pivot = end - 1;
        int i = str.length() - 1;
        // d, k, h, c
        // Find the second highest character after pivot
        while (str.charAt(i) <= str.charAt(pivot)) {
            i--;
        }

        // Swap pivot with the next highest element than it from the end i.e. 4
        // <--> 8
        // 4, 11, 8, 3 --> d, k, h, c
        char temp = strChars[pivot];
        strChars[pivot] = strChars[i];
        strChars[i] = temp;
        // 8, 11, 4, 3 --> h, k, d, c

        // Sort the characters after pivot(0) --> k, d, c in ascending order
        // Reverse the suffix sequence
        i = str.length() - 1;
        while (end < i) {
            temp = strChars[end];
            strChars[end] = strChars[i];
            strChars[i] = temp;
            end++;
            i--;
        }
        // output string ==>> h, c, d, k
        return new String(strChars);
    }

    /**
     * Reverses the words in the given string and capitalize first letter of
     * each word. <br>
     * For ex. "Hello World" --> "OlleH DlroW"
     *
     * @param string
     *            Input sequence of words
     * @return String with reversed words
     */
    private static String reverseWordsInText(String string) {
        String[] splits = string.split(" ");
        StringBuffer sb = new StringBuffer();
        // Preserve the order of input words
        for (String string2 : splits) {
            // Capitalize first letter of reversed string ==> OlleH
            sb.append((reverseString(string2).substring(0, 1).toUpperCase()));
            // Append the remaining characters keeping the order(lower/upper) of
            // letters as is ==> lleH
            sb.append(reverseString(string2).substring(1));
            sb.append(" ");
        }

        // If we want to reverse the order of words as well in the output string
        // for (int s=splits.length-1 ; s>=0; s--) {
        // sb.append(reverseString(splits[s]));
        // sb.append(" ");
        // }
        return sb.toString();
    }

    /**
     * Reverse the given string in O(n) time.
     *
     * @param str
     *            Input string
     * @return Reversed string
     */
    private static String reverseString(String str) {

        char[] strChars = str.toCharArray();
        int l = 0;
        int r = strChars.length - 1;

        while (l < r) {
            // swap (strChars[l], strChars[r]);
            char temp = strChars[l];
            strChars[l] = strChars[r];
            strChars[r] = temp;
            l++;
            r--;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(strChars);
        return sb.toString();
    }

    /**
     * Sherlock and the valid string<br>
     * <b>INCOMPLETE SOLUTION <br>
     * Failed 4 test cases out of 15 on Hackerrank</b> <blockquote>Failing for
     * test case "aabcd" ==> should return "NO" instead of "YES"</blockquote>
     *
     * @param s
     *            Input string
     * @return "YES" or "NO"
     */
    private static String isValid(String s) {

        String sToLower = s.toLowerCase();
        char[] letters = sToLower.toCharArray();
        int[] count = new int[124];
        for (int i : count) {
            count[i] = 0;
        }

        for (char l : letters) {
            int ascii = l;
            ++count[ascii];
        }

        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 97; i < 123; i++) {
            if (count[i] != 0) {
                numbers.add(count[i]);
            }
        }

        Set<Integer> uniqKeys = new TreeSet<Integer>(numbers);
        Integer[] intArr = new Integer[uniqKeys.size()];
        if (uniqKeys.size() == 2) {
            intArr = uniqKeys.toArray(intArr);
            int diff = Math.abs(intArr[0] - intArr[1]);
            if (diff > 1)
                return "NO";
        }

        return (uniqKeys.size() > 2) ? "NO" : "YES";
    }

    /**
     * Check whether given parenthesis expression is balanced or not.
     *
     * @param exp
     *            Input expression string
     * @return "YES", if it is balanced <br>
     *         "NO", if it is not
     */
    private static String parenthesisChecker(String exp) {
        StringBuilder sb = new StringBuilder();
        sb.append(exp);
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < sb.length(); i++) {
            Character c = sb.charAt(i);
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            }

            else if (c == '}' || c == ')' || c == ']') {
                if (stack.isEmpty()) {
                    return "NO";
                } else if (!matchOpeningPair(c, stack.pop())) {
                    return "NO";
                } else
                    continue;
            }
        }
        if (stack.isEmpty())
            return "YES";
        return "NO";
    }

    private static boolean matchOpeningPair(Character c, Character peek) {

        if (c == '}' && peek == '{') {
            return true;
        } else if (c == ')' && peek == '(') {
            return true;
        } else if (c == ']' && peek == '[') {
            return true;
        } else
            return false;
    }

    /**
     * Get the maximum element from the stack. <br>
     * Hackerrank Problem: <br>
     * 3 choices -- <br>
     * 1. Push element <br>
     * 2. Pop element <br>
     * 3. Print max element of stack at that point
     *
     */
    public static void stackMaxElement() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> maxStack = new Stack<Integer>();

        while (n > 0) {
            int choice = sc.nextInt();
            if (choice == 1) {
                Integer val = sc.nextInt();
                if (val >= getStackMax(maxStack))
                    maxStack.push(val);
                stack.push(val);
            } else if (choice == 2) {
                Integer value = stack.pop();
                if (value == getStackMax(maxStack))
                    maxStack.pop();
            } else if (choice == 3) {
                if (!maxStack.isEmpty()) {
                    System.out.println(getStackMax(maxStack));
                }
            }
            n--;
        }
        sc.close();
    }

    private static Integer getStackMax(Stack<Integer> s) {
        if (s.isEmpty())
            return Integer.MIN_VALUE;
        return s.peek();
    }

    /**
     * <b>Calculate no of operations required to make two strings anagrams.</b>
     * <br>
     * Split the given string in half, compare two halves (S1, S2) <br>
     * <br>
     * For ex. abcb == ab + cb <br>
     * changes required = 1 (replace a from S1 with c) <br>
     * <br>
     * xaxbbbxx == "xaxb" + "bbxx". <br>
     * changes required = 1 (Replace 'a' from S1 with 'b' so that S1 = "xbxb"
     * <br>
     * and we can rearrange its letter to "bbxx" in order to get S2.)
     *
     * @param s
     * @return
     */
    public static int anagram(String s) {
        // Remove the matching characters from both strings.
        // Distinct(remaining) characters from first string will be the answer
        // (i.e. no of changes required).
        int count = 0;
        if (s.length() % 2 != 0) { // If the string is of odd length then it's
            // not anagram ==> aba
            return -1;
        }
        int half = s.length() / 2;

        // "aaabbb" == "aaa" + "bbb"
        String S1 = s.substring(0, half);
        String S2 = s.substring(half, s.length());
        int[] charCount = new int[26];
        // Increment the index of character count
        // "aaa" --> charCount[96] = 3
        for (char c : S1.toCharArray())
            ++charCount[c - 'a'];
        // Decrement the character count index only when it's already present
        // i.e. greater than 0
        // "bbb" --> charCount[97] = 0
        for (char c : S2.toCharArray())
            // Decrement only when character has appeared before
            charCount[c - 'a'] = (charCount[c - 'a'] > 0) ? charCount[c - 'a'] - 1 : 0;
        // Count the charCount array == 3 + 0 = 3
        for (int i : charCount)
            count += i;
        return count; // 3
    }

    /**
     * Left rotate the given array for k no of times
     *
     * @param a
     *            Input array
     * @param n
     *            Length of array
     * @param k
     *            Rotate count
     * @return Rotated array
     */
    public static int[] arrayLeftRotation(int[] a, int n, int k) {
        // Iterate for k no of rotations
        for (int i = 0; i < k; i++) {
            int ii = 0;
            // Store first element of array before rotation
            int temp = a[ii];
            int j = 0;
            // Rotate elements one time except the last element
            while (j < n - 1) {
                a[j] = a[j + 1];
                j++;
            }
            // Replace the last element of array with first
            a[n - 1] = temp;
            ii++;
        }
        return a;
    }

    /**
     * Check if two string contains anything in them common.
     *
     * @param s1
     *            String 1
     * @param s2
     *            String 2
     * @return "YES", if there's any character common in them <br>
     *         "NO", if there's nothing common in two strings
     */
    @SuppressWarnings("unused")
    private static String twoStrings(String s1, String s2) {

        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        int[] indexes = new int[127];
        for (int j : indexes)
            j = 0;
        for (int i = 0; i < s1Arr.length; i++)
            indexes[s1Arr[i]]++;

        for (int j = 0; j < s2Arr.length; j++) {
            if (indexes[s2Arr[j]] > 0)
                indexes[s2Arr[j]] = indexes[s2Arr[j]] - 2;
        }

        for (int k = 0; k < indexes.length; k++) {
            if (indexes[k] < 0)
                return "YES";
        }

        return "NO";
    }

    /**
     * Check if the given number is prime or not in O(N ^ 1/2) time. <b> The
     * algorithm determines whether or not a single number, n, is prime or not.
     * Each time the algorithm is run, its runtime is <i>with respect to n.</i>
     * <b>
     *
     * @param n
     *            Input number
     * @return true, if given no is prime
     */
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Find out the no of deletions required to make two strings anagrams
     *
     * @param s1
     * @param s2
     * @return No of deletions required
     */
    static int makingAnagrams(String s1, String s2) {
        int charCount[] = new int[26];
        for (int i = 0; i < s1.length(); i++)
            charCount[s1.charAt(i) - 97]++; // charCount[s1.charAt(i) - 'a']++
        // (since letters are all lower cases)
        // To store the count of letter a means subtract 97
        // from character and
        // add one to it's array values
        for (int i = 0; i < s2.length(); i++)
            charCount[s2.charAt(i) - 97]--;
        int count = 0;
        for (int i = 0; i < 26; i++)
            count += Math.abs(charCount[i]);
        return count;
    }

    /**
     * Given a string, find the length of the longest substring without
     * repeating characters.
     *
     * @param s
     *            Input string
     * @return Length of substring
     */
    public static int lengthOfLongestSubstring(String s) {
        int ins = 0, del = 0, max = 0;
        Set<Character> set = new HashSet<>();
        while (ins < s.length()) {
            // If char not present in the set
            if (!set.contains(s.charAt(ins))) {
                // Add the char and increment the insert pointer
                set.add(s.charAt(ins++));
                max = Math.max(max, set.size());
            } else {
                // Remove the char from start in the set and
                // increment the deletion pointer
                set.remove(s.charAt(del++));
            }
        }
        return max;
    }

    /**
     * Find the longest palindromic substring in s. <br>
     * Explanation:
     * https://discuss.leetcode.com/topic/21848/ac-relatively-short-and-very-clear-java-solution
     * <br>
     * Editorial:
     * <b>https://leetcode.com/articles/longest-palindromic-substring/#approach-4-expand-around-center-accepted</b>
     *
     * @param s
     *            Input string
     * @return Longest palindromic substring
     */
    public static String longestPalindromicSubstring(String s) {
        String res = "";
        int currLength = 0;
        for (int i = 0; i < s.length(); i++) {
            // Expand with keeping char as center (odd length substring)
            if (isPalindrome(s, i - currLength - 1, i)) {
                res = s.substring(i - currLength - 1, i + 1);
                currLength = currLength + 2;
            } else if (isPalindrome(s, i - currLength, i)) {
                // right combinations (even length substring)
                res = s.substring(i - currLength, i + 1);
                currLength = currLength + 1;
            }
        }
        return res;

        /// Alternative solution [Editorial]
        // int start = 0, end = 0;
        // for (int i = 0; i < s.length(); i++) {
        // int len1 = expandAroundCenter(s, i, i);
        // int len2 = expandAroundCenter(s, i, i + 1);
        // int len = Math.max(len1, len2);
        // if (len > end - start) {
        // start = i - (len - 1) / 2;
        // end = i + len / 2;
        // }
        // }
        // return s.substring(start, end + 1);
    }

    public static boolean isPalindrome(String s, int begin, int end) {
        if (begin < 0)
            return false;
        while (begin < end) {
            if (s.charAt(begin++) != s.charAt(end--))
                return false;
        }
        return true;
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     * Given a sorted array, remove the duplicates in place such that each
     * element appear only once and return the new length.
     *
     * Do not allocate extra space for another array, you must do this in place
     * with constant memory.
     *
     * <blockquote> Time Complexity = O(n), Space Complexity = O(1)
     * </blockquote>
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1])
                nums[j++] = nums[i];
        }
        return j;
    }

    /**
     * Search an element in a sorted rotated array.
     *
     * @param nums
     *            Array
     * @param target
     *            Search key
     * @return index of the given key
     */
    public static int search(int[] nums, int target) {
        return searchUtil(nums, 0, nums.length - 1, target);
    }

    public static int searchUtil(int[] a, int lo, int hi, int key) {
        if (lo > hi)
            return -1;
        int mid = (lo + hi) / 2;
        if (a[mid] == key)
            return mid;

        if (a[lo] <= a[mid]) {
            if ((key <= a[mid]) && (key >= a[lo]))
                return searchUtil(a, lo, mid - 1, key);
            else
                return searchUtil(a, mid + 1, hi, key);
        }

        if ((key >= a[mid]) && (key <= a[hi]))
            return searchUtil(a, mid + 1, hi, key);
        return searchUtil(a, lo, mid - 1, key);
    }

    /**
     * Find minimum in rotated sorted array
     *
     * @param nums
     *            Input array
     * @return Minimum key value
     */
    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            // [1, 2, 3, 4, 5]
            if (nums[start] < nums[end])
                return nums[start];

            int mid = (start + end) / 2;
            // [4, 5, 6, 7, 1, 2, 3]
            if (nums[mid] > nums[end])
                start = mid + 1;
            else
                // [4, 5, 1, 2, 3]
                end = mid;
        }
        return nums[start];
    }

    /**
     * Given an integer n, generate the nth term of the count-and-say sequence.
     * The count-and-say sequence is the sequence of integers with the first
     * five terms as following:<br>
     * <b> 1. 1 <br>
     * </b> <code> 1 is read off as "one 1" or 11. </code> <br>
     * <b> 2. 11 <br>
     * </b> <code> 11 is read off as "two 1s" or 21. </code> <br>
     * <b> 3. 21 <br>
     * </b> <code> 21 is read off as "one 2, then one 1" or 1211. </code> <br>
     * <b> 4. 1211 <br>
     * </b>
     * <code> 1211 is read off as "one 1, one 2, then two 1" or 111221. </code>
     * <br>
     * <b> 5. 111221 </b> <br>
     *
     * @param n
     * @return Nth count-and-say sequence string
     */
    public static String countAndSay(int n) {
        String s = "1";
        if (n == 1)
            return "1";
        StringBuilder res = new StringBuilder();
        char prev = '1';
        int count = 0;
        for (int i = 1; i < n; i++) {
            count = 0;
            res.setLength(0);
            for (int jj = 0; jj < s.length(); jj++) {
                if (s.charAt(jj) == prev)
                    count++;
                else {
                    if (count == 0) {
                        prev = s.charAt(jj);
                        count = 1;
                        continue;
                    }
                    res.append(count).append(prev);
                    count = 1;
                    prev = s.charAt(jj);
                }
            }
            res.append(count).append(prev);
            // System.out.println(res);
            s = res.toString();
        }
        return s;
    }

    /**
     * Given an unsorted integer array, find the first missing positive integer.
     *
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        int i = 0;
        int n = nums.length;
        // 3, 4, -1, 1
        for (i = 0; i < n; ++i) {
            // Iterate until array elements are in their position
            // 1, -1, 3, 4 i.e. 1, 2, 3, 4
            while ((nums[i] <= n) && (nums[i] > 0) && (nums[i] != nums[nums[i] - 1])) {
                swap(nums, i, nums[i] - 1);
            }
        }
        i = 0;
        for (i = 0; i < n; i++) {
            if ((nums[i] != i + 1)) {
                break;
            }
        }
        return i + 1;
    }

    private static void swap(int[] a, int n1, int n2) {
        int temp = a[n1];
        a[n1] = a[n2];
        a[n2] = temp;
    }

    /**
     * Check if there exists a pair of squares that equals the given number.
     * <br>
     * For ex. <br>
     * num1*num1 + num2*num2 = c <br>
     * <b>1*1 + 2*2 = 5 </b><br>
     *
     * @param c
     *            Given number
     * @return true, if there exists a pair of such squares
     */
    public boolean judgeSquareSum(int c) {
        double sqrt = Math.sqrt(c);
        int x = (int) sqrt;

        // Check if given number is a perfect square
        if (Math.pow(sqrt, 2) == Math.pow(x, 2)) {
            return true;
        }

        int limit = (int) Math.sqrt(c);
        int[] squares = new int[limit];

        // Calculate squares of all the numbers until squareroot digit
        for (int i = 0; i < limit; i++) {
            squares[i] = (i + 1) * (i + 1);
        }

        for (int i = limit - 1; i >= 0; i--) {
            int target = c - squares[i];
            // Scan and compare if remainder exists in squares array
            int pos = Arrays.binarySearch(squares, 0, i + 1, target);
            if (pos >= 0) {
                return true;
            }
        }
        return false;

        // Alternate solution
        // int i = 0;
        // int j = (int) Math.sqrt(c);
        // while (i <= j) {
        // if ((i * i + j * j) < c)
        // i++;
        // else if ((i * i + j * j) > c)
        // j--;
        // else
        // return true;
        // }
        // return false;

    }

    /**
     * Find the maximum product of 3 numbers in an array of mixed(+/-) integers.
     *
     * @param nums
     *            Integer array
     * @return Max product
     */
    public static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        Integer pSum = 1, nSum = 1;
        // All positive numbers
        pSum = nums[len - 1] * nums[len - 2] * nums[len - 3];
        // Positive and/or negative numbers
        nSum = nums[0] * nums[1] * nums[len - 1];
        return Math.max(pSum, nSum);
    }

    /**
     * Given a column title as appear in an Excel sheet, return its
     * corresponding column number.
     *
     * For example:
     *
     * A -> 1 B -> 2 C -> 3 ... Z -> 26 AA -> 27 AB -> 28
     *
     * @param s
     * @return
     */
    public static int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res *= 26;
            res += (s.charAt(i) - 65) + 1;
        }
        return res;
    }

    /**
     * Given a positive integer, return its corresponding column title as appear
     * in an Excel sheet. For example:
     *
     * 1 -> A 2 -> B 3 -> C ... 26 -> Z 27 -> AA 28 -> AB
     *
     * @param n
     * @return
     */
    public static String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            n--;
            sb.append((char) (65 + (n % 26)));
            n /= 26;
        }
        return sb.reverse().toString();
    }

    /**
     * Given an array of n integers where n > 1, nums, return an array output
     * such that output[i] is equal to the product of all the elements of nums
     * except nums[i].
     *
     * Solve it without division and in O(n).
     *
     * For example, given [1,2,3,4], return [24,12,8,6].
     *
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int temp = 1;

        // Scan array to right and store results
        for (int i = 0; i < nums.length; i++) {
            res[i] = temp;
            temp *= nums[i];
        }

        temp = 1;
        // Scan array to left and update results
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= temp;
            temp *= nums[i];
        }
        return res;
    }

    /**
     * Given an array of integers sorted in ascending order, find the starting
     * and ending position of a given target value.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];

        res[0] = binarySearchFirst(nums, 0, nums.length - 1, target);
        res[1] = binarySearchLast(nums, 0, nums.length - 1, target);
        return res;
    }

    public static int binarySearchFirst(int[] arr, int lo, int hi, int key) {
        if (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == key && (mid == 0 || arr[mid - 1] < key))
                return mid;
            else if (key > arr[mid])
                return binarySearchFirst(arr, mid + 1, hi, key);
            else
                return binarySearchFirst(arr, lo, mid - 1, key);
        }
        return -1;
    }

    public static int binarySearchLast(int[] arr, int lo, int hi, int key) {
        if (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == key && (mid == arr.length - 1 || arr[mid + 1] > key))
                return mid;
            else if (key < arr[mid])
                return binarySearchLast(arr, lo, mid - 1, key);
            else
                return binarySearchLast(arr, mid + 1, hi, key);
        }
        return -1;
    }

    /**
     * Given a digit string, return all possible letter combinations that the
     * number could represent.
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        String[] mappings = { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        LinkedList<String> queue = new LinkedList<String>();
        if (digits.length() < 1)
            return queue;

        queue.add("");
        for (int i = 0; i < digits.length(); i++) {
            int num = Character.getNumericValue(digits.charAt(i));
            if (num < 2) {
                queue.clear();
                return queue;
            }

            // Iterate until each char is appended to the last combination
            while (queue.peek().length() == i) {
                // Get the front of the queue
                String temp = queue.poll();

                // Append the chars to front of the queue
                for (char c : mappings[num].toCharArray()) {
                    queue.offer(temp + c);
                }
            }
        }
        return queue;
    }

    /**
     * Find the contiguous subarray within an array (containing at least one
     * number) which has the largest sum.
     *
     * For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous
     * subarray [4,-1,2,1] has the largest sum = 6.
     *
     * @param nums
     * @return
     */
    public static int maxSumSubArray(int[] nums) {
        int maxSoFar = nums[0], maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    /**
     * Find the contiguous subarray within an array (containing at least one
     * number) which has the largest product.
     *
     * For example, given the array [2,3,-2,4], the contiguous subarray [2,3]
     * has the largest product = 6.
     *
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        int prevMax, max = nums[0];
        int currMax = nums[0], currMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // carry on previous max product
            prevMax = currMax;
            currMax = Math.max(Math.max(currMin * nums[i], prevMax * nums[i]), nums[i]);

            // product of previous max and current number can be minimum when
            // negative integers are present
            // [-2, 1, -3, 4]
            currMin = Math.min(Math.min(currMin * nums[i], prevMax * nums[i]), nums[i]);
            max = Math.max(max, currMax);
        }
        return max;
    }

    /**
     * Calculate the sum of two integers a and b, but you are not allowed to use
     * the operator + and - i.e. w/o arithmetic operators
     *
     * @param a
     *            Number 1
     * @param b
     *            Number 2
     * @return Sum of both the numbers
     */
    public static int getSumWOarithmeticOps(int a, int b) {
        while (b != 0) {
            // generate all the carry
            int c = a & b; // 0001 --> TT for AND is
            // 0&0 = 0, 0&1 = 0, 1&0 = 0, 1&1 = 1

            // add all the bits & store the sum in a
            a = a ^ b; // 0110 --> TT for XOR is
            // 0^0 = 0, 0^1 = 1, 1^0 = 1, 1^1 = 0

            // shift the carry one bit to left
            b = c << 1;
        }
        return a;
    }

    /**
     * The Hamming distance between two integers is the number of positions at
     * which the corresponding bits are different.
     *
     * Given two integers x and y, calculate the Hamming distance.
     *
     * @param x
     *            Integer 1
     * @param y
     *            Integer 2
     * @return
     */
    public static int hammingDistance(int x, int y) {
        int xor = x ^ y; // it will set off all the ones in result
        // count the 1s in the result
        int count = Integer.bitCount(xor);
        // String bin = Integer.toBinaryString(x ^ y);
        return count;
    }

    /**
     * Given an 2D board, count how many battleships are in it. The battleships
     * are represented with 'X's, empty slots are represented with '.'s. You may
     * assume the following rules:
     *
     * <li>You receive a valid board, made of only battleships or empty slots.
     *
     * <li>Battleships can only be placed horizontally or vertically. <br>
     * In other words, they can only be made of the shape <code><b>1xN (1 row, N
     * columns)</b> or <b>Nx1 (N rows, 1 column),</b>
     * where N can be of any size.</code>
     * <li>At least one horizontal or vertical cell separates between two
     * battleships - <strong>
     * <ul>
     * there are no adjacent battleships.
     * </ul>
     * </strong>
     *
     * @param board
     * @return
     */
    public static int countBattleships(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;
        int count = 0;
        // iterate over all the cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Search for the head of the battleship
                if (board[i][j] == 'X') {
                    // there should not be another ship before the head i.e.
                    // nothing should be on the left or up of the head

                    if ((i == 0 || board[i - 1][j] != 'X') // up
                            && (j == 0 || board[i][j - 1] != 'X')) // left
                        count++;
                }
            }
        }
        return count;
    }

    /**
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of
     * islands. <br>
     * An island is surrounded by water and is formed by connecting adjacent
     * lands <b>horizontally or vertically</b>. <br>
     * You may assume all four edges of the grid are all surrounded by water.
     *
     * <br>
     * <br>
     * Example 1: <br>
     * 11110<br>
     * 11010<br>
     * 11000<br>
     * 00000<br>
     * Answer: 1
     *
     * @param grid
     * @return
     */
    public static int numIslands(char[][] grid) {
        int rows = grid.length;
        if (rows == 0)
            return 0;
        int cols = grid[0].length;
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Check for island
                if (grid[i][j] == '1') {
                    checkIslandsDFS(grid, i, j);
                    ++count;
                }
            }
        }
        return count;
    }

    // Visit all the connected islands of the given/found island recursively
    public static void checkIslandsDFS(char[][] grid, int i, int j) {
        // if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length ||
        // grid[i][j] != '1')
        // return;
        if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j] == '1') {
            // Mark the visited neighbor as false/water
            grid[i][j] = '0';
            checkIslandsDFS(grid, i, j + 1); // right
            checkIslandsDFS(grid, i + 1, j); // down
            checkIslandsDFS(grid, i - 1, j); // up
            checkIslandsDFS(grid, i, j - 1); // left
        }
    }

    /**
     * Given an array of non-negative integers, you are initially positioned at
     * the first index of the array.
     *
     * Each element in the array represents your maximum jump length at that
     * position.
     *
     * Determine if you are able to reach the last index. <br>
     * For example: A = [2,3,1,1,4], return true. <br>
     * A = [3,2,1,0,4], return false.
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int currFarthest = 0;
        // Loop through an entire array
        for (int i = 0; i < nums.length; i++) {
            // If current/last location is unreachable from max jump
            // then return false
            // Consider case --> [3,2,1,0,4]
            if (i > currFarthest) {
                return false;
            }
            // Check the max jump from current location
            currFarthest = Math.max(currFarthest, i + nums[i]);
        }
        return true;
    }

    /**
     * Given an array of non-negative integers, you are initially positioned at
     * the first index of the array.
     *
     * Each element in the array represents your maximum jump length at that
     * position.
     *
     * Your goal is to reach the last index in the minimum number of jumps. <br>
     * {@code For example: Given array A = [2,3,1,1,4]
     * The minimum number of jumps to reach the last index is 2. (Jump 1 step
     * from index 0 to 1, then 3 steps to the last index.)
     * }
     *
     * <b>Note: You can assume that you can always reach the last index.</b>
     *
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        int currJump = 0, maxJump = 0, count = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            // check how far can you jump from current position
            currJump = Math.max(currJump, i + nums[i]);

            // once you have reached the farthest point
            // initiate the jump count
            if (i == maxJump) {
                count++; // make another jump
                maxJump = currJump;
            }
        }
        return count;
    }

    /**
     * Given a collection of numbers that might contain duplicates, return all
     * possible <b>unique</b> permutations.
     *
     * <br>
     * For example, <b>[1,1,2]</b> have the following unique permutations: <br>
     * <b>[ [1,1,2],<br>
     * [1,2,1], <br>
     * [2,1,1] ]</b>
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Set<List<Integer>> set = permuteDFS(nums, new HashSet<List<Integer>>(), 0, nums.length - 1);
        res.addAll(set);
        return res;
    }

    private static Set<List<Integer>> permuteDFS(int[] arr, Set<List<Integer>> tempSet, int begin, int end) {
        if (begin >= end) {
            List<Integer> list = new ArrayList<Integer>();
            for (int i : arr) {
                list.add(i);
            }
            tempSet.add(list);
        }

        for (int i = begin; i <= end; i++) {
            swap(arr, begin, i);
            permuteDFS(arr, tempSet, begin + 1, end);
            swap(arr, begin, i);
        }
        return tempSet;
    }

    /**
     * Given a string containing just the characters '(' and ')', find the
     * length of the longest valid (well-formed) parentheses substring. <br>
     * For <b>"(()"</b>, the longest valid parentheses substring is "()", which
     * has <b>length = 2</b>. <br>
     * For <b>"()(()"</b>, the longest valid parentheses substring is "()",
     * which has <b>length = 2</b>. <br>
     * Another example is <b>")()())"</b>, where the longest valid parentheses
     * substring is "()()", which has <b>length = 4</b>.
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {

        Stack<Integer> stack = new Stack<Integer>();
        int maxCount = 0;
        int leftIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push(i);
            else {
                if (stack.isEmpty())
                    leftIndex = i;
                else {
                    stack.pop();
                    if (stack.isEmpty())
                        maxCount = Math.max(maxCount, i - leftIndex);
                    else
                        maxCount = Math.max(maxCount, i - stack.peek());
                }
            }
        }
        return maxCount;
    }

    /**
     * A sequence of number is called arithmetic if it consists of at least
     * three elements and if the difference between any two consecutive elements
     * is the same.
     *
     * For example, these are arithmetic sequence: <br>
     * 1, 3, 5, 7, 9<br>
     * 7, 7, 7, 7 <br>
     * 3, -1, -5, -9 <br>
     * The following sequence is not arithmetic. <br>
     * 1, 1, 2, 5, 7 <br>
     * The function should return the number of arithmetic slices in the array
     * A. <br>
     *
     * Example: A = [1, 2, 3, 4] <br>
     * return: 3, for 3 arithmetic slices in A: <br>
     * [1, 2, 3], <br>
     * [2, 3, 4] and <br>
     * [1, 2, 3, 4] itself.
     *
     * @param A
     * @return
     */
    public static int numberOfArithmeticSlices(int[] A) {
        int curr = 0, sum = 0;

        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                curr++;
                sum += curr;
            } else
                curr = 0;
        }
        return sum;
    }

    /**
     * Given an integer array, you need to find one continuous subarray that if
     * you only sort this subarray in ascending order, then the whole array will
     * be sorted in ascending order, too. <br>
     * <br>
     * You need to find the shortest such subarray and output its length. <br>
     * <b>Input</b>: [2, 6, 4, 8, 10, 9, 15] <br>
     * <b>Output</b>: 5 <br>
     * <b>Explanation</b>: You need to sort [6, 4, 8, 10, 9] in ascending order
     * to make the whole array sorted in ascending order.
     *
     * @param nums
     * @return
     */
    public static int findUnsortedSubarray(int[] nums) {
        int[] dup = nums.clone();
        Arrays.sort(nums);
        int start = 0, end = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != dup[i]) {
                start = i;
                break;
            }
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != dup[i]) {
                end = i;
                break;
            }
        }
        return end - start + 1;
    }

    /**
     * You're given a matrix represented by a two-dimensional array, and two
     * positive integers r and c representing the row number and column number
     * of the wanted reshaped matrix, respectively. The reshaped matrix need to
     * be filled with all the elements of the original matrix in the same
     * row-traversing order as they were. <br>
     * If the 'reshape' operation with given parameters is possible and legal,
     * output the new reshaped matrix; Otherwise, output the original matrix.
     * <br>
     * <br>
     * <b>Input</b>: nums = [[1,2], [3,4]] <br>
     * r = 1, c = 4 <br>
     * Output: [[1,2,3,4]] <br>
     * Explanation: The row-traversing of nums is [1,2,3,4]. The new reshaped
     * matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
     * <br>
     * <br>
     * <b>Input</b>: nums = [[1,2], [3,4]] <br>
     * r = 2, c = 4 <br>
     * Output: [[1,2], [3,4]] <br>
     * Explanation: There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix.
     * So output the original matrix.
     *
     * @param nums
     * @param r
     * @param c
     * @return
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int rows = nums.length;
        int cols = nums[0].length;

        if (r * c != rows * cols)
            return nums;
        int[][] res = new int[r][c];
        int r1 = 0, c1 = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[r1][c1] = nums[i][j];
                c1++;
                if (c1 == c) {
                    c1 = 0;
                    r1++;
                }
            }
        }
        return res;
    }

    /**
     * Given an array of integers where 1 <= a[i] <= n (n = size of array), some
     * elements appear twice and others appear once. Find all the elements of
     * [1, n] inclusive that do not appear in this array. <br>
     * Could you do it without extra space and in O(n) runtime? You may assume
     * the returned list does not count as extra space. <br>
     * <br>
     * Input: [4,3,2,7,8,2,3,1] <br>
     * Output: [5,6]
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            // Iterate until array elements are in their position
            while ((nums[i] <= nums.length) && (nums[i] > 0) && (nums[i] != nums[nums[i] - 1])) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }

        /*
         * // Alternate O(n) solution ====> // Mark every element visited by
         * making the value at index negative for (int i = 0; i < nums.length;
         * i++) { int val = Math.abs(nums[i]) - 1; // If unvisited then mark
         * visited if (nums[val] > 0) { nums[val] = -nums[val]; } }
         *
         * // If the value is not visited(negative) then add to result for (int
         * i = 0; i < nums.length; i++) { if (nums[i] > 0) { result.add(i + 1);
         * } }
         */

        return result;
    }

    /**
     * Given an array of integers, 1 <= a[i] <= n (n = size of array), some
     * elements appear twice and others appear once.
     *
     * Find all the elements that appear twice in this array. <br>
     * Could you do it without extra space and in O(n) runtime? <br>
     * <br>
     * Input: [4,3,2,7,8,2,3,1] <br>
     * Output: [2,3]
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        // when find a number i, flip the number at position i-1 to negative.
        // if the number at position i-1 is already negative, i is the number
        // that occurs twice.

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0)
                result.add(Math.abs(index) + 1);
            nums[index] = -nums[index];
        }

        return result;
    }

    /**
     * Find all distinct/unique palindromic substrings from a given string
     *
     * @param input
     * @return
     */
    public static Set<String> getPalindromes(final String input) {

        final Set<String> result = new HashSet<>();

        for (int i = 0; i < input.length(); i++) {
            // expanding even length palindromes:
            expandPalindromes(result, input, i, i + 1);
            // expanding odd length palindromes:
            expandPalindromes(result, input, i, i);
        }
        return result;
    }

    private static void expandPalindromes(final Set<String> result, final String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            result.add(s.substring(i, j + 1));
            i--;
            j++;
        }
    }

    /**
     * Given an array consisting of n integers, find the contiguous subarray of
     * given length k that has the maximum average value. And you need to output
     * the maximum average value. <br>
     * <br>
     * <b>Input</b>: [1,12,-5,-6,50,3], k = 4 <br>
     * <b>Output</b>: 12.75 <br>
     * <b>Explanation</b>: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
     *
     * @param nums
     * @param k
     * @return
     */
    public static double findMaxAverage(int[] nums, int k) {
        int sum = nums[0];
        double avg = 0;
        for (int j = 1; j < k; j++)
            sum += nums[j];

        int maxSum = sum;
        for (int i = k; i < nums.length; i++) {
            // slide the window
            // add current element and remove prev first element from the window
            sum = sum + nums[i] - nums[i - k];
            maxSum = Math.max(sum, maxSum);
        }

        avg = (double) maxSum / (double) k;
        return avg;
    }

    /**
     * Given an array of integers, return indices of the two numbers such that
     * they add up to a specific target. You may assume that each input would
     * have exactly one solution, and you may not use the same element twice.
     * <br>
     * <b>Input</b>: nums = [2, 7, 11, 15], target = 9, <br>
     * Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] indices = new int[2];

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                indices[1] = i;
                indices[0] = map.get(target - nums[i]);
                break;
            }
            map.put(nums[i], i);
        }
        return indices;
    }

    /**
     * Partition an integers array into odd number first and even number second.
     * <br>
     * <b>Input</b>: [12, 34, 45, 9, 8, 90, 3] <br>
     * <b>Output</b>: [3,9,45,34,8,90,12] <br>
     * <br>
     * <b>Input</b>: [0, 1, 2, 3, 4] <br>
     * <b>Output</b>: [3,1,2,0,4]
     *
     * @param nums
     */
    public static void partitionArray(int[] nums) {
        if (nums == null)
            return;

        int left = 0, right = nums.length - 1;
        while (left < right) {
            // odd number
            while (left < right && nums[left] % 2 != 0) {
                left++;
            }
            // even number
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            // swap
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
    }

    /**
     * Determine if a Sudoku is valid.
     *
     * The Sudoku board could be partially filled, where empty cells are filled
     * with the character '.'
     *
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    if (!set.add(board[i][j] + " row " + i) || !set.add(board[i][j] + " column " + j)
                            || !set.add(board[i][j] + " cube " + i / 3 + "-" + j / 3))
                        // if the number is already been added in corres.
                        // row/col/block
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * Given an array of integers and an integer k, find out whether there are
     * two distinct indices i and j in the array such that nums[i] = nums[j] and
     * the absolute difference between i and j is at most k.
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Alternate solution
        // Set<Integer> set = new HashSet<Integer>();
        // for(int i=0; i<nums.length; i++) {
        // if (i > k) set.remove(nums[i-k-1]);
        // if (!set.add(nums[i])) return true;
        // }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k)
                    return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    /**
     * Given an array consists of non-negative integers, your task is to count
     * the number of triplets chosen from the array that can make triangles if
     * we take them as side lengths of a triangle. <br>
     * <b>Input</b>: [2,2,3,4] <b>Output</b>: 3 <br>
     * <b>Valid combinations are</b>: <br>
     * 2,3,4 (using the first 2)<br>
     * 2,3,4 (using the second 2) <br>
     * 2,2,3
     *
     * we need to find 3 number, i < j < k, and a[i] + a[j] > a[k];
     *
     * @param nums
     * @return
     */
    public static int triangleNumber(int[] nums) {
        int count = 0;
        if (nums.length < 3)
            return count;
        Arrays.sort(nums);
        for (int i = 2; i < nums.length; i++) {
            int left = 0, right = i - 1;
            while (left < right) {
                // Found a triplet
                if (nums[left] + nums[right] > nums[i]) {
                    // All the elements from left to right-1 are valid
                    count += right - left;
                    right--;
                } else
                    left++;
            }
        }
        return count;
    }

    /**
     * Give you an integer array (index from 0 to n-1, where n is the size of
     * this array, value from 0 to 10000) and an query list. For each query,
     * give you an integer, return the number of element in the array that are
     * smaller than the given integer.
     *
     * For array [1,2,7,8,5], and queries [1,8,5], <br>
     * return [0,4,2]
     *
     * @param a
     * @param queries
     * @return
     */
    public static ArrayList<Integer> countOfSmallerNumber(int[] a, int[] queries) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        // Sort the input array
        Arrays.sort(a);

        for (int i = 0; i < queries.length; i++) {
            int id2 = binarySearchUtil(a, 0, a.length - 1, queries[i]);
            list.add(id2);
        }

        return list;
    }

    private static int binarySearchUtil(int arr[], int low, int high, int x) {
        if (high >= low) {
            int mid = low + (high - low) / 2;
            if ((mid == 0 || x > arr[mid - 1]) && arr[mid] == x)
                return mid;
            else if (x > arr[mid])
                return binarySearchUtil(arr, (mid + 1), high, x);
            else
                return binarySearchUtil(arr, low, (mid - 1), x);
        }
        return low;
    }

    /**
     * Check whether an integer is a palindrome or not.
     *
     * @param num
     * @return
     */
    public static boolean palindromeNumber(int num) {
        // check for negative numbers or multiples of 10's
        if (num < 0 || (num != 0 && num % 10 == 0))
            return false;
        int rev = 0;

        while (num > rev) {
            rev = rev * 10 + num % 10;
            num /= 10;
        }

        return (num == rev || num == rev / 10);
    }

    /**
     * You are given a map in form of a two-dimensional integer grid where 1
     * represents land and 0 represents water. Grid cells are connected
     * horizontally/vertically (not diagonally). The grid is completely
     * surrounded by water, and there is exactly one island (i.e., one or more
     * connected land cells). The island doesn't have "lakes" (water inside that
     * isn't connected to the water around the island). One cell is a square
     * with side length 1. The grid is rectangular, width and height don't
     * exceed 100. Determine the perimeter of the island. <br>
     * <b> Example:</b> <br>
     * [[0,1,0,0], <br>
     * [1,1,1,0], <br>
     * [0,1,0,0], <br>
     * [1,1,0,0]] <br>
     * <br>
     * <b>Answer</b>: 16
     *
     * @param grid
     * @return
     */
    public static int islandPerimeter(int[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    count += 4; // 4 edges of island
                    // Check for prev connected island in row (left)
                    if (i > 0 && grid[i - 1][j] == 1)
                        // remove 2 connected row internal (vertical) edges
                        count -= 2;
                    // Check for prev connected island in column (up)
                    if (j > 0 && grid[i][j - 1] == 1)
                        // remove 2 connected column internal (horizontal) edges
                        count -= 2;
                }
            }
        }

        return count;
    }

    /**
     * Given a sorted array with possibly duplicate elements, the task is to
     * find indexes of first and last occurrences of an element x in the given
     * array.
     *
     * Examples: <br>
     * <b>Input </b>: arr[] = {1, 3, 5, 5, 5, 5 ,67, 123, 125}, key = 5 <br>
     * <b>Output </b>: First Occurrence = 2 || Last Occurrence = 5<br>
     * <br>
     * <b>Input </b>: arr[] = {1, 3, 5, 5, 5, 5 ,7, 123 ,125 }, key = 7 <br>
     * <b>Output </b>: First Occurrence = 6 || Last Occurrence = 6<br>
     *
     * @param a
     * @param low
     * @param high
     * @param key
     * @return
     */
    @SuppressWarnings("unused")
    private static int binarySearchIterative(int[] a, int low, int high, int key) {
        int found = -1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                found = mid;
                // For last occurrence:
                low = mid + 1;
                // For first occurrence:
                // high = mid - 1;
            }
        }
        // For previous index before target when target is not present (first
        // occurrence)
        // return Math.max(found, low);

        return found; // return either first or last occurrence as requested
    }

    /**
     * The set S originally contains numbers from 1 to n. But unfortunately, due
     * to the data error, one of the numbers in the set got duplicated to
     * another number in the set, which results in repetition of one number and
     * loss of another number. <br>
     * <br>
     * Given an array nums representing the data status of this set after the
     * error. Your task is to firstly find the number occurs twice and then find
     * the number that is missing. Return them in the form of an array. <br>
     * <br>
     * <b>Input</b>: [1,2,2,4] <br>
     * <b>Output</b>: [2,3]
     *
     * @param nums
     * @return
     */
    public static int[] findErrorNums(int[] nums) {

        int[] res = new int[2];
        int[] count = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++)
            count[nums[i]]++;
        for (int i = 1; i < count.length; i++) {
            if (count[i] == 2)
                res[0] = i;
            if (count[i] == 0)
                res[1] = i;
        }
        return res;
    }

    /**
     * You are given n pairs of numbers. In every pair, the first number is
     * always smaller than the second number. Now, we define a pair (c, d) can
     * follow another pair (a, b) if and only if b < c. Chain of pairs can be
     * formed in this fashion. <br>
     * <br>
     * Given a set of pairs, find the length longest chain which can be formed.
     * You needn't use up all the given pairs. You can select pairs in any
     * order.<br>
     * <br>
     * <b>Input</b>: [[1,2], [2,3], [3,4]] <br>
     * <b>Output</b>: 2 <br>
     * <b>Explanation</b>: The longest chain is [1,2] -> [3,4]<br>
     * <br>
     * <b>Input</b>: [[5, 24],[27, 40],[39, 60], [15, 28], [50, 90]] <br>
     * <b>Output</b>: 3 <br>
     * <b>Explanation</b>: The longest chain is [5,24] -> [27,40] -> [50,90]<br>
     *
     * @param pairs
     * @return
     */
    public static int findLongestChain(int[][] pairs) {

        if (pairs.length == 0)
            return 0;
        int len = pairs.length;
        // Sort the pairs by their end values
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        // You can also sort using comparator
        // Arrays.sort(pairs, new Comparator<int[]>() {
        // public int compare(int[] a, int[] b) {
        // return a[1] - b[1];
        // }
        // });
        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        for (int i = 1; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                int currEnd = pairs[j][1];
                int nextBegin = pairs[i][0];
                // Check if chain condition satisfies
                if (currEnd < nextBegin) {
                    // Update dp table
                    if (dp[i] < dp[j] + 1)
                        dp[i] = dp[j] + 1;
                }
            }
        }
        // Return the maximum value from memoization table
        Arrays.sort(dp);
        return dp[len - 1];
    }

    private static int palindromeCount = 0;

    /**
     * Given a string, your task is to count how many palindromic substrings in
     * this string. The substrings with different start indexes or end indexes
     * are counted as different substrings even they consist of same characters.
     * Find all distinct/unique palindromic substrings from a given string. <br>
     * <b>Input</b>: "abc" <br>
     * <b>Output</b>: 3 <br>
     * <b>Explanation</b>: Three palindromic strings: "a", "b", "c". <br>
     * <br>
     * <b>Input</b>: "aaa" <br>
     * <b>Output</b>: 6 <br>
     * <b>Explanation</b>: Six palindromic strings: "a", "a", "a", "aa", "aa",
     * "aaa".
     *
     * @param input
     * @return
     */
    public static int countSubstrings(final String input) {

        // In case of finding list of palindromic substrings
        // List<String> palindromeList = new ArrayList<String>();

        // Iterate over string keeping i as the center
        for (int i = 0; i < input.length(); i++) {
            // expanding odd length palindromes with (i) as center:
            expandPalindromes(input, i, i);
            // expanding even length palindromes with (i, i+1) center:
            expandPalindromes(input, i, i + 1);
        }
        // return palindromeList; // return list of unique palindromes
        return palindromeCount;
    }

    private static void expandPalindromes(final String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            // palindromeList.add(s.substring(i, j + 1));
            palindromeCount++;
            i--;
            j++;
        }
    }

    /**
     * In English, we have a concept called root, which can be followed by some
     * other words to form another longer word - let's call this word successor.
     * For example, the root an, followed by other, which can form another word
     * another. <br>
     * Now, given a dictionary consisting of many roots and a sentence. You need
     * to replace all the successor in the sentence with the root forming it. If
     * a successor has many roots can form it, replace it with the root with the
     * shortest length.
     *
     * You need to output the sentence after the replacement. <br>
     * <br>
     * <b>Input</b>: dict = ["cat", "bat", "rat"] <br>
     * <b>Sentence</b> = "the cattle was rattled by the battery" <br>
     * <b>Output</b>: "the cat was rat by the bat"
     *
     * @param dict
     * @param sentence
     * @return
     */
    public static String replaceWords(List<String> dict, String sentence) {
        String[] words = sentence.split(" ");

        Set<String> set = new HashSet<String>();
        for (String d : dict) {
            set.add(d);
        }

        StringBuilder result = new StringBuilder();
        StringBuffer prefix = new StringBuffer();

        for (String word : words) {
            prefix.setLength(0);
            for (int i = 0; i < word.length(); i++) {
                prefix.append(Character.toString(word.charAt(i)));
                if (set.contains(prefix.toString()))
                    break;
            }
            result.append(prefix.toString() + " ");
        }
        return result.toString().trim();
    }

    /**
     * Given an array nums, write a function to move all 0's to the end of it
     * while maintaining the relative order of the non-zero elements. <br>
     * <b>Input</b> = [0, 1, 0, 3, 12], <br>
     * <b>Output</b> = [1, 3, 12, 0, 0]. <br>
     * <code>Note: You must do this in-place without making a copy of the array.
     * Minimize the total number of operations.</code>
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // Move all the non-zero numbers to the front of the array
            if (nums[i] != 0) {
                // int temp = nums[i];
                // nums[i] = nums[j];
                // nums[j] = temp;
                swap(nums, i, j);
                j++;
            }
        }
        return;
    }

    /**
     * Given an array and a value, remove all instances of that value in place
     * and return the new length. <br>
     * Do not allocate extra space for another array, you must do this in place
     * with constant memory. <br>
     * The order of elements can be changed. It doesn't matter what you leave
     * beyond the new length. <br>
     * <br>
     * <b>Input</b> = [3,2,2,3], val = 3 <br>
     *
     * Your function should return length = 2, with the first two elements of
     * nums being 2.
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[start++] = nums[i];
            }
        }
        return start;
    }

    /**
     * Given a string which consists of lowercase or uppercase letters, find the
     * length of the longest palindromes that can be built with those letters.
     * <br>
     * This is case sensitive, for example "Aa" is not considered a palindrome
     * here. <br>
     * <br>
     * <b>Input</b> : "abccccdd" <br>
     * <b>Output</b>: 7 <br>
     * <b>Explanation</b>: One longest palindrome that can be built is
     * "dccaccd", whose length is 7.
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        int len = 0;
        Set<Character> set = new HashSet<Character>();

        for (int i = 0; i < s.length(); i++) {
            // check for repeating chars that can be used for building
            // palindrome
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(i));
                len++;
            } else
                set.add(s.charAt(i));
        }

        if (!set.isEmpty()) // set is not empty
            return len * 2 + 1; // so odd length palindrome

        return len * 2; // even length palindrome
    }

    /**
     * Given two strings s and t, determine if they are isomorphic.
     *
     * Two strings are isomorphic if the characters in s can be replaced to get
     * t.
     *
     * All occurrences of a character must be replaced with another character
     * while preserving the order of characters. No two characters may map to
     * the same character but a character may map to itself.
     *
     * For example, Given "egg", "add", return true.
     *
     * Given "foo", "bar", return false.
     *
     * Given "paper", "title", return true.
     *
     * You may assume both s and t have the same length.
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isIsomorphic(String s, String t) {
        int[] s1 = new int[256];
        int[] t1 = new int[256];
        for (int i = 0; i < s.length(); i++) {
            // Check the occurrences of both letters in their corres. words are
            // same
            if (s1[s.charAt(i)] != t1[t.charAt(i)])
                return false;
            // Store the char indices in occurrence array
            s1[s.charAt(i)] = i + 1; // +1 for zero'th char index
            t1[t.charAt(i)] = i + 1;
        }
        return true;
    }

    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start'
     * in the diagram below).
     *
     * The robot can only move either down or right at any point in time. The
     * robot is trying to reach the bottom-right corner of the grid (marked
     * 'Finish' in the diagram below).
     *
     * How many possible unique paths are there?
     *
     * @param m
     *            No of rows
     * @param n
     *            No of columns
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int[][] tab = new int[m][n]; // initialize the memoization table
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) // can't go left or up
                    tab[i][j] = 1;
                else
                    tab[i][j] = tab[i - 1][j] + tab[i][j - 1];
            }
        }
        return tab[m - 1][n - 1];
    }

    /**
     * Follow up for "Remove Duplicates": What if duplicates are allowed at most
     * twice?
     *
     * For example, Given sorted array A = [1,1,1,2,2,3],
     *
     * Your function should return length = 5, and A is now [1,1,2,2,3].
     *
     * @param nums
     * @return
     */
    public static int removeDuplicatesII(int[] nums) {
        int j = 1;
        int count = 1;
        if (nums.length < 2)
            return nums.length;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j++] = nums[i];
                count = 1;
            } else {
                if (count < 2) {
                    nums[j++] = nums[i];
                    count++;
                }
            }
        }
        return j;
    }

    /**
     * Given an array of size n, find the majority element. The majority element
     * is the element that appears more than n/2 times.
     *
     * You may assume that the array is non-empty and the majority element
     * always exist in the array. Given [1, 1, 1, 1, 2, 2, 2], return 1
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int candidate = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            // Select/change the candidate for majority element
            if (count == 0) {
                candidate = nums[i];
                count++;
            } else if (candidate == nums[i]) {
                // If selected candidate, increment count
                count++;
            } else
                // If other than candidate, decrement count
                count--;
        }
        return candidate;
    }

/**
 * Given an array of integers that is already sorted in ascending order,
 * find two numbers such that they add up to a specific target number. <br>
 * <br>
 * The function twoSum should return indices of the two numbers such that
 * they add up to the target, where index1 must be less than index2. Please
 * note that your returned answers (both index1 and index2) are not
 * zero-based. <br>
 * <br>
 * You may assume that each input would have exactly one solution and you
 * may not use the same element twice. <br>
 * <br>
 * <b>Input</b>: numbers = {2, 7, 11, 15}, target = <b>9</b> <br>
 * <b>Output</b>: index1 = <b>1</b>, index2 = <b>2</b>
 *
 * @param numbers
 * @param target
 * @return
 */
public static int[] twoSumAscArr(int[] numbers, int target) {
    int left = 0;
    int right = numbers.length - 1;
    int[] indices = new int[2];
    while (left < right) {
        int sum = numbers[left] + numbers[right];
        if (sum == target) {
            indices[0] = left + 1;
            indices[1] = right + 1;
            break;
        } else if (sum > target) {
            right--;
        } else
            left++;
    }
    return indices;
}

    /**
     * Find all possible combinations of k numbers that add up to a number n,
     * given that only numbers from 1 to 9 can be used and each combination
     * should be a unique set of numbers. <br>
     * <br>
     * Input: k = 3, <b>n = 7</b><br>
     * Output: <b>[[1,2,4]] </b><br>
     * <br>
     *
     * Input: k = 3, <b>n = 9</b><br>
     * Output: <b>[[1,2,6], [1,3,5], [2,3,4]]</b> <br>
     *
     * @param k
     * @param n
     * @return
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        int[] nums = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        combSum3Helper(result, nums, new TreeSet<Integer>(), 1, k, n);
        return result;
    }

    public static void combSum3Helper(List<List<Integer>> result, int nums[], TreeSet<Integer> set, int start, int k,
                                      int target) {
        if (set.size() > k)
            return;

        if ((target == 0) && set.size() == k) {
            List<Integer> list = new ArrayList<Integer>(set);
            result.add(list);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            set.add(nums[i]);
            combSum3Helper(result, nums, set, i + 1, k, target - nums[i]);
            // backtrack by removing last element in the set
            set.remove(set.last());
        }
    }

    /**
     * Given a collection of candidate numbers (C) and a target number (T), find
     * all unique combinations in C where the candidate numbers sums to T. <br>
     * <code>Each number in C may only be used once in the combination.
     * </code><br>
     * <b>Note</b>: All numbers (including target) will be positive integers.
     * The solution set <b>must not</b> contain duplicate combinations. <br>
     * <br>
     * <b>Input</b>: Candidate Set = [10, 1, 2, 7, 6, 1, 5], <b>Target = 8</b>,
     * <br>
     * <b>Output</b>: [ [1, 7], <br>
     * [1, 2, 5], <br>
     * [2, 6], <br>
     * [1, 1, 6] ]<br>
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        combSum2Helper(result, candidates, new ArrayList<Integer>(), 0, target);
        return result;
    }

    public static void combSum2Helper(List<List<Integer>> result, int nums[], ArrayList<Integer> list, int start,
                                      int target) {
        if (target < 0)
            return;

        if (target == 0) {
            result.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1])
                continue; // skip duplicates in the result list
            list.add(nums[i]);
            combSum2Helper(result, nums, list, i + 1, target - nums[i]);
            // backtrack by removing last element in the set
            list.remove(list.size() - 1);
        }
    }

    /**
     * Given a set of candidate numbers (C) (without duplicates) and a target
     * number (T), find all unique combinations in C where the candidate numbers
     * sums to T. <br>
     * The <b>same repeated number </b>may be chosen from C <b>unlimited</b>
     * number of times. <br>
     * <br>
     * <b>Note</b>: All numbers (including target) will be positive integers.
     * The solution set <b>must not</b> contain duplicate combinations. <br>
     * <br>
     * <b>Input</b>: Candidate Set [2, 3, 6, 7], Target = 7<br>
     * <b>Output</b>: [ <br>
     * [7], <br>
     * [2, 2, 3] ]<br>
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        combSumHelper(result, candidates, new ArrayList<Integer>(), 0, target);
        return result;
    }

    public static void combSumHelper(List<List<Integer>> result, int nums[], ArrayList<Integer> list, int start,
                                     int target) {
        if (target < 0)
            return;

        if (target == 0) {
            result.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = start; i < nums.length && i < target; i++) {
            list.add(nums[i]);
            // pass i and not i + 1 because we can reuse same element
            combSumHelper(result, nums, list, i, target - nums[i]);
            // backtrack by removing last element in the set
            list.remove(list.size() - 1);
        }
    }

    /**
     * Rotate an array of n elements to the right by r steps.
     *
     * <br>
     * <b>Input</b>: n = 7 and <b>r = 3</b>, the array = [1,2,3,4,5,6,7] <br>
     * <b>Output</b>: [5,6,7,1,2,3,4].
     *
     * @param nums
     * @param r
     */
    public static void rotateRight(int[] nums, int r) {
        for (int i = 0; i < r; i++) {
            // store last element
            int temp = nums[nums.length - 1], j = nums.length - 1, k = nums.length - 2;
            while (j > 0) {
                nums[j--] = nums[k--];
            }
            nums[j] = temp;
        }
        // alternative approach using array reversal

        // change the Collections.reverse with normal user-defined function
        // for the purpose of modifying nums array in-place
        // r %= nums.length;
        // List<Integer> list =
        // IntStream.of(nums).boxed().collect(Collectors.toList());
        // Collections.reverse(list);
        // Collections.reverse(list.subList(0, r));
        // Collections.reverse(list.subList(r, nums.length));

    }

    /**
     * A zero-indexed array A consisting of N different integers is given.
     * <code>The
     * array contains all integers in the range [0, N - 1].</code> <br>
     * <br>
     * <code>Sets S[K] for 0 <= K < N are defined as follows: <br>
     * S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }. </code><br>
     * <b>Sets S[K] are finite for each K and should NOT contain duplicates.
     * </b><br>
     * Write a function that given an array A consisting of N integers,
     * <b>return the size of the largest set S[K]</b> for this array. <br>
     * <br>
     * <b>Input</b>: A = [5,4,0,3,1,6,2] <b>Output: 4 </b><br>
     * <b>Explanation</b>: <br>
     * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
     * <br>
     * <br>
     * One of the longest S[K]: <br>
     * <code> S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}</code>
     *
     * @param nums
     * @return
     */
    public static int arrayNesting(int[] nums) {
        int max = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = 0;
            // iterate the chain and count
            while (nums[i] != -1) {
                int temp = i;
                i = nums[i];
                // mark visited
                nums[temp] = -1;
                count++;
            }
            // i = temp;
            max = Math.max(max, count);
        }
        return max;
    }

    /**
     * Given a string and an offset, rotate string by offset. (rotate from left
     * to right).<br>
     * <br>
     *
     * <b>Input: "abcdefg" </b><br>
     * <br>
     * offset=0 => <i>"abcdefg" </i><br>
     * offset=1 => <i>"gabcdef" </i><br>
     * offset=2 => <i>"fgabcde" </i><br>
     * offset=3 => <i>"efgabcd" </i><br>
     *
     * @param str
     * @param offset
     */
    public static void rotateString(char[] str, int offset) {
        int len = str.length;
        offset %= len;
        // System.out.println("Original letters = " + new String(str));
        reverseChars(str, 0, len - offset - 1);
        reverseChars(str, len - offset, len - 1);
        reverseChars(str, 0, len - 1);
        // System.out.println("Transformed letters = " + new String(str));
        return;
    }

    private static void reverseChars(char[] chars, int beginIndex, int endIndex) {
        while (beginIndex < endIndex) {
            char temp = chars[beginIndex];
            chars[beginIndex++] = chars[endIndex];
            chars[endIndex--] = temp;
        }
    }

    /**
     * Given a rotated sorted array, recover it to sorted array in-place. <br>
     * <br>
     * For example, the orginal array is <code>[1,2,3,4],</code> <br>
     * The rotated array of it can be
     * <code>[1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]</code> <br>
     * <br>
     * Example {@code [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]}<br>
     *
     * @param nums
     */
    public static void recoverRotatedSortedArray(List<Integer> nums) {
        if (nums.isEmpty() || nums == null)
            return;

        int temp = nums.get(0);

        for (int i = 0; i < nums.size(); i++) {
            if (temp > nums.get(i)) {
                temp = i;
                // System.out.println("Break point = "+temp);
                break;
            }
            temp = nums.get(i);
        }
        int len = nums.size();
        Collections.reverse(nums.subList(0, temp));
        Collections.reverse(nums.subList(temp, len));
        Collections.reverse(nums.subList(0, len));
        return;
    }

    /**
     * Given an unsorted array, find the maximum difference between the
     * successive elements in its sorted form.
     *
     * Return 0 if the array contains less than 2 elements.
     *
     * @param nums
     * @return
     */
    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int max = nums[0], mGap = 0;
        // Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i], max);
        }

        int exp = 1; // power 1, 10, 100, 1000....
        int radix = 10; // base 10 system

        int[] sorted = new int[nums.length];

        // sort the array using radix sort in O(n) time
        while (max / exp > 0) {
            int[] count = new int[radix];

            // start from the LSD (least significant digit)
            // counting sort
            for (int i = 0; i < nums.length; i++) {
                // count[(nums[i] / exp) % 10]++;
                count[getDigit(nums[i], exp)]++;
            }

            // linear sum the bucket count as per the number of elements inside
            // each bucket
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            // fill auxiliary array with the sorted results per lsd / digit
            for (int i = nums.length - 1; i >= 0; i--) {
                // sorted[--count[(nums[i] / exp) % 10]] = nums[i];
                // get the digit place for the current nums[i] element
                int digit = getDigit(nums[i], exp);

                // fill the aux array with the current element and
                // remove the element from that digits' bucket
                sorted[--count[digit]] = nums[i];
            }

            // update the original array with the sorted results
            for (int i = 0; i < nums.length; i++) {
                nums[i] = sorted[i];
            }

            // iterate the digit from right to left
            // 1 --> 10 --> 100 ...
            exp *= 10;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            mGap = Math.max(nums[i + 1] - nums[i], mGap);
        }
        return mGap;
    }

    private static int getDigit(int value, int digitPlace) {
        return ((value / digitPlace) % 10);
    }

    /**
     * Given k strings, find the longest common prefix (LCP). <br>
     * <br>
     * Example <br>
     * For strings "ABCD", "ABEF" and "ACEF", <br>
     * the LCP is "A"<br>
     * <br>
     * For strings "ABCDEFG", "ABCEFG" and "ABCEFA", <br>
     * the LCP is "ABC"<br>
     * <br>
     * For strings ["ca","a"]<br>
     * the LCP is "" <br>
     * <br>
     * For strings "aca","cba" <br>
     * the LCP is ""
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        StringBuffer lcp = new StringBuffer();
        int minLen = strs[0].length(), strIndex = 0;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLen) {
                minLen = strs[i].length();
                strIndex = i;
            }
        }

        // If all strings are of same size then don't shift
        if (strs[0].length() != minLen) {
            // Shift the smallest string to the first position
            String temp = strs[0];
            strs[0] = strs[strIndex];
            strs[strIndex] = temp;
        }

        for (int i = 0; i < minLen; i++) {
            // Iterate the letters of first string through entire array of
            // strings by keeping it as prefix
            char pre = strs[0].charAt(i);
            lcp.append(pre);
            for (int j = 1; j < strs.length; j++) {
                if ((strs[j].charAt(i)) != pre) {
                    lcp.deleteCharAt(lcp.length() - 1);
                    // If lcp becomes zero then do not continue to check rest of
                    // the strings. ex --> [acad, cban] == equal lengths &&
                    // second last char is same
                    if (lcp.length() == 0)
                        return "";
                    break;
                }
            }
        }
        return lcp.toString();
    }

    /**
     * Given an array of strings, group anagrams together. <br>
     * <br>
     * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], <br>
     * Return:
     *
     * [ ["ate", "eat","tea"], <br>
     * ["nat","tan"], <br>
     * ["bat"] ]
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (strs == null || strs.length == 0)
            return result;

        Map<String, List<String>> map = new HashMap<String, List<String>>();

        // To get anagrams, sort the characters inside a string
        // for ex. --> dab, adb --> both are anagrams --> abd , abd
        for (String word : strs) {
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            String keyStr = String.valueOf(letters);
            // Check if map has the chars then put the current string in a new
            // list
            if (!map.containsKey(keyStr))
                map.put(keyStr, new ArrayList<String>());
            // add the current word to the list
            map.get(keyStr).add(word);
        }

        result.addAll(map.values());
        return result;
    }

    /**
     * Given a non-negative number represented as an array of digits, plus one
     * to the number. <br>
     * <br>
     * The digits are stored such that the most significant digit is at the head
     * of the list. <br>
     * <br>
     * Given [1,2,3] which represents 123, <br>
     * return [1,2,4].<br>
     * <br>
     *
     * Given [9,9,9] which represents 999, <br>
     * return [1,0,0,0].
     *
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            // start from the last digit (LSB) of the number
            if (digits[i] < 9) {
                // if the last digit is < 9 then
                // return the number adding one to last digit
                // 1, 2, 3 --> 1, 2, 4
                digits[i]++;
                return digits;
            }
            // if the digit is 9 then replace it with 0
            // 9, 9 --> 0, 0
            digits[i] = 0;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    /**
     * Reverse digits of an integer.<br>
     * {@code Returns 0 when the reversed integer overflows (signed 32-bit integer).}
     * <br>
     * <br>
     * Given x = <b>123</b>, return <b>321</b> <br>
     * Given x = <b>-123</b>, return <b>-321</b>
     *
     * @param n
     * @return
     */
    public int reverseInteger(int n) {
        long rev = 0;
        // check for x is not 0 condition and not for x > 0 condition
        // because number can also be negative
        while (n != 0) {
            rev = rev * 10 + n % 10;
            if (rev > Integer.MAX_VALUE) {
                // handle the integer overflows error (signed 32-bit integer)
                return 0;
            }
            n = n / 10;
        }
        return (int) rev;
    }

    /**
     * You are climbing a stair case. It takes n steps to reach to the top.
     *
     * Each time you can either climb 1 or 2 steps. In how many distinct ways
     * can you climb to the top?
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {

        // this problem is related to the fibonacci series problem
        // return climbStairs (n-1) + climbStairs(n - 2); // TLE --> Time Limit
        // Exceeded
        if (n < 2)
            return 1;

        int dp[] = new int[n + 1]; // O(n) linear space
        // int first = 1, second = 2; // O(1) constant space
        dp[1] = 1;
        dp[2] = 2;
        // you can get to the i'th step from (i - 1)th last step or (i - 2)th
        // step -- second to last step --> just like in a fibonacci series
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            // int third = first + second;
            // first = second;
            // second = third;
        }
        return dp[n];
        // return second;
    }

    /**
     * Given an array nums of integers and an int k, partition the array (i.e
     * move the elements in "nums") such that: <br>
     * <br>
     * All elements < k are moved to the left <br>
     * All elements >= k are moved to the right <br>
     * Return the partitioning index, i.e the first index i nums[i] >= k. <br>
     * <br>
     * If nums = [3,2,2,1] and k=2, <br>
     * a valid answer is <b>1</b> --> (array index after partitioning).
     *
     * @param nums
     * @param k
     * @return
     */
    public static int partitionArray(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        // perform cyclic arrangements
        while (left <= right) {
            // lower numbers
            while (left <= right && nums[left] < k) {
                left++;
            }
            // higher numbers
            while (left <= right && nums[right] >= k) {
                right--;
            }
            // swap
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        // partitioning index
        return left;
    }

    /**
     * Given a sorted array, two integers k and x, find the k closest elements
     * to x in the array. The result should also be sorted in ascending order.
     * If there is a tie, the smaller elements are always preferred. <br>
     * <br>
     * Input: [1,2,3,4,5], k=4, x=3 <br>
     * Output: [1,2,3,4] <br>
     * <br>
     * Input: [1,2,3,4,5], k=4, x=-1 <br>
     * Output: [1,2,3,4]
     *
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public static List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        List<Integer> result = new ArrayList<Integer>();
        // validations check
        if (arr.size() < 2 || arr == null)
            return arr;
        // sort the input array in asc order
        // Collections.sort(arr); // array is already given in sorted order

        // binary search the element x
        int index = getClosestIndex(arr, x);
        // int index = search(x, arr.stream().mapToInt(i -> i).toArray());

        int left = index;
        int right = left + 1;

        // if element is found in input array then add that to the result array
        if (x == arr.get(index)) {
            left--;
            result.add(x);
            k--;
        }
        // explore elements from the found index
        while (left >= 0 && right < arr.size() && k > 0) {
            int ld = Math.abs((x - arr.get(left)));
            int rd = Math.abs((x - arr.get(right)));
            if (ld <= rd)
                result.add(arr.get(left--));
            else
                result.add(arr.get(right++));
            k--;
        }
        // add remaining elements from the left
        while (k > 0 && left >= 0) {
            result.add(arr.get(left--));
            k--;
        }
        // add the remaining elements from the right
        while (k > 0 && right < arr.size()) {
            result.add(arr.get(right++));
            k--;
        }
        // sort the result in asc order
        Collections.sort(result);
        return result;
    }

    /**
     * Returns the index of closest value to given input value in an arraylist.
     *
     * @param arr
     * @param key
     * @return
     */
    private static int getClosestIndex(List<Integer> arr, int key) {
        if (key < arr.get(0)) {
            return arr.get(0);
        } // lower boundary
        if (key > arr.get(arr.size() - 1)) {
            return arr.get(arr.size() - 1);
        } // upper boundary
        int pos = Collections.binarySearch(arr, key);
        if (pos >= 0) {
            // we found an exact match
            return pos;
        }
        // we didn't find an exact match, now we have two candidates:
        // insertion point and insertion point-1 (we excluded the trivial
        // case before)
        // pos = -ip-1 | +ip -pos => ip = -pos-1
        int insertionP = -pos - 1;
        int closest;
        if (arr.get(insertionP) - key < key - arr.get(insertionP - 1)) {
            closest = insertionP;
        } // < can be <= if smaller value is preferred
        else {
            closest = insertionP - 1;
        }
        return closest;
    }

    /**
     * Returns the index of closest value to given input value in an array.
     *
     * @param value
     * @param a
     * @return
     */
    private static int search(int value, int[] a) {

        if (value < a[0]) {
            return a[0];
        }
        if (value > a[a.length - 1]) {
            return a[a.length - 1];
        }

        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = (hi + lo) / 2;

            if (value < a[mid]) {
                hi = mid - 1;
            } else if (value > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        // lo == hi + 1
        return (a[lo] - value) < (value - a[hi]) ? lo : hi;
    }

    /**
     * Initially, there is a Robot at position (0, 0). Given a sequence of its
     * moves, judge if this robot makes a circle, which means it moves back to
     * the original place. <br>
     * The move sequence is represented by a string. And each move is represent
     * by a character. The valid robot moves are R (Right), L (Left), U (Up) and
     * D (down). The output should be true or false representing whether the
     * robot makes a circle. <br>
     * <br>
     * Input: "UD" Output: <b>TRUE</b> <br>
     * <br>
     * Input: "LL" Output: <b>FALSE</b>
     *
     * @param moves
     * @return
     */
    public static boolean judgeCircle(String moves) {
        int lc = 0, uc = 0;

        for (int i = 0; i < moves.length(); i++) {
            Character c = moves.charAt(i);
            if (c == 'L') {
                lc++;
            } else if (c == 'U') {
                uc++;
            } else if (c == 'R') {
                lc--;
            } else if (c == 'D') {
                uc--;
            }
        }
        return (lc == 0 && uc == 0);
    }

    /**
     * Start from integer 1, remove any integer that contains 9 such as 9, 19,
     * 29... <br>
     * <br>
     * So now, you will have a new integer sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10,
     * 11, ... <br>
     * <br>
     * Given a positive integer n, you need to return the n-th integer after
     * removing. <br>
     * {@code Note that 1 will be the first integer. }<br>
     * <br>
     * <b>Input: 9 Output: 10</b>
     *
     * @param n
     * @return
     */
    public int newInteger(int n) {
        // one liner solution
        // instead of base 10, select the radix as 9 in toString representation
        // return Integer.parseInt(Integer.toString(n, 9));
        int rv = 0;
        int base = 1;

        while (n > 0) {
            int remainder = n % 9;
            rv += remainder * base;
            n = n / 9;
            base = base * 10;
        }

        return rv;
    }

    /**
     * Say you have an array for which the ith element is the price of a given
     * stock on day i.<br>
     * <br>
     *
     * If you were only permitted to complete at most one transaction (ie, buy
     * one and sell one share of the stock), design an algorithm to find the
     * maximum profit.<br>
     * <br>
     * Input: [7, 1, 5, 3, 6, 4] Output: 5 <br>
     * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be
     * larger than buying price) <br>
     * <br>
     * Input: [7, 6, 4, 3, 1] Output: 0 <br>
     * In this case, no transaction is done, i.e. max profit = 0.
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int maxProf = 0, currProf = 0, minPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(prices[i], minPrice);

            currProf = prices[i] - minPrice;
            maxProf = Math.max(currProf, maxProf);
        }

        return maxProf;
    }

    /**
     * Say you have an array for which the ith element is the price of a given
     * stock on day i. <br>
     * <br>
     * Design an algorithm to find the maximum profit. You may complete as many
     * transactions as you like (ie, buy one and sell one share of the stock
     * multiple times). <br>
     * <br>
     * However, you may not engage in multiple transactions at the same time
     * (ie, you must sell the stock before you buy again). <br>
     * <br>
     * Given an example [2,1,2,0,1], return 2
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int maxProf = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i])
                maxProf += prices[i] - prices[i - 1];
        }
        return maxProf;
    }

    /**
     * Given a non-empty string s and a dictionary wordDict containing a list of
     * non-empty words, determine if s can be segmented into a space-separated
     * sequence of one or more dictionary words. You may assume the dictionary
     * does not contain duplicate words. <br>
     * <br>
     * <b>Input:</b> s = "leetcode", dict = ["leet", "code"]. <br>
     * <b>Output:</b> Return <b>true</b> because "leetcode" can be segmented as
     * "leet code".
     *
     * @param s
     * @param dict
     * @return
     */
    public boolean wordBreak(String s, Set<String> dict) {

        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true; // set the intial index as true

        for (int i = 1; i <= s.length(); i++) {
            // check for each char/substring in the given word if it exists
            // in the dict until last char
            for (int j = 0; j < i; j++) {
                // iterate for the whole word starting from j to last index as i
                if (dp[j] != false) {
                    // check if the prev char/prefix was present in the dict
                    if (dict.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        // if last char is present/true in the dp[] then return true
        return dp[s.length()];
    }

    /**
     * Given two sorted integer arrays A and B, merge B into A as one sorted
     * array. <br>
     * <br>
     * You may assume that A has enough space (size that is greater or equal to
     * m + n) to hold additional elements from B. The number of elements
     * initialized in A and B are m and n respectively. <br>
     * <br>
     * <b>Input</b>: A = [1, 2, 3, empty, empty], B = [4, 5] <br>
     * <b>Output</b>: After merge, A will be filled as [1, 2, 3, 4, 5]
     *
     * @param A
     *            Input array 1
     * @param m
     *            no of integers in array A
     * @param B
     *            Inpute array 2
     * @param n
     *            no of integers in array B
     */
    public static void mergeSortedArray(int[] A, int m, int[] B, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;

        // start filling the array from back
        while (i >= 0 && j >= 0) {
            A[k--] = (A[i] > B[j]) ? A[i--] : B[j--];
        }

        // fill the remaining elements
        while (j >= 0) {
            A[k--] = B[j--];
        }
    }

    /**
     * Suppose a sorted array is rotated at some pivot unknown to you
     * beforehand.
     *
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     *
     * You are given a target value to search. If found in the array return its
     * index, otherwise return -1.
     *
     * You may assume no duplicate exists in the array. For [4, 5, 1, 2, 3] and
     * target=1, return 2.
     *
     * For [4, 5, 1, 2, 3] and target=0, return -1.
     *
     * @param arr
     * @param low
     * @param high
     * @param x
     * @return
     */
    public static int binSearchRotatedArrIterative(int[] arr, int low, int high, int x) {
        // return binSearchRotatedArrIterative(nums, 0, nums.length - 1,
        // target);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == x)
                return mid;
            // lower(left) half is not rotated
            if (arr[low] <= arr[mid] && x <= arr[mid] && arr[low] <= x ||
                    // OR lower(left) half is rotated
                    (arr[low] > arr[mid] && (arr[low] <= x || x <= arr[mid]))) {
                high = mid - 1; // shift the high pointer
            } else // search right
                low = mid + 1;
        }
        return -1;
    }

    /**
     * You are going to receive payments in the upcoming month. The payments are
     * numbered from to and denotes the amount of money in dollars that will be
     * paid in connection with the -th payment. Before receiving the payments,
     * you have two banking options to consider: <br>
     * <br>
     * 1. You can receive all the payments on your current bank account
     * remembering that the bank charges you for each payment i ==> max(k, x %
     * of p[i]), where k and x are given. <br>
     * 2. You can pay the bank dollars upfront to open a new special account for
     * which the bank doesn't charge you for any of the upcoming transactions.
     * <br>
     * <br>
     * Your task is to decide which of the above two options is more profitable
     * to you. If both ways are equally profitable, then you prefer to be
     * charged for each transaction.
     *
     * @param n
     * @param k
     * @param x
     * @param d
     * @param p
     * @return
     */
    public static String feeOrUpfront(int n, int k, int x, int d, int[] p) {
        double totalCharges = 0;
        for (int i = 0; i < p.length; i++) {
            totalCharges += Math.max(k, x * p[i] / 100.0);
        }
        if (totalCharges == d)
            return "fee";
        return (totalCharges < d) ? "fee" : "upfront";
    }

    /**
     * Given an array S of n integers, are there elements a, b, c in S such that
     * a + b + c = 0? Find all unique triplets in the array which gives the sum
     * of zero. <br>
     * <br>
     * <code>Note: The solution set must not contain duplicate triplets. </code><br>
     * <br>
     * <b>Input</b>: [-1, 0, 1, 2, -1, -4], <br>
     * <b>Output</b>:<br>
     * [ [-1, 0, 1], <br>
     * [-1, -1, 2] ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        int sum, low, high;
        // [-1,0,1,2,-1,-4]
        // [-2,0,1,1,2]
        for (int i = 0; i < nums.length - 2; i++) {
            // don't iterate over the repeated elements
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int key = -nums[i]; // compare the sum with negating the curr
                // element
                low = i + 1;
                high = nums.length - 1; // keep indices of two elements
                // binary search for the two elements
                while (low < high) {
                    sum = nums[low] + nums[high];
                    if (sum == key) {
                        // found the triplet
                        res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        // avoid the duplicate elements in the list
                        low++;
                        high--;
                        // increment the index by skipping the same elements
                        while (low < high && nums[low] == nums[low - 1])
                            low++;
                        // skip duplicate pairs with the same high
                        while (low < high && nums[high] == nums[high + 1])
                            high--;
                    } else if (sum < key) {
                        // search in the right half
                        low++;
                    } else
                        high--;
                }
            }
        }
        return res;
    }

    /**
     * Given an array S of n integers, find three integers in S such that the
     * sum is closest to a given number, target. Return the sum of the three
     * integers. You may assume that each input would have exactly one solution.
     * <br>
     * <br>
     * <b>Input</b>: Given array S = {-1 2 1 -4}, and target = 1. <br>
     * <b>Output</b>: The sum that is closest to the target is 2. <br>
     * <b>(-1 + 2 + 1 = 2)</b>.
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                if (sum < target) {
                    left++;
                } else
                    right--;
                // compare the difference between sum and target
                // if it is less than previous diff then replace it
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }

    /**
     * Given a string which contains only letters. Sort it by lower case first
     * and upper case second. <br>
     * <br>
     * It's NOT necessary to keep the original order of lower-case letters and
     * upper case letters. <br>
     * <br>
     * For "abAcD", a reasonable answer is "acbAD"
     *
     * @param chars
     */
    public void sortLetters(char[] chars) {
        int l = 0, r = chars.length - 1;
        while (l < r) {
            while (l < r && Character.isLowerCase(chars[l]))
                l++;
            while (l < r && Character.isUpperCase(chars[r]))
                r--;
            // swap
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            // iterate
            l++;
            r--;
        }
    }

    /**
     * Given an array with n objects colored red, white or blue, sort them so
     * that objects of the same color are adjacent, with the colors in the order
     * red, white and blue. <br>
     * <br>
     * Here, we will use the integers 0, 1, and 2 to represent the color red,
     * white, and blue respectively. <br>
     * <br>
     * Given [1, 0, 1, 2], sort it in-place to [0, 1, 1, 2].
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {
        int l = 0, r = nums.length - 1;
        for (int i = 0; i <= r; i++) {
            if (nums[i] == 0) {
                // get all the 0's to the front
                swap(nums, i, l++);
            } else if (nums[i] == 2)
                // push all the 2's to the back
                swap(nums, i--, r--);
        }
    }

    /**
     * Given two strings, find the longest common substring. Return the length
     * of it. <br>
     * <br>
     * The characters in substring should occur continuously in original string.
     * This is different with subsequence. <br>
     * <br>
     * Given A = "ABCD", B = "CBCE", return 2.
     *
     * @param A
     * @param B
     * @return
     */
    public static int longestCommonSubstring(String A, String B) {
        int dp[][] = new int[A.length() + 1][B.length() + 1];
        int maxLen = 0;

        for (int i = 0; i <= A.length(); i++) {
            for (int j = 0; j <= B.length(); j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    // found match increment pointer
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLen = Math.max(dp[i][j], maxLen);
                } else // if it's not a match then reinitialize the cell
                    dp[i][j] = 0;
                // dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        // return dp[A.length()][B.length()];
        return maxLen;
    }

    /**
     * Given a dictionary, find all of the longest words in the dictionary. <br>
     * <br>
     * <b>Input</b>: { "dog", "google", "facebook", "internationalization",
     * "blabla" } <br>
     * <b>Output</b>: Longest words are(is) ["internationalization"].
     *
     * <br>
     * <br>
     * <b>Input</b>: { "like", "love", "hate", "yes" } <br>
     * <b>Output</b>: Longest words are ["like", "love", "hate"].
     *
     * @param dictionary
     * @return
     */
    static ArrayList<String> longestWords(String[] dictionary) {
        ArrayList<String> result = new ArrayList<>();
        int maxLen = 0;

        for (int i = 0; i < dictionary.length; i++) {
            if (dictionary[i].length() >= maxLen) {
                maxLen = dictionary[i].length();
                if (!result.isEmpty() && (result.get(0).length() < maxLen)) {
                    result.clear();
                    result.add(dictionary[i]);
                } else
                    result.add(dictionary[i]);
            }
        }
        return result;
    }

    /**
     * Given two strings S and T, determine if they are both one edit distance
     * apart. <br>
     * <br>
     * <b>Input</b>: s = "aDb", t = "adb" <br>
     * <b>Output</b>: true <br>
     * <br>
     * <b>Input</b>: s = "a", t = "ab" <br>
     * <b>Output</b>: true <br>
     * <br>
     * <b>Input</b>: s = "bcde", t = "abcde" <br>
     * <b>Output</b>: true
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null)
            return false;

        if (Math.abs(s.length() - t.length()) > 1 || s.equals(t))
            return false;

        // make t always shorter than s
        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }

        // check if two chars are same
        // check if two strings are whether one modify apart or one delete apart
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                // delete char from String s
                // "ab" && "a"
                boolean isDel = s.substring(i + 1).equals(t.substring(i + 1));
                // consider "bcde" && "abcde"
                boolean isIns = s.substring(i).equals(t.substring(i + 1));
                return isDel || isIns;
            }
        }

        // if all the letters are same
        // then compare differences in lengths of the string
        // both strings must be one length apart
        return Math.abs(s.length() - t.length()) == 1;
    }

    /**
     * You are given an n x n 2D matrix representing an image.
     *
     * Rotate the image by 90 degrees (clockwise). <br>
     * <br>
     * <b>Input</b>:<br>
     * 1 2 3<br>
     * 4 5 6<br>
     * 7 8 9<br>
     * <b>Output</b>: <br>
     * 7 4 1<br>
     * 8 5 2<br>
     * 9 6 3<br>
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        // find the transpose of matrix
        transpose(matrix);
        // reverse the rows of the matrix
        reverseRows(matrix);
    }

    /**
     * Modifies the matrix to its transpose <br>
     * <br>
     * <br>
     * <b>Input Matrix</b>:<br>
     * 1 2 3<br>
     * 4 5 6<br>
     * 7 8 9<br>
     * <br>
     * <b>Modified Matrix</b>: <br>
     * 1 4 7<br>
     * 2 5 8<br>
     * 3 6 9<br>
     *
     * @param mat
     */
    private void transpose(int[][] mat) {
        for (int i = 0; i < mat.length; i++) { // rows
            for (int j = i; j < mat[0].length; j++) { // columns
                // swap(mat[i][j], mat[j][i]);
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
    }

    // reverse the rows of the matrix
    // 1 4 7 ==> 7 4 1
    private void reverseRows(int[][] mat) {
        // int rows = mat.length;
        // int cols = mat[0].length;

        // for(int i = 0; i < mat.length; i++){
        // ArrayUtils.reverse(mat[i]);
        // }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length / 2; j++) {
                // swap(mat[i][j], [i][j - n]);
                int temp = mat[i][j];
                mat[i][j] = mat[i][mat[i].length - j - 1];
                mat[i][mat[i].length - j - 1] = temp;
            }
        }
    }

    /**
     * Given an array of integers, the majority number is the number that occurs
     * more than 1/3 of the size of the array. <br>
     * <br>
     * <code> There is only one majority number in the array.
     * </code> <br>
     * <br>
     * <b>Input</b>: {@code [1, 2, 1, 2, 1, 3, 3]}, <b>Output</b>: 1. <br>
     * <br>
     * <b>Input</b>: {@code [99, 2, 99, 2, 99, 3, 3]}, <b>Output</b>: 99. <br>
     *
     * @param nums
     * @return
     */
    public static int majorityNumber(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0)
            return 0;
        // since we have to find majority occurring [1/3] of array
        // we need two contenders for comparison
        int candidate1 = 0, candidate2 = 0, count1 = 0, count2 = 0;

        for (int i = 0; i < nums.size(); i++) {
            if (candidate1 == nums.get(i)) {
                count1++;
            } else if (candidate2 == nums.get(i)) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = nums.get(i);
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = nums.get(i);
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        // consider test case
        // [1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4]
        // at the end ===> count1 == 2 with candidate1 == 1
        // count2 == 3 with candidate2 == 4
        // i.e. count2 > count1 which is wrong in our case
        // so, again tally the occurrences of both these candidates
        count1 = count2 = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == candidate1) {
                count1++;
            } else if (nums.get(i) == candidate2) {
                count2++;
            }
        }
        return (count1 > count2) ? candidate1 : candidate2;
    }

    /**
     * Given an encoded message containing digits, determine the total number of
     * ways to decode it. <br>
     * <br>
     * A message containing letters from A-Z is being encoded to numbers using
     * the following mapping: <br>
     * 'A' -> 1 <br>
     * 'B' -> 2 <br>
     * ...<br>
     * 'Z' -> 26 <br>
     * <b>Input</b>: Given encoded message <b>12</b>, <br>
     * <b>Output</b>: It could be decoded as <b>AB (1 2) </b>or <b>L
     * (12)</b>.<br>
     * <br>
     * {@code The number of ways for decoding 12 is 2.}
     *
     * @param s
     * @return
     */
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int dp[] = new int[s.length() + 1];
        dp[0] = 1;

        // check for the zero at the beginning of the string
        dp[1] = (s.charAt(0) != '0') ? 1 : 0;

        for (int i = 2; i <= s.length(); i++) {
            // check for the single digits
            // exclude the search for the zero
            int one = Integer.valueOf(s.substring(i - 1, i));
            if (one >= 1 && one <= 9) {
                dp[i] += dp[i - 1];
            }
            // check for only two letter words
            int two = Integer.valueOf(s.substring(i - 2, i));
            if (two >= 10 && two <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

    /**
     * Implement an algorithm to determine if a string has all unique
     * characters. (w/o the use of extra data structure)
     *
     * https://stackoverflow.com/questions/19484406/detecting-if-a-string-has-unique-characters-comparing-my-solution-to-cracking/19484495#19484495
     *
     * @param str
     * @return
     */
    public static boolean isUnique(String str) {
        // O(n^2) solution --> w/o using any additional data structure
        for (char c : str.toCharArray()) { // takes O(n) time
            if (str.indexOf(c) != str.lastIndexOf(c)) {
                // indexOf() ==> takes O(n) time
                return false;
            }
        }
        return true;
    }

    /**
     * Given a pattern and a string str, find if str follows the same pattern.
     * <br>
     * <br>
     * Here follow means a full match, such that there is a bijection between a
     * letter in pattern and a non-empty word in str. <br>
     * <br>
     * Examples:<br>
     * <b>pattern</b> = "{@code abba}", str = "{@code dog cat cat dog}" should
     * return <b>true</b>.<br>
     * <b>pattern</b> = "{@code abba}", str = "{@code dog cat cat fish}" should
     * return <b>false</b>. <br>
     * <b>pattern</b> = "{@code aaaa}", str = "{@code dog cat cat dog}" should
     * return <b>false</b>. <br>
     * <b>pattern</b> = "{@code abba}", str = "{@code dog dog dog dog}" should
     * return <b>false</b>. <br>
     * <br>
     * Notes: You may assume pattern contains only lowercase letters, and str
     * contains lowercase letters separated by a single space.
     *
     * @param pattern
     * @param str
     * @return
     */
    public static boolean wordPattern(String pattern, String str) {
        // Store a hashmap for mapping values of patterns to its counterparts in
        // its resp. string
        if (pattern == null || str == null)
            return false;

        Map<Character, String> map = new HashMap<Character, String>();
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;

        for (int i = 0; i < pattern.length(); i++) {
            if (!map.containsKey(pattern.charAt(i))) {
                // check if the value has already been assigned to another key
                if (map.containsValue(words[i]))
                    return false;
                map.put(pattern.charAt(i), words[i]);
            } else {
                if (!words[i].equalsIgnoreCase(map.get(pattern.charAt(i)))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Implement next permutation, which rearranges numbers into the
     * lexicographically next greater permutation of numbers. <br>
     * {@code If such arrangement is not possible, it must rearrange it as the lowest
     * possible order (ie, sorted in ascending order). }<br>
     * The replacement must be <b>in-place, do not allocate extra memory.
     * </b><br>
     * <br>
     * <b>Inputs</b> ---- <b>Outputs</b> <br>
     * {@code 1,2,3 --> 1,3,2} <br>
     * {@code 3,2,1 --> 1,2,3} <br>
     * {@code 1,1,5 --> 1,5,1}
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        if (nums.length < 2 || nums == null)
            return;

        // find the violating number which is not in order from the back of the
        // array
        int decreasingNumber = 0, i = 0;
        // consider sequence [1, 2, 3, 5, 4]
        // 4 < 5 && 3 < 2 < 1 but 5 !< 3
        // hence, 3 is the number breaking increasing order from the back
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                decreasingNumber = nums[i];
                break;
            }
        }
        // store the index where the order breaks i.e. 2
        int revIndex = i;
        if (revIndex >= 0) {
            // reiterate the array from back to find the first number
            // larger than the decreasingNumber
            i = nums.length - 1;
            while (i >= 0 && nums[i] <= decreasingNumber) {
                i--;
            }
            // [1, 2, 3, 5, 4]
            // swap the found number(4) at index i=4 with the decreasingNumber 3
            int swapIndex = i;
            // swap index 4(num = 4) and index 2(num = 3)
            swap(nums, revIndex, swapIndex);
            // [1, 2, 4, 5, 3]
        }
        // reverse the array after breaking index viz., 2
        reverseIntArray(nums, revIndex + 1);
        // next permutation --> [1, 2, 4, 3, 5]
    }

    /**
     * Reverses the integer array starting from the given index
     *
     * @param nums
     * @param index
     */
    private static void reverseIntArray(int[] nums, int index) {
        // array == [1, 2, 4, 5, 3]
        // index == 3
        // [1, 2, 4, 3, 5]
        int i = index, j = nums.length - 1;
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    /**
     * Given an array with n integers, your task is to check if it could become
     * non-decreasing by modifying at most 1 element. <br>
     * <br>
     * {@code We define an array is non-decreasing if array[i] <= array[i + 1] holds
     * for every i (1 <= i < n)}. <br>
     * <br>
     * <b>Input</b>: {@code [4, 2, 3]}<br>
     * <b>Output</b>: <b>True</b> <br>
     * Explanation: You could modify the first 4 to 1 to get a non-decreasing
     * array. <br>
     * <br>
     * <b>Input</b>: {@code [4, 2, 1]}<br>
     * <b>Output</b>: <b>False</b> <br>
     * Explanation: You can't get a non-decreasing array by modify at most one
     * element.<br>
     * <br>
     * Note: The n belongs to [1, 10,000].
     *
     * @param nums
     * @return
     */
    public static boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                // rule violated, increment count
                count++;
                // check both left and right neighbors of violating number/index
                // to see if they satisfies the ascending order criteria
                // i.e. a[i] < a[i+1] < a[i+2] < .... < a[n]
                if (count >= 2 || i > 0 && nums[i - 1] > nums[i + 1] && i + 2 < nums.length && nums[i + 2] < nums[i])
                    return false;
            }
        }
        return true;
    }

    /**
     * Define S = [s,n] as the string S which consists of n connected strings s.
     * For example, ["abc", 3] ="abcabcabc". <br>
     * <br>
     * On the other hand, we define that string s1 can be obtained from string
     * s2 if we can remove some characters from s2 such that it becomes s1. For
     * example, abc can be obtained from abdbec based on our definition, but
     * it can not be obtained from acbbe. <br>
     * <br>
     * You are given two non-empty strings s1 and s2 (each at most 100
     * characters long) and two integers 0 <= n1 <= 106 and 1 <= n2 <= 106. Now
     * consider the strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. Find the
     * maximum integer M such that [S2,M] can be obtained from S1. <br>
     * <br>
     *
     * <b>Input</b>: s1="acb", <br>
     * n1=4 <br>
     * s2="ab", <br>
     * n2=2 <br>
     * <b>Output</b>: 2
     *
     * @param s1
     * @param n1
     * @param s2
     * @param n2
     * @return
     */
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        // using brute-force approach for this problem
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int c1 = 0, c2 = 0, i = 0, j = 0;
        // we need to find a pattern of s2 that matches in s1
        while (c1 < n1) {
            // s2 character matches with s1
            if (chars1[i] == chars2[j]) {
                j++; // increment s2 pointer
                if (j == s2.length()) { // reached the end of s2
                    j = 0; // reinitialize s2 pointer
                    c2++; // found one match for string s2 in s1
                }
            }
            i++; // increment the s1 pointer for next comparison
            if (i == s1.length()) {
                // end of first repetition of string s1
                i = 0; // reset the pointer
                c1++; // increment the repetition counter
            }
        } // end of string s1 including its repetitions

        return c2 / n2;
    }

    /**
     * There is an integer array which has the following features: <br>
     * The numbers in adjacent positions are different. <br>
     * {@code A[0] < A[1] &&
     * A[A.length - 2] > A[A.length - 1]}. <br>
     * We define a position P is a peek if: <br>
     * {@code A[P] > A[P-1] && A[P] > A[P+1]} <br>
     * <br>
     * Find a peak element in this array. Return the index of the peak. <br>
     * <b> Note: The array may contains multiple peeks, find any of them. </b>
     * <br>
     * <br>
     * <b>Input</b>: [1, 2, 1, 3, 4, 5, 7, 6] <br>
     * Return index 1 (which is number 2) or 6 (which is number 7)
     *
     * @param A
     * @return
     */
    public int findPeak(int[] A) {
        // consider 3 test cases
        // 1. Array elements in asc order
        // 2. Array elements in desc order
        // 3. Neither of 1 or 2

        int l = 0, h = A.length - 1;

        while (l < h) {
            int mid = l + (h - l) / 2;
            // 1, 2, 3, 4, 5
            // 5, 4, 3, 2, 1
            // since its guaranteed to exist at least one peak element
            if (A[mid] > A[mid + 1]) {
                // then if a[mid] is > arr[mid+1],
                // other pair must be in the lower half
                h = mid;
            } else
                // search for the upper half of the array
                l = mid + 1;
        }
        return l;
    }

    /**
     * Returns a index to the first occurrence of target in source, or -1 if
     * target is not part of source. <br>
     * <br>
     * <b>Input</b>: source = "source" and target = "target", <br>
     * <b>Output</b>: return -1. <br>
     * <br>
     * <b>Input</b>: source = "abcdabcdefg" and target = "bcd", <br>
     * <b>Output</b>: return 1.
     *
     * @param source
     *            string to be scanned.
     * @param target
     *            string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        // using brute-force approach O(n^2)
        if (source == null || target == null)
            return -1;

        int l1 = source.length(), l2 = target.length();
        if (l1 < l2) {
            return -1;
        } else if (l2 == 0) {
            return 0;
        }
        // no need to scan through entire source string
        int threshold = l1 - l2;

        // check for the pattern in source string till threshold
        for (int i = 0; i <= threshold; i++) {
            // i+l2 will parse the last part of the source string after the
            // threshold
            if (source.substring(i, i + l2).equals(target)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Give you an integer array (index from 0 to n-1, where n is the size of
     * this array, data value from 0 to 10000) . For each element Ai in the
     * array, count the number of element before this element Ai is smaller than
     * it and return count number array. <br>
     * <br>
     * <b>Input</b>: {@code [1,2,7,8,5],} <br>
     * <b>Output</b>: {@code [0,1,2,3,2]}
     *
     * @param nums
     * @return
     */
    public static List<Integer> countOfSmallerNumberII(int[] nums) {
        // List<Integer> A =
        // IntStream.of(nums).boxed().collect(Collectors.toList());

        List<Integer> A = convertToList(nums);
        List<Integer> result = new ArrayList<Integer>();
        if (A.isEmpty() || A == null || A.size() < 2)
            return result;
        result.add(0);

        for (int ii = 1; ii < A.size(); ii++) {
            // in order to get the elements appearing before self
            // that are smaller than the current element
            List<Integer> tempList = A.subList(0, ii);
            // sort the elements before current element
            Collections.sort(tempList);
            // search for the index of the element viz.,
            // just smaller than the current element
            // in the previous subList
            // int num = searchKey(tempList.stream().mapToInt(i -> i).toArray(),
            // (int) (A.get(ii)));
            int num = findIndex(tempList, (int) (A.get(ii)));
            result.add((num == -1) ? tempList.size() : num);
        }
        return result;
    }

    /**
     * Converts the int[] to ArrayList<Integer>
     *
     * @param nums
     *            primitive int array
     * @return List of integers
     */
    private static List<Integer> convertToList(int[] ints) {
        List<Integer> intList = new ArrayList<Integer>();
        for (int index = 0; index < ints.length; index++) {
            intList.add(ints[index]);
        }
        return intList;
    }

    /**
     * Returns the index of element just smaller than the target key, if target
     * is not found. (Count the number of elements smaller than the target)
     *
     * @param sorted
     *            List of sorted integers
     * @param target
     *            Target key
     * @return index, target key index, if found. <br>
     *         else, index of element just smaller than the target key
     */
    private static int findIndex(List<Integer> sorted, int target) {
        if (sorted.size() == 0)
            return 0;
        int start = 0;
        int end = sorted.size() - 1;
        if (sorted.get(end) < target)
            return end + 1;
        if (sorted.get(start) >= target)
            return 0;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (sorted.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (sorted.get(start) >= target)
            return start;
        return end;
    }

    /**
     * Returns the index of the element just smaller than the target element, -1
     * if such element does not exists.
     *
     * @param arr
     *            Input array
     * @param key
     *            Target element
     * @return <b>-1</b>, if key doesn't exist && no element smaller than key
     *         found<br>
     *         <b>index</b>, if element just smaller than the key is found <br>
     *         <b>index - 1</b>, index of previous element, if target key is
     *         found
     */
    public static int searchKey(int arr[], int key) {
        int low = 0, high = arr.length, mid = -1;
        boolean flag = false;

        while (low < high) {
            mid = low + (high - low) / 2;
            if (arr[mid] == key) {
                flag = true;
                break;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        if (flag) {
            return mid - 1;
        } else {
            if (low >= arr.length)
                return -1;
            else
                return low;
            // high will give next smaller
        }
    }

    /**
     * Given n non-negative integers representing an elevation map where the
     * width of each bar is 1, compute how much water it is able to trap after
     * raining.
     *
     * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
     *
     * @param heights
     *            a list of integers
     * @return a integer
     */
    public int trap(int[] heights) {
        if (heights == null || heights.length < 2)
            return 0;
        int len = heights.length;
        int ans = 0;

        // using dynamic programming approach
        // time complexity = O(3n) ~ O(n)
        // space complexity = O(2n) ~ O(n)

        // water trapped can be measured by calculating the
        // left and right boundary walls between cells

        // store max left and max right walls sizes/heights for each i
        int[] max_left = new int[len + 1];
        int[] max_right = new int[len + 1];

        // iterate the given array from start to end
        // and update max_left wall size for each i

        max_left[0] = heights[0]; // initialize the first element
        for (int i = 1; i < len; i++) {
            max_left[i] = Math.max(max_left[i - 1], heights[i]);
        }

        // do the same and update right end wall sizes
        max_right[len - 1] = heights[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], heights[i]);
        }

        // total water trapped at i will be the difference between
        // it's min(left, right) boundary walls sizes
        for (int i = 0; i < len; i++) {
            ans += Math.min(max_left[i], max_right[i]) - heights[i];
        }
        return ans;
    }

    /**
     * Evaluate the value of an arithmetic expression in Reverse Polish
     * Notation. Valid operators are {@code +, -, *, /}. Each operand may be an
     * integer or another expression. <br>
     * <br>
     * <b>Example</b>: {@code ["2", "1", "+", "3", "*"] }-> ((2 + 1) * 3) -> 9
     * <br>
     * <b>Example</b>: {@code ["4", "13", "5", "/", "+"]} -> (4 + (13 / 5)) -> 6
     *
     * @param tokens
     *            The Reverse Polish Notation
     *
     * @return the value
     */
    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<String>();
        int ans = 0;

        for (int i = 0; i < tokens.length; i++) {
            if (Character.isDigit(tokens[i].charAt(0)) || tokens[i].matches("-?[1-9]\\d*|0")) {
                stack.push(tokens[i]);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());

                String op = tokens[i];
                Integer res = 0;
                switch (op.charAt(0)) {
                    case '+':
                        res = num2 + num1;
                        break;
                    case '-':
                        res = num2 - num1;
                        break;
                    case '*':
                        res = num2 * num1;
                        break;
                    case '/':
                        res = num2 / num1;
                        break;
                }
                // push the result back onto the stack
                stack.push(res.toString());
            }
        }
        return (stack.isEmpty()) ? ans : Integer.parseInt(stack.peek());
    }

    /**
     * Given an expression s includes numbers, letters and brackets. Number
     * represents the number of repetitions inside the brackets(can be a string
     * or another expression). Please expand expression to be a string. <br>
     * <br>
     * <b>Input</b>: {@code s = abc3[a]},<br>
     * <b>Output</b>: {@code abcaaa}<br>
     * <br>
     * <b>Input</b>: {@code s = 3[abc]},<br>
     * <b>Output</b>: {@code abcabcabc}<br>
     * <br>
     * <b>Input</b>: {@code s = 4[ac]dy},<br>
     * <b>Output</b>: {@code acacacacdy}<br>
     * <br>
     * <b>Input</b>: {@code s = 3[2[ad]3[pf]]xyz}, <br>
     * <b>Output</b>: {@code adadpfpfpfadadpfpfpfadadpfpfpfxyz}<br>
     *
     * @param s
     *            an expression includes numbers, letters and brackets
     * @return a string
     */
    public static String expressionExpand(String s) {
        Stack<Object> stack = new Stack<Object>();
        int number = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // number = Character.getNumericValue(ch);
                number *= 10; // multiply by 10 to form multiple digit number
                number += (ch - '0'); // like 12, 21, 499, 30, etc.
            } else if (ch == '[') {
                // store the count of repetitions
                stack.push(Integer.valueOf(number));
                number = 0; // reset the count number
            } else if (ch == ']') {
                String temp = getStackContents(stack);
                Integer count = (Integer) stack.pop();
                while (count > 0) {
                    stack.push(temp);
                    count--;
                }
            } else {
                stack.push(String.valueOf(ch));
            }
        }
        // retrieve the string in a reverse format from original stack
        return getStackContents(stack);
    }

    /**
     * Returns the strings present in a stack in order of their insertion. <br>
     * <br>
     * Example: Original Stack == (top/peek) c > b > a (bottom) <br>
     * returns "abc"
     *
     * @param stack
     *            Original stack
     * @return Strings in order of insertion
     */
    private static String getStackContents(Stack<Object> stack) {
        Stack<String> s = new Stack<String>();
        // check if the top of the stack is indeed a String
        while (!stack.isEmpty() && (stack.peek() instanceof String)) {
            s.push((String) stack.pop());
        }
        // no need to reverse the contents of original stack
        // since we are unloading elements from one stack to another
        StringBuffer sb = new StringBuffer();
        while (!s.isEmpty()) {
            sb.append(s.pop());
        }
        return sb.toString();
    }

    /**
     * Write an algorithm to determine if a number is happy. <br>
     * <br>
     * A happy number is a number defined by the following process:<br>
     * Starting with any positive integer, replace the number by the sum of the
     * squares of its digits, and repeat the process until the number equals 1
     * (where it will stay), or it loops endlessly in a cycle which does not
     * include 1. Those numbers for which this process ends in 1 are happy
     * numbers.
     *
     * <br>
     * <br>
     * <b>Input</b>: 19 <br>
     * <b>Output</b>: true <br>
     * <b>Explanation</b>:<br>
     * {@code 1^2 + 9^2 = 82}<br>
     * {@code 8^2 + 2^2 = 68}<br>
     * {@code 6^2 + 8^2 = 100}<br>
     * {@code 1^2 + 0^2 + 0^2 = 1}<br>
     *
     * @param n
     *            An integer
     * @return true, if this is a happy number or false
     */
    public static boolean isHappy(int n) {
        // take double pointers
        int slow, fast;
        slow = fast = n;
        // detect the cycle
        do {
            // reiterate the values of squares sum
            slow = getSquaresSum(slow);
            fast = getSquaresSum(fast);
            // if there's a loop then both values will be the same
            fast = getSquaresSum(fast);
        } while (slow != fast);
        // check if either of the pointers point to 1
        return (fast == 1) ? true : false;
    }

    /**
     * Returns the sum of squares of all the digits in the given number.
     *
     * @param n
     *            Input number
     * @return
     */
    private static int getSquaresSum(int n) {
        int sum = 0, tmp;
        while (n > 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }

    /**
     *
     * Given two words (start and end), and a dictionary, find the length of
     * shortest transformation sequence from start to end, such that: <br>
     * Only one letter can be changed at a time. Each intermediate word must
     * exist in the dictionary. <br>
     * <br>
     * Return 0 if there is no such transformation sequence. <br>
     * <br>
     * Given: <br>
     * start = "hit" <br>
     * end = "cog" <br>
     * dict = ["hot","dot","dog","lot","log"] <br>
     * As one shortest transformation is <br>
     * "hit" -> "hot" -> "dot" -> "dog" -> "cog", <br>
     * return its length 5.
     *
     * @param start,
     *            a string
     * @param end,
     *            a string
     * @param dict,
     *            a set of string
     * @return an integer
     */
    public static int ladderLength(String start, String end, Set<String> dict) {
        Queue<String> queue = new LinkedList<String>();
        if (start.equals(end))
            return 1;

        Set<String> visited = new HashSet<String>();
        visited.add(start);
        queue.add(start);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                for (int j = 0; j < str.length(); j++) {
                    // change each letter of given string and
                    // match with the words in the dict
                    char[] chars = str.toCharArray();

                    // alter each letter of the word one at a time
                    // this way the edit distance will be 1
                    for (char k = 'a'; k <= 'z'; k++) {
                        // str.charAt(j) = k;
                        chars[j] = k;
                        String word = new String(chars);
                        // check if its the end word even when it's not present
                        // in the dict
                        if (word.equals(end))
                            return level + 1;
                        // add the next word to the queue and visited list
                        if (dict.contains(word) && !visited.contains(word)) {
                            queue.add(word);
                            visited.add(word);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }

    /**
     * Given a string s and a string t, check if s is a subsequence of t. <br>
     * <br>
     * You may assume that there is only lower case English letters in both s
     * and t. t is potentially a very long (length ~= 500,000) string, and s is
     * a short string (<=100). <br>
     * <br>
     * A subsequence of a string is a new string which is formed from the
     * original string by deleting some (can be none) of the characters without
     * disturbing the relative positions of the remaining characters. (ie, "ace"
     * is a subsequence of "abcde" while "aec" is not). <br>
     * <br>
     * <b>Input</b>: {@code s = "abc", t = "ahbgdc" }<br>
     * <b>Output</b>: <b>true</b>. <br>
     * <br>
     * <b>Input</b>: {@code s = "axc", t = "ahbgdc" }<br>
     * <b>Output</b>: <b>false</b>.
     *
     * @param s
     *            String pattern (matcher)
     * @param t
     *            Text string (text)
     * @return
     */
    public static boolean isSubsequence(String s, String t) {
        int i = 0, j = 0, m = s.length(), n = t.length();
        // iterate through the whole text string to find a match
        while (i < n && j < m) {
            // enter the loop if first char of pattern(S) matches with the
            // current text character
            if (t.charAt(i) == s.charAt(j)) {
                j++;
            }
            i++; // increment the text string pointer/iterator
        }
        // // check if whole pattern(S) string has been matched
        // if (j == m) return true;
        return (j == m); // if the complete pattern(String s) is matched then
        // return true
    }

    /**
     * Given n non-negative integers a1, a2, ..., an, where each represents a
     * point at coordinate (i, ai). n vertical lines are drawn such that the two
     * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which
     * together with x-axis forms a container, such that the container contains
     * the most water. <br>
     * <br>
     * <b>Input</b>: {@code [1,3,2]} <br>
     * <b>Output</b>: 2<br>
     * <br>
     * <b>Input</b>:{@code [1, 8, 6, 2, 5, 4, 8, 3, 7]}<br>
     * <b>Output</b>:{@code 49}
     *
     * @param heights
     *            a vector of integers
     * @return an integer
     */
    public int maxArea(int[] heights) {
        if (heights == null || heights.length < 2)
            return 0;

        int maxArea = 0, l = 0, r = heights.length - 1;
        while (l < r) {
            // area formed by rectangle will be
            // area = base * min(height[i], heights[j])
            maxArea = Math.max(Math.min(heights[l], heights[r]) * (r - l), maxArea);
            // forward the left pointer if left wall is < right wall
            if (heights[l] < heights[r])
                l++;
            else
                r--;
        }
        return maxArea;
    }

    /**
     * Given numRows, generate the first numRows of Pascal's triangle. <br>
     * <br>
     * <b>Input</b>: numRows = 5,<br>
     * <b>Output</b>:<br>
     * {@code [}<br>
     * {@code [1],} <br>
     * {@code [1,1],} <br>
     * {@code [1,2,1],} <br>
     * {@code [1,3,3,1],} <br>
     * {@code [1,4,6,4,1]} <br>
     * {@code ]}
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows <= 0)
            return result;
        for (int i = 0; i < numRows; i++) {
            List<Integer> temp = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    temp.add(1);
                else
                    temp.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
            }
            result.add(temp);
        }
        return result;
    }

    /**
     * Given an array of integers and an integer k, you need to find the number
     * of unique k-diff pairs in the array. Here a k-diff pair is defined as an
     * integer pair (i, j), where i and j are both numbers in the array and
     * their absolute difference is k. <br>
     * <br>
     * <b>Input</b>: {@code [3, 1, 4, 1, 5], k = 2 }<br>
     * <b>Output</b>: 2 <br>
     * <b>Explanation</b>: There are two 2-diff pairs in the array,
     * {@code (1, 3) and (3, 5)}. Although we have two 1s ((3, 1)) in the input,
     * we should only return the number of unique pairs.<br>
     * <br>
     * <b>Input</b>:{@code [1, 2, 3, 4, 5], k = 1 }<br>
     * <b>Output</b>: 4 <br>
     * <b>Explanation</b>: There are four 1-diff pairs in the array,
     * {@code (1, 2), (2, 3), (3, 4) and (4, 5)}. <br>
     * <br>
     * <b>Input</b>: {@code [1, 3, 1, 5, 4], k = 0 }<br>
     * <b>Output</b>: 1 <br>
     * <b>Explanation</b>: There is one 0-diff pair in the array,
     * {@code (1, 1)}.
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int count = 0;
        if (k < 0)
            return count;

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            // special case
            // find pairs of same integer keys
            if (k == 0) {
                // check if this entry key occurred multiple times in an
                // array
                if (entry.getValue() >= 2)
                    count++;
            } else if (map.containsKey(entry.getKey() + k)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Given a non-negative integer, you could swap two digits at most once to
     * get the maximum valued number. Return the maximum valued number you could
     * get. <br>
     * <br>
     * <b>Input</b>: 2736 <br>
     * <b>Output</b>: 7236 <br>
     * <b>Explanation</b>: Swap the number 2 and the number 7. <br>
     * <br>
     * <b>Input</b>: 9973 <br>
     * <b>Output</b>: 9973 <br>
     * <b>Explanation</b>: No swap.
     *
     * @param num
     * @return
     */
    public static int maximumSwap(int num) {
        String str = new String(Integer.toString(num));
        // Consider input == 9937
        char[] originalDigits = str.toCharArray();
        char[] sortedDigits = str.toCharArray();

        Arrays.sort(sortedDigits);
        // sort the digits in descending order
        sortedDigits = new StringBuilder(new String(sortedDigits)).reverse().toString().toCharArray();
        // digits = 9973
        int ind = 0;
        // compare the digits w/ the sorted digits
        for (ind = 0; ind < originalDigits.length; ind++) {
            // if not same --> swap
            // 9 9 3 7
            // 9 9 7 3
            if (originalDigits[ind] != sortedDigits[ind])
                break; // 3 != 7
        }

        // if reached end of the string, return
        if (ind == str.length()) // index == 2 --> (not the end)
            return num;

        // find the last position of the mismatching digit
        char replace = sortedDigits[ind]; // replace = 7
        // in the original string viz., str
        int swpInd = str.lastIndexOf(replace); // last index = 3

        // swap the digits
        // 9937, 2, 3
        swapDigits(originalDigits, ind, swpInd);
        // 9973
        return Integer.parseInt(new String(originalDigits));
    }

    /**
     * Swaps the digits/chars present at index i && j.
     *
     * @param arr
     * @param i
     * @param j
     */
    static void swapDigits(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Returns the second smallest element in an array in O(n) time.
     *
     * @param arr
     * @return
     */
    private int getSecondSmallestInArray(int[] arr) {
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < smallest) {
                secondSmallest = smallest;
                smallest = arr[i];
            } else if (arr[i] < secondSmallest) {
                secondSmallest = arr[i];
            }
        }
        return secondSmallest;
    }

    /**
     * Given a collection of distinct numbers, return all possible permutations.
     * <br>
     * <br>
     * <b>Input</b>:<br>
     * [1,2,3] would have the following <b>six</b> permutations:<br>
     * {@code [}<br>
     * {@code [1,2,3],}<br>
     * {@code [1,3,2],}<br>
     * {@code [2,1,3],}<br>
     * {@code [2,3,1],}<br>
     * {@code [3,1,2],}<br>
     * {@code [3,2,1]}<br>
     * ]
     *
     * @param nums
     *            A list of integers.
     * @return A list of permutations.
     */
    public static List<List<Integer>> permute(int[] nums) {
        return permuteIterative(nums);
        // ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        // permuteDFS(nums, result, new ArrayList<Integer>());
        // return result;
    }

    /**
     * Iterative solution for getting all the possible permutations of the given
     * array elements.
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // start from an empty list
        result.add(new ArrayList<Integer>());
        if (nums.length == 0)
            return result;

        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> tempRes = new ArrayList<List<Integer>>();
            // iterate current result and alter lists
            for (List<Integer> list : result) {
                // fix the index j to add the new elements
                for (int j = 0; j <= i; j++) {
                    // copy the list from result to new list
                    List<Integer> tempList = new ArrayList<Integer>(list);
                    // add the element to the list at index j
                    tempList.add(j, nums[i]);
                    // move the tempList to result
                    tempRes.add(tempList);
                }
            }
            // transfer the temp results to main results list
            result = tempRes;
        }

        return result;
    }

    /**
     * Recursive method to get all the possible permutations of the given
     * numbers present in an array.
     *
     * @param nums
     *            Input array
     * @param result
     *            Output list
     * @param tempList
     *            Intermediate results
     */
    public static void permuteDFS(int[] nums, List<List<Integer>> result, List<Integer> tempList) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tempList.contains(nums[i]))
                continue; // element already exists, skip
            tempList.add(nums[i]);
            permuteDFS(nums, result, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * Given a set of distinct integers, return all possible subsets. <br>
     * <br>
     * Elements in a subset <b>must be in non-descending order</b>. <br>
     * The solution set must not contain duplicate subsets.<br>
     * <br>
     * <b>Input</b>: If S = [1,2,3], <br>
     * <b>Output</b>: <br>
     * [<br>
     * [3], <br>
     * [1], <br>
     * [2], <br>
     * [1,2,3], <br>
     * [1,3], <br>
     * [2,3], <br>
     * [1,2], <br>
     * [] <br>
     * ]
     *
     * @param nums
     *            A set of numbers
     * @return A list of lists
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // Elements should be in NON-desc i.e. in ascending order
        Arrays.sort(nums);
        backtrack(result, nums, 0, new ArrayList<Integer>());
        return result;
    }

    private static void backtrack(List<List<Integer>> result, int[] nums, int start, ArrayList<Integer> tempList) {
        result.add(new ArrayList<Integer>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(result, nums, i + 1, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * Given a list of numbers that may has duplicate numbers, return all
     * possible subsets. <br>
     * <br>
     * Elements in a subset <b>must be in non-descending order</b>. <br>
     * The solution set must not contain duplicate subsets.<br>
     * <br>
     * <b>Input</b>: If S = [1,2,2], <br>
     * <b>Output</b>: <br>
     * [<br>
     * [2], <br>
     * [1], <br>
     * [1,2,2], <br>
     * [2,2], <br>
     * [1,2], <br>
     * [] <br>
     * ]
     *
     * @param nums
     *            A set of numbers
     * @return A list of lists. All valid subsets.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // Elements should be in NON-desc i.e. in ascending order
        Arrays.sort(nums);
        backtrackWithDup(result, nums, 0, new ArrayList<Integer>());
        return result;
    }

    private static void backtrackWithDup(List<List<Integer>> result, int[] nums, int start,
                                         ArrayList<Integer> tempList) {
        result.add(new ArrayList<Integer>(tempList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1])
                continue; // skip duplicates
            tempList.add(nums[i]);
            backtrackWithDup(result, nums, i + 1, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * Given a sequence of integers, find the longest increasing subsequence
     * (LIS). Return the length of the LIS. <br>
     * <br>
     * <b>Input</b>: [5, 4, 1, 2, 3], <br>
     * <b>Output</b>: Return 3, because the LIS is [1, 2, 3]<br>
     * <br>
     * <b>Input</b>: [4, 2, 4, 5, 3, 7], <br>
     * <b>Output</b>: Return 4, because the LIS is [2, 4, 5, 7]<br>
     *
     * @param nums
     *            The integer array
     * @return The length of LIS (longest increasing subsequence)
     */
    public static int longestIncreasingSubsequence(int[] nums) {
        // base case
        if (nums.length < 2)
            return nums.length;

        // using dynamic programming approach
        // time complexity = O(n^2)
        int dp[] = new int[nums.length];
        int maxLen = 0;
        // if not filled manually, then by default 0's will be added,
        // instead of 1's
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            // recalculate maxLen at the end of each iteration
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    /**
     * Given an unsorted array of integers, find the length of the longest
     * consecutive elements sequence. <br>
     * <b>Your algorithm should run in O(n) complexity.</b><br>
     * <br>
     * <b>Input</b>: {@code [100, 4, 200, 1, 3, 2]},<br>
     * <b>Output</b>: {@code Return length 4,} because the longest consecutive
     * elements sequence is {@code [1, 2, 3, 4]}.<br>
     *
     * @param nums
     *            A list of integers
     * @return An integer
     */
    public static int longestConsecutive(int[] nums) {
        int maxLen = 0;
        if (nums.length <= 1)
            return nums.length;
        Set<Integer> set = new HashSet<Integer>();

        // add all the elements in the hashset
        for (int el : nums)
            set.add(el);

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            // compare only the beginnings of the trails
            if (!set.contains(nums[i] - 1)) {
                // check if this element has any right neighbours
                while (set.contains(nums[i]++))
                    count++;

                maxLen = Math.max(count, maxLen);
            }
        }

        return maxLen;
    }

    /**
     * Give an integer array, find the longest increasing continuous subsequence
     * in this array. <br>
     * <br>
     * An increasing continuous subsequence: <br>
     * Can be from right to left or from left to right. <br>
     * Indices of the integers in the subsequence <b>should be
     * continuous.</b><br>
     * <br>
     * <b>Input</b>: {@code [5, 4, 2, 1, 3]}, <br>
     * <b>Output</b>: Return 4, because the LICS is {@code [5, 4, 2, 1]}.<br>
     * <br>
     * <b>Input</b> {@code [5, 1, 2, 3, 4]}, <br>
     * <b>Output</b>: Return 4, because the LICS is {@code [1, 2, 3, 4]}.
     *
     * @param nums
     *            An array of Integer
     * @return an integer
     */
    public static int longestIncreasingContinuousSubsequence(int[] nums) {
        int maxLen = 0, len = 0;
        // base case
        if (nums == null || nums.length < 1)
            return 0;

        // get the LICS from left to right
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                len++; // increasing element found
                maxLen = Math.max(len, maxLen);
            } else
                // Indices of the integers in the subsequence should be
                // continuous.
                len = 0; // reset
        }

        len = 0; // reset the length

        // get the LICS from right to left
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] < nums[i - 1]) {
                len++; // increasing element found
                maxLen = Math.max(len, maxLen);
            } else
                len = 0; // reset
        }
        return maxLen + 1;
    }

    /**
     * Implement a basic calculator to evaluate a simple expression string. The
     * expression string may contain open ( and closing parentheses ), the plus
     * + or minus sign -, non-negative integers and empty spaces . <br>
     * <b>You may assume that the given expression is always valid. </b><br>
     * <br>
     * <b>Input</b>: "1 + 1"<br>
     * <b>Output</b> = 2<br>
     * <b>Input</b>" 2-1 + 2 "<br>
     * <b>Output</b> = 3 <br>
     * <b>Input</b>: "(1+(4+5+2)-3)+(6+8)" <br>
     * <b>Output</b> = 23
     *
     * @param s
     * @return
     */
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int sign = 1; // sign flag
        int res = 0; // prev carry result
        int digit = 0; // digit

        // we only have to consider addition/subtraction/brackets
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                // add the current digit to previous digit
                digit = digit * 10 + Character.getNumericValue(ch);
                // for number 12 ==>
                // 1.) 0 * 10 + 1 = 1
                // 2.) 1 * 10 + 2 = 12
            } else if (ch == '+') {
                // pop the previous result
                res += sign * digit;
                digit = 0; // reset the digit
                sign = 1;
            } else if (ch == '-') {
                res += sign * digit;
                digit = 0; // reset the digit
                sign = -1; // set the sign to negative
            } else if (ch == '(') {
                // push the previous result with its sign
                stack.push(res);
                stack.push(sign);
                // reset the result and sign
                res = 0;
                sign = 1;
            } else if (ch == ')') {
                res += sign * digit; // calculate the result till now
                // reset the digit
                digit = 0;
                // pop the previous results sign
                res *= stack.pop();
                // pop the previous stored stack results
                // and add it to the result
                res += stack.pop();
            }
        }
        // add any remaining stored sum
        if (digit != 0)
            res += digit * sign;
        return res;
    }

    /**
     * Given an unsorted array of integers, find the length of longest
     * <b>continuous</b> increasing subsequence. <br>
     * <br>
     * <b>Input</b>: {@code [1, 3, 5, 4, 7]}, <br>
     * <b>Output</b>: <b>3</b>, because the LICS is {@code [1, 3, 5]}. <br>
     * Even though {@code [1, 3, 5, 7]} is also an increasing subsequence, it's
     * not a continuous one where 5 and 7 are separated by 4.<br>
     * <br>
     * <b>Input</b> {@code [2, 2, 2, 2, 2]}, <br>
     * <b>Output</b>: <b>1</b>, because the LICS is {@code [2]}.
     *
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {
        int dp[] = new int[nums.length + 1];
        int maxLen = 1;
        if (nums == null || nums.length < 1)
            return 0;
        // if not filled manually, then by default 0's will be added,
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                dp[i] = dp[i - 1] + 1;
            }
            // recalculate maxLen at the end of each iteration
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    /**
     * Given an unsorted array of integers, find the number of longest
     * increasing subsequence. <br>
     * <br>
     * <b>Input</b>: {@code [1, 3, 5, 4, 7]}, <br>
     * <b>Output</b>: <b>2</b> <br>
     * <b>Explanation</b>: The two longest increasing subsequence are
     * {@code [1, 3, 4, 7]} and {@code [1, 3, 5, 7]}. <br>
     * <br>
     * <b>Input</b> {@code [2, 2, 2, 2, 2]}, <br>
     * <b>Output</b>: <b>5</b> <br>
     * <b>Explanation</b>: The length of longest continuous increasing
     * subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
     *
     * @param nums
     * @return
     */
    public static int findNumberOfLIS(int[] nums) {
        int count = 0, maxLen = 0;
        if (nums == null || nums.length < 1)
            return count;

        // using the DP approach O(n^2)
        // take two memo arrays each for length and count till nums[i]
        int len[] = new int[nums.length];
        int cnt[] = new int[nums.length];
        Arrays.fill(len, 1);
        Arrays.fill(cnt, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // fill the len array keeping track of no of elements in the
                // current sequence
                if (nums[j] < nums[i]) {
                    // check if there already exists increasing sequence
                    if (len[i] == len[j] + 1)
                        cnt[i] += cnt[j]; // increment the count of sequences
                    if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j]; // keep track of count
                    }
                }
            }
            // if reached max length then increment count
            if (maxLen == len[i])
                count += cnt[i]; // increment from cnt array

                // recalculate maxLen at the end of each iteration
            else if (maxLen < len[i]) {
                // recompute the max length of sequence
                maxLen = len[i];
                // reset the count
                count = cnt[i];
            }
        }
        // num = [1, 3, 5, 4, 7]
        // final memo arrays
        // len = [1, 2, 3, 3, 4]
        // cnt = [1, 1, 1, 1, 2]
        return count;
    }

    /**
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square
     * containing only 1's and return its area. <br>
     * <br>
     * For example, given the following matrix: <br>
     * 1 0 1 0 0<br>
     * 1 0 <b>1 1</b> 1<br>
     * 1 1 <b>1 1</b> 1<br>
     * 1 0 0 1 0<br>
     * <br>
     * Return 4.<br>
     *
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        // using the DP approach
        // Time complexity = O(m*n)
        // Space complexity = O(m*n)
        if (matrix == null || matrix.length < 1)
            return 0;
        int maxSide = 0, rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    // Get the min(dp(i-1, j), dp(i-1, j-1), dp(i, j-1))
                    // dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]),
                    // dp[i - 1][j - 1]) + 1;
                    dp[i][j] = getMinOfThree((dp[i - 1][j]), (dp[i][j - 1]), (dp[i - 1][j - 1])) + 1;
                    // keep track of max square side length
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }

    /**
     * Returns the minimum among three integers
     */
    private static int getMinOfThree(int i, int j, int k) {
        return (i < j) ? Math.min(i, k) : Math.min(j, k);
    }

    /**
     * You are a professional robber planning to rob houses along a street. Each
     * house has a certain amount of money stashed, the only constraint stopping
     * you from robbing each of them is that adjacent houses have security
     * system connected and it will automatically contact the police if two
     * adjacent houses were broken into on the same night. <br>
     * <br>
     * Given a list of non-negative integers representing the amount of money of
     * each house, determine the maximum amount of money you can rob tonight
     * without alerting the police.
     *
     * @param nums
     *            An array of non-negative integers
     *
     * @return The maximum amount of money you can rob tonight
     */
    public int rob(int[] nums) {
        int rob = 0, notRob = 0, currRob = 0;
        for (int i = 0; i < nums.length; i++) {
            // rob the current house viz., nums[i]
            // rob this house + money robbed excluding the previous house
            currRob = notRob + nums[i];
            // find max robbery for not robbing this house
            // max of previous robbery and current robbery so far
            notRob = Math.max(notRob, rob);
            // carry forward the current robbery
            rob = currRob;
        }
        return Math.max(rob, notRob);
    }

    /**
     * Given a non-empty array containing only positive integers, find if the
     * array can be partitioned into two subsets such that the sum of elements
     * in both subsets is equal. <br>
     * <br>
     * Each of the array element will not exceed 100. The array size will not
     * exceed 200. <br>
     * <b>Input</b>: Given nums = {@code [1, 5, 11, 5], }<br>
     * <b>Output</b>: <b>true</b><br>
     * <b>Explanation</b>: The array can be partitioned as {@code [1, 5, 5]} and
     * {@code [11]}.<br>
     * <br>
     * <b>Input</b>: Given nums = {@code [1, 2, 3, 9], }<br>
     * <b>Output</b>: <b>false</b> <br>
     * <b>Explanation</b>: The array cannot be partitioned into equal sum
     * subsets.
     *
     * @param nums
     *            a non-empty array only positive integers
     * @return true if can partition or false
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        // base case
        if (nums == null || nums.length < 2) {
            return false;
        }

        // compute the sum of all the elements
        for (int num : nums) {
            sum += num;
        }

        // check if the sum of all elements is even
        if (sum % 2 != 0) {
            return false; // sum cannot be partitioned into two equal halves
        }

        // only take the half of the sum for comparison
        sum /= 2;
        boolean dp[][] = new boolean[nums.length][sum + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true; // set first column to true
        }

        // take care of the first row
        // set true only if the element <= sum
        // if(nums[0] <= sum) dp[0][nums[0]] = true;

        dp[0][0] = true; // 0 numbers make 0 sum

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < sum + 1; j++) {
                // if current sum is less than the current number
                if (j < nums[i]) {
                    // if the number before has made it to sum == j
                    // then the current number can also make up the sum j
                    dp[i][j] = dp[i - 1][j];
                } else // either take value from above or
                    // travel back to the sum - current no (nums[i])
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
            }
        }
        return dp[nums.length - 1][sum];
    }

    /**
     * Given an integer array, your task is to find all the different possible
     * increasing subsequences of the given array, and the length of an
     * increasing subsequence should be at least 2. <br>
     * <br>
     *
     * <b>Input</b>: {@code [4, 6, 7, 7]} <br>
     * <b>Output</b>:
     * {@code [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7,
     * 7], [7,7], [4,7,7]] }<br>
     * <br>
     * <b>Note</b>: <br>
     * The length of the given array will not exceed 15. <br>
     * The range of integer in the given array is {@code [-100,100]}. <br>
     * The given array may contain duplicates, and two equal integers should
     * also be considered as a special case of increasing sequence.<br>
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<List<Integer>>();
        backtrackSeq(res, new ArrayList<Integer>(), nums, 0);
        return new ArrayList<>(res);
    }

    public static void backtrackSeq(Set<List<Integer>> res, List<Integer> temp, int[] arr, int start) {
        if (temp.size() >= 2) {
            res.add(new ArrayList<>(temp));
            // return; // DO NOT RETURN
        }

        for (int i = start; i < arr.length; i++) {
            // check if the current element is greater than the last
            if (temp.size() == 0 || arr[i] >= temp.get(temp.size() - 1)) {
                temp.add(arr[i]);
                backtrackSeq(res, temp, arr, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /**
     * Count the number of prime numbers less than a non-negative number, n.
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        boolean isPrime[] = new boolean[n];
        int count = 0;

        // boolean[] is false by default
        Arrays.fill(isPrime, true);

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    // mark all the multiples of current number as not prime
                    isPrime[i * j] = false;
                }
            }
        }
        return count;
    }

    /**
     * Given a non-empty string s, you may delete <b>at most</b> {@code one}
     * character. Judge whether you can make it a palindrome. <br>
     * <br>
     * <b>Input</b>: "aba" <br>
     * <b>Output</b>: True <br>
     * <br>
     * <b>Input</b>: "abca" <br>
     * <b>Output</b>: True <br>
     * <b>Explanation</b>: You could delete the character 'c'.<br>
     * <br>
     * <b>Input</b>: "tebbem" <br>
     * <b>Output</b>: False
     *
     * @param s
     * @return
     */
    public static boolean validPalindrome(String s) {
        if (s == null || s.length() == 0)
            return false;

        // abcbe
        for (int i = 0; i < s.length() / 2; i++) {
            // if a mismatch occurred
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                // check whether the rest of the substring is palindrome or not
                return isPalindrome(s, i + 1, s.length() - i - 1) || isPalindrome(s, i, s.length() - i - 2);
                // either delete the left end char || delete the right end char
            }
        }
        return true;
    }

    /**
     * You're now a baseball game point recorder. <br>
     * Given a list of strings, each string can be one of the 4 following types:
     * <br>
     * <br>
     * <b>Integer</b> (one round's score): Directly represents the number of
     * points you get in this round. <br>
     * <b>"+"</b> (one round's score): Represents that the points you get in
     * this round are the sum of the last two valid round's points.<br>
     * <b>"D"</b> (one round's score): Represents that the points you get in
     * this round are the doubled data of the last valid round's points. <br>
     * <b>"C"</b> (an operation, which isn't a round's score): Represents the
     * last valid round's points you get were invalid and should be removed.
     * Each round's operation is permanent and could have an impact on the round
     * before and the round after.<br>
     * You need to return the sum of the points you could get in all the rounds.
     * <br>
     * <br>
     *
     * <b>Input</b>:{@code ["5","2","C","D","+"]} <br>
     * <b>Output</b>: 15 <br>
     * <b>Explanation</b>: <br>
     * Round 1: You could get 5 points. The sum is: 5. <br>
     * Round 2: You could get 2 points. The sum is: 7. <br>
     * Operation 1: The round 2's data was invalid. The sum is: 5. <br>
     * Round 3: You could get 10 points (the round 2's data has been removed).
     * The sum is: 15. <br>
     * Round 4: You could get 5 + 10 = 15 points. The sum is: 30. <br>
     * <br>
     * <b>Input</b>: ["5","-2","4","C","D","9","+","+"] <br>
     * <b>Output</b>: 27 <br>
     * <b>Explanation</b>: <br>
     * Round 1: You could get 5 points. The sum is: 5. <br>
     * Round 2: You could get -2 points. The sum is: 3. <br>
     * Round 3: You could get 4 points. The sum is: 7. <br>
     * Operation 1: The round 3's data is invalid. The sum is: 3. <br>
     * Round 4: You could get -4 points (the round 3's data has been removed).
     * The sum is: -1. <br>
     * Round 5: You could get 9 points. The sum is: 8. <br>
     * Round 6: You could get -4 + 9 = 5 points. The sum is 13. <br>
     * Round 7: You could get 9 + 5 = 14 points. The sum is 27.
     *
     * @param ops
     * @return
     */
    public static int calPoints(String[] ops) {
        // base case
        if (ops == null || ops.length < 1)
            return 0;

        int total = 0;
        List<Integer> list = new ArrayList<Integer>();
        for (String s : ops) {
            if (s.equals("+")) {
                // if action is '+' then look up the prev two numbers
                // and append their sum into the list
                int num1 = list.get(list.size() - 1);
                int num2 = list.get(list.size() - 2);
                list.add(num1 + num2);
            } else if (s.equals("C")) {
                // if 'C' then last valid round score is invalid
                list.remove(list.size() - 1); // discard the last stored score
            } else if (s.equals("D")) { // double the last valid round's points
                // take only the last valid round
                int num = list.get(list.size() - 1);
                list.add(num * 2);
            } else { // normal score
                list.add(Integer.parseInt(s));
            }
        }
        // compute the sum
        for (Integer i : list) {
            total += i;
        }

        return total;
    }

    /**
     * Check if the given string is an integer
     *
     * @param s
     *            Input string
     * @return true, if the string is an integer
     */
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
