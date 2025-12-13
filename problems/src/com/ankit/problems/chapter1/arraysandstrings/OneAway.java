package com.ankit.problems.chapter1.arraysandstrings;


/**
 * Problem Statement :
 * There are 3 types of operations performed on strings - insert a character, remove a character, replace a character.
 * Given two strings, write a function to check if they are one edit OR zero edits away.
 * Examples :
 * pale, ple -> true
 * pales, pale -> true
 * pale, bale -> true
 * pale, bake -> false
 */
public class OneAway {


    public static void main(String[] args) {

        System.out.println("***** Strings passed : pale, bale ******");
        System.out.println(oneEditAway("pale", "bale"));

        System.out.println("***** Strings passed : pale, pale ******");
        System.out.println(oneEditAway("pale", "pale"));

        System.out.println("***** Strings passed : pale, ple ******");
        System.out.println(oneEditAway("pale", "ple"));

        System.out.println("***** Strings passed : pale, pales ******");
        System.out.println(oneEditAway("pale", "pales"));

    }

    /**
     * The algorithm checks the two Strings whether they are one replacement/insertion/removal away.
     * Depending on the lengths of the passed strings, the logic of checking whether its one of the 3 processes it
     * would fall into is decided.
     * 1. Replacement - Example : pale, bale. For replacement, the Strings will be of equal size/length.
     * 2. Insertions - Example : pale, ple. Here, the 2nd string would need to insert an additional 'a' to be equal.
     * 3. Removal - Example : pale, pales. Here, the 2nd string would need to remove the 's' at the end. Its actually,
     * the inverse of Insertion algorithm. Hence, to avoid boilerplate code, we'll reuse insertion mechanism for this,
     * by reversing the position of Strings when calling the method.
     * <p>
     * Runtime Complexity : O(n), where n is the length of the shorter String. As the algorithm(s) - any of them,
     * scans the Strings once.
     *
     * @param str1 - The first passed string.
     * @param str2 - The second passed string.
     * @return boolean - whether the two Strings are just one edit away.
     */
    public static boolean oneEditAway(String str1, String str2) {
        if (str1.equals(str2)) {
            System.out.println("The strings are equal");
            return true;
        }

        if (Math.abs(str1.length() - str2.length()) > 1)
            return false;

        if (str1.length() == str2.length()) {
            System.out.println("Process applied : Replacement");
            return oneReplacementAway(str1, str2);
        }
        if (str1.length() - str2.length() == 1) {
            System.out.println("Process applied : Insertion");
            return oneInsertionAway(str1, str2);
        }
        if (str2.length() - str1.length() == 1) {
            System.out.println("Process applied : Removal");
            return oneInsertionAway(str2, str1);
        }
        return false;
    }

    public static boolean oneReplacementAway(String str1, String str2) {
        boolean differenceFound = false;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (differenceFound)
                    return false;
                differenceFound = true;
            }
        }
        return true;
    }

    public static boolean oneInsertionAway(String str1, String str2) {
        boolean differenceFound = false;
        int index1 = 0, index2 = 0;
        while (index1 < str1.length() && index2 < str2.length()) {
            if (str1.charAt(index1) != str2.charAt(index2)) {
                if (differenceFound)
                    return false;
                differenceFound = true;
                index1++;
            } else {
                index1++;
                index2++;
            }
        }
        return true;
    }
}
