package com.ankit.problems.chapter1;

import java.util.Arrays;

/**
 * Problem Statement :
 * Given two strings, write a method to decide if one string is a permutation of the other.
 * <p>
 * Clarifying criteria :
 * 1. Is the string an ASCII string OR a Unicode string.
 * 2. For simplicity, lets consider it is an ASCII String - ASCII has a total of 128 characters.
 * In case of extended ASCII character set, this size can be considered to be of 256 characters.
 */
public class CheckStringPermutations {

    public static void main(String args[]) {

        String testString1 = "ababa";
        String testString2 = "abaab";
        String testString3 = "ababb";

        System.out.println("***** Sorted String Method Using Iteration *****");
        System.out.println(sortedStringMethod_usingIteration(testString1, testString2));
        System.out.println(sortedStringMethod_usingIteration(testString1, testString3));
        System.out.println(sortedStringMethod_usingIteration(testString2, testString3));
        System.out.println();
        System.out.println("***** Sorted String Method Using Equals *****");
        System.out.println(sortedStringMethod_usingEquals(testString1, testString2));
        System.out.println(sortedStringMethod_usingEquals(testString1, testString3));
        System.out.println(sortedStringMethod_usingEquals(testString2, testString3));
        System.out.println();
        System.out.println("***** ASCII charset  method *****");
        System.out.println(asciiCharsetMethod(testString1, testString2));
        System.out.println(asciiCharsetMethod(testString1, testString3));
        System.out.println(asciiCharsetMethod(testString2, testString3));
    }

    /**
     * If we are allowed to modify the strings, we sort both the String, then iterate over the Strings
     * to check for uniqueness.
     * <p>
     * Runtime Complexity :
     * Runtime Complexity to sort the Strings - 2 * O(n Log(n)) = ~ O(n Log(n))
     * Runtime Complexity of iteration and comparison - O(n)
     * Total Runtime = O(n Log(n)) + O(n) = ~ O(n Log(n)).
     * Space Complexity : 2 * O(n) -> ~ O(n) - extra space required by the Sorting Algorithm.
     *
     * @param str1
     * @param str2
     * @return
     *
     * In this method, we use iteration method, where the loop iterates over the strings and compare every character.
     */
    public static boolean sortedStringMethod_usingIteration(String str1, String str2) {
        if(str1.length() != str2.length())
            return false;
        String sortedStr1 = sortString(str1);
        String sortedStr2 = sortString(str2);

        for(int i = 0; i < sortedStr1.length(); i++)
            if(sortedStr1.charAt(i) != sortedStr2.charAt(i))
                return false;

        return true;
    }

    /**
     * If we are allowed to modify the strings, we sort both the String, then iterate over the Strings
     * to check for uniqueness.
     * <p>
     * Runtime Complexity :
     * Runtime Complexity to sort the Strings - 2 * O(n Log(n)) = ~ O(n Log(n))
     * Runtime Complexity of comparison - O(n) if two Strings are not pointing to the same String. However, if both the
     * references are pointing to the same object, then the runtime complexity would be O(1).
     * Total Runtime = O(n Log(n)) + O(n) = ~ O(n Log(n)).
     * Space Complexity : 2 * O(n) -> ~ O(n) - extra space required by the Sorting Algorithm.
     *
     * @param str1
     * @param str2
     * @return
     *
     * In this method, we use String's inbuilt equals method
     */
    public static boolean sortedStringMethod_usingEquals(String str1, String str2) {
        if(str1.length() != str2.length())
            return false;
        String sortedStr1 = sortString(str1);
        String sortedStr2 = sortString(str2);

        for(int i = 0; i < sortedStr1.length(); i++)
            if(sortedStr1.charAt(i) != sortedStr2.charAt(i))
                return false;

        return sortedStr1.equals(sortedStr2);
    }

    /**
     * In this method, we'll create two int arrays of size 128(Size of ASCII charset),
     * where the count at index i indicates the occurrences of character i in each of the strings.
     * We'll then compare the value of each index position in both the arrays. If the two strings are
     * permutations of each other, occurrence of each character in both the Strings should match.
     * <p>
     * Runtime Complexity : O(n), where the n is the length of the string, since we'll be iterating over the String to
     * capture the occurrences of each character in the String.
     * Also, for comparison, we iterate over the array of fixed size 128, the loop will never iterate beyond 128 + 1 iterations.
     * This gives a Runtime Complexity of O(1), as it's a fixed number. Hence, total runtime would be O(n) + O(1) = ~ O(n)
     * <p>
     * However, the runtime complexity would be O(max(c,n)),
     * where c is the size of the charset, and n is the size of the string.
     * <p>
     * Space Complexity : O(1), if the size of the charset is fixed.
     * O(c) if c, the size of the charset is not fixed.
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean asciiCharsetMethod(String str1, String str2){
        if(str1.length() != str2.length())
            return false;
        int[] str1AsciiCharCount = new int[128];
        int[] str2AsciiCharCount = new int[128];

        for(int i = 0; i < str1.length(); i++){
            char charInStr1 = str1.charAt(i);
            str1AsciiCharCount[charInStr1] = str1AsciiCharCount[charInStr1] + 1;
            int charInStr2 = str2.charAt(i);
            str2AsciiCharCount[charInStr2] = str2AsciiCharCount[charInStr2] + 1;
        }
        for(int i = 0; i < 128; i++){
            if(str1AsciiCharCount[i] != str2AsciiCharCount[i])
                return false;
        }
        return true;
    }


    public static String sortString(String inputString) {
        // Converting input string to character array
        char tempArray[] = inputString.toCharArray();

        // Sorting temp array using
        Arrays.sort(tempArray);

        // Returning new sorted string
        return new String(tempArray);
    }
}


