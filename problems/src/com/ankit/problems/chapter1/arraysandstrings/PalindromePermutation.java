package com.ankit.problems.chapter1.arraysandstrings;


import java.util.HashMap;
import java.util.Map;

/**
 * Problem Statement :
 * Given a string, check if it is a permutation of a palindrome.
 * Palindrome - a word or phrase that is same forwards and backwards.
 * Permutation - rearrangement of letters, does not necessarily have to be a dictionary word.
 * <p>
 * Example :
 * Input : Tact Coa
 * Output : True(permutations : "taco cat", "atco cta", etc.)
 * One important thing to note here is to check whether the String is to be considered case-sensitive OR not.
 * Also, check the character set the function should support. In this case, we are supporting only characters,
 * and that too case-insensitive. Hence, we turn the String to lowercase.
 * <p>
 * For a set of characters to be the same way forwards and backwards, we need to have an
 * even number of almost all characters, so that half can be on one side and half can be on the other side.
 * At most, one character(the middle character) can have an odd count.
 */
public class PalindromePermutation {

    public static void main(String[] args) {

        String testString = "Tact Coad";
        String str = testString.toLowerCase();

        System.out.println("***** Hash Map method *****");
        HashMap<Character, Integer> charFrequencyMap = buildCharFrequencyMap(str);
        System.out.println(checkMaxOneOdd_HashMap(charFrequencyMap));
        System.out.println();

        System.out.println("***** Array method *****");
        int[] charFrequencyArray = buildCharFrequencyArray(str);
        System.out.println(checkMaxOneOdd_Array(charFrequencyArray));
        System.out.println();

    }

    //********************* Approach 1 - Hash Map **********************

    /**
     * The algorithm uses a @java.util.HashMap to store the frequency of every character.
     * In Java, HashMap functions like put (add), get (fetch/search), and remove have an average-case
     * time complexity of O(1) (constant time) and a worst-case complexity of O(log n). We'll consider average case here.
     * <p>
     * Runtime Complexity : Scan through the String to convert to char array (O(n)) + scan on the char array itself
     * to determine the frequency of every char (O(n)) = O(n) + O(n) = ~ O(n)
     * Space Complexity : O(n) - The space complexity of a hash map (or hash table) for an input of size n
     * is typically O(n).
     *
     * @param str - the input string.
     * @return - map of the character frequency.
     */
    public static HashMap<Character, Integer> buildCharFrequencyMap(String str) {
        HashMap<Character, Integer> charFrequencyMap = new HashMap<>();
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        for (Character c : str.toCharArray()) {
            int charIntValue = Character.getNumericValue(c);
            if (charIntValue >= a && charIntValue <= z) {
                charFrequencyMap.put(c, charFrequencyMap.getOrDefault(c, 0) + 1);
            }
        }
        return charFrequencyMap;
    }

    /**
     * Here, we scan through the hash map to determine if there is at most one character with odd occurrences.
     * Runtime Complexity : O(n) - for scanning through the hash map.
     *
     * @param charFrequencyMap - the hashmap containing character frequency.
     * @return - boolean, if at most one char has odd frequency.
     */
    public static boolean checkMaxOneOdd_HashMap(HashMap<Character, Integer> charFrequencyMap) {
        boolean foundOdd = false;
        for (Map.Entry<Character, Integer> entry : charFrequencyMap.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                if (foundOdd)
                    return false;
                foundOdd = true;
            }
        }
        return true;
    }

    //********************* Approach 2 - Array **********************

    /**
     * The algorithm uses an array to save the frequency of small case letters occurring in the string.
     * <p>
     * Runtime Complexity : Scan through the String to convert to char array (O(n)) + scan on the char array itself
     * to determine the frequency of every char (O(n)) = O(n) + O(n) = ~ O(n)
     * Space Complexity : O(1) - as the array is of fixed size.
     * <p>
     * On a higher level, both the algorithms have a Runtime Complexity of O(n). But this algorithm is more efficient,
     * as its average and worst time complexity for fetching the char frequency from the data structure is O(1) - index
     * based, rather than when we use a hash map.
     *
     * @param str - the input sting, in lower case.
     * @return - int array with frequency of characters via index.
     */
    public static int[] buildCharFrequencyArray(String str) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int[] charFrequencyArray = new int[(z - a) + 1];
        for (Character c : str.toCharArray()) {
            int charIntValue = Character.getNumericValue(c);
            if (charIntValue >= a && charIntValue <= z) {
                // Need to subtract 'a' from the character int value to fetch the index in the char array.
                // As the numerical value of 'a' is 10 and the array index starts from 0.
                charFrequencyArray[charIntValue - a] = charFrequencyArray[charIntValue - a] + 1;
            }
        }
        return charFrequencyArray;
    }

    /**
     * Here, we scan through the array to determine if there is at most one character with odd occurrences.
     * Runtime Complexity : O(1) - for scanning through the array of fixed size.
     *
     * @param charFrequencyArray - frequency of characters.
     * @return boolean, if at most one char has odd frequency.
     */
    public static boolean checkMaxOneOdd_Array(int[] charFrequencyArray) {
        boolean foundOdd = false;
        for (int count : charFrequencyArray) {
            if (count % 2 == 1) {
                if (foundOdd)
                    return false;
                foundOdd = true;
            }
        }
        return true;
    }
}
