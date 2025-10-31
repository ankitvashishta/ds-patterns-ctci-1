package com.ankit.problems.chapter1;

import java.util.Arrays;

/**
 * Problem Statement :
 * Implement an algorithm to determine if a string has all unique characters.
 * Also consider - what if you cannot use additional data structures.
 * <p>
 * Clarifying criteria :
 * 1. Is the string an ASCII string OR a Unicode string.
 * 2. For simplicity, lets consider it is an ASCII String - ASCII has a total of 128 characters.
 * In case of extended ASCII character set, this size can be considered to be of 256 characters.
 */

public class UniqueStringCharacters {

    public static void main(String args[]) {
        String testString1 = "alphabet";
        String testString2 = "alphbet";

        System.out.println("***** Brute Force method *****");
        if (isStringUnique_BruteForce(testString1))
            System.out.println(testString1 + " : " + "hasUniqueCharacters");
        if (isStringUnique_BruteForce(testString2))
            System.out.println(testString2 + " : " + "hasUniqueCharacters");
        System.out.println("*********************");

        System.out.println("***** Sorted String method *****");
        if (isStringUnique_UseSorting(testString1))
            System.out.println(testString1 + " : " + "hasUniqueCharacters");
        if (isStringUnique_UseSorting(testString2))
            System.out.println(testString2 + " : " + "hasUniqueCharacters");
        System.out.println("*********************");

        System.out.println("***** ASCII charset  method *****");
        if (isStringUnique_ASCIICharacterSet(testString1))
            System.out.println(testString1 + " : " + "hasUniqueCharacters");
        if (isStringUnique_ASCIICharacterSet(testString2))
            System.out.println(testString2 + " : " + "hasUniqueCharacters");
        System.out.println("*********************");
    }

    /**
     * Brute-Force Method. Runtime Complexity : O(n^2); Space Complexity : O(1)
     * Also, the case, where no additional data structure is used.
     * This method compares every character of the string to every other character of the string.
     *
     * @param str
     * @return
     */
    public static boolean isStringUnique_BruteForce(String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            char currentCharacter = str.charAt(i);
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(j) == currentCharacter)
                    return false;
            }
        }
        return true;
    }

    /**
     * If we are allowed to modify the string, we sort the String, then iterate over the String
     * to check for uniqueness.
     * <p>
     * Runtime Complexity :
     * Runtime Complexity to sort the String - O(n Log(n))
     * Runtime Complexity of iteration and comparison - O(n)
     * Total Runtime = O(n Log(n)) + O(n) = ~ O(n Log(n)).
     * Space Complexity : O(n) - extra space required by the Sorting Algorithm.
     *
     * @param str
     * @return
     */
    public static boolean isStringUnique_UseSorting(String str) {
        String sortedString = sortString(str);
        char prevChar = sortedString.charAt(0);
        for (int i = 1; i < sortedString.length(); i++) {
            char currentChar = sortedString.charAt(i);
            if (currentChar == prevChar)
                return false;
            prevChar = currentChar;
        }
        return true;
    }

    /**
     * In this method, we'll create a boolean array of size 128(Size of ASCII charset),
     * where the flag at index i indicates whether character i in the alphabet is contained in the string.
     * <p>
     * Also, if the string length exceeds the number of unique characters in the alphabet, we can return false immediately.
     * Because, no way a String of size greater than 128, can have unique characters of a 128 size character set.
     * <p>
     * Runtime Complexity : O(n), where the n is the length of the string.
     * Also, since the array is of fixed size 128, the loop will never iterate beyond 128 + 1 iterations.
     * This gives a Runtime Complexity of O(1), as it's a fixed number.
     * <p>
     * However, if the character set is not fixed, the runtime complexity would be O(min(c,n)),
     * where c is the size of the charset, and n is the size of the string.
     * <p>
     * Space Complexity : O(1), if the size of the charset is fixed.
     * O(c) if c, the size of the charset is not fixed.
     *
     * @param str
     * @return
     */
    public static boolean isStringUnique_ASCIICharacterSet(String str) {
        if (str.length() > 128)
            return false;
        boolean[] charSet = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int charVal = str.charAt(i);
            if (charSet[charVal])
                return false;
            charSet[charVal] = true;
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
