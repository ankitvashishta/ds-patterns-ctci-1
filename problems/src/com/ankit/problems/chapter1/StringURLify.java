package com.ankit.problems.chapter1;


/**
 * Problem Statement :
 * Given a string, replace all spaces in a string with'%20'.
 * Assume that the string has sufficient space at the end to hold additional characters, and that you are given the
 * 'true' length of the string
 * <p>
 * Example :
 * Input : "Mr John Smith    ", 13
 * Output : "Mr%20John%20Smith"
 */
public class StringURLify {

    public static void main(String args[]) {
        String testString = "Mr John Smith    ";
        System.out.println(urlifyGivenString(testString, 13));
    }

    /**
     * The algorithm employs a two-scan approach. In the first scan, we count the number of spaces
     * In the second scan, done in reverse order, we edit the string, and replace a space with '%20'
     *
     * Also, since in Java, Strings are immutable, and every operation on String would create another String,
     * and hence memory. Therefore, we used a char array to do the manipulation.
     *
     * Runtime Complexity : 2 * O(n) - for both the scans = ~ O(n)
     * Space Complexity : 2 * O(n) - for the char array, and the final String created = ~ O(n).
     * @param str
     * @param trueLength
     * @return
     */
    public static String urlifyGivenString(String str, int trueLength) {
        int countSpaces = 0;
        for (int i = 0; i < trueLength; i++) {
            if (str.charAt(i) == ' ')
                countSpaces++;
        }
        int urlStringLength = trueLength + 2 * countSpaces;
        char[] stringChar = str.toCharArray();
        int indexToEnter = urlStringLength - 1;

        for (int i = trueLength - 1; i >= 0; i--) {
            if (str.charAt(i) == ' ') {
                stringChar[indexToEnter--] = '0';
                stringChar[indexToEnter--] = '2';
                stringChar[indexToEnter--] = '%';
            }else{
                stringChar[indexToEnter--] = str.charAt(i);
            }
        }
        return new String(stringChar);
    }
}
