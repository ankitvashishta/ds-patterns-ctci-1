package com.ankit.problems.chapter1;

/**
 * Problem Statement :
 * Implement a method to perform basic String compression using the counts of repeated characters.
 * Example : aabcccccaaa would become a2b1c5a3.
 * If the "compressed" string would not become smaller than the original String,
 * the method should return the original string.
 * Assume that the String only has uppercase and lowercase letters.
 */
public class StringCompression {

    public static void main(String[] args) {
        String testString1 = "aaaaabbaaacccc";
        // Should print : a5b2a3c4
        System.out.println(compressString(testString1));
        String testString2 = "aaaaabbaaaccccd";
        // Should print : a5b2a3c4d1
        System.out.println(compressString(testString2));
        String testString3 = "aabbccdd";
        // Should print : aabbccdd - as the compressed String would be the same size as original String.
        System.out.println(compressString(testString3));
        String testString4 = "abcd";
        // Should print : abcd - as the compressed String would be of greater size as original String.
        System.out.println(compressString(testString4));

    }

    /**
     * The algorithm here uses a {@link StringBuilder} instead of using {@link String} concatenations - as the String
     * concatenations operates in O(n^2) time. And the runtime of the algorithm would become O(p + k^2), where p is the
     * size of the original String and k is the number of character sequences. Also every concatenation operation
     * on String would create a new object adding to Space Complexity as well.
     *
     * While when using StringBuilder, the runtime complexity of appending to a StringBuilder in Java is
     * generally considered to be amortized O(1) for appending single characters or small strings, and O(m)
     * when appending a string of length m - StringBuilder internally uses a resizable character array, similar to
     * how ArrayList works with elements. When appending, if there is enough capacity in the internal array,
     * the character or string is simply copied to the next available position, which is an O(1) operation.
     * When the capacity is exceeded, the StringBuilder allocates a new, larger array and copies the contents of the
     * old array to the new one. While this copying operation is O(N) (where N is the current length of the StringBuilder),
     * it happens infrequently enough that the average cost of appending over many operations is amortized O(1).
     *
     * Runtime Complexity : O(n) - for looping through the String along its length.
     * Space Complexity : O(n) - The space complexity of appending to a StringBuilder in Java is
     * generally O(N), where N is the final length of the string.
     *
     * @param str - the String to compress
     * @return - the compressed String if its size is lesser than the original String.
     */
    public static String compressString(String str) {
        StringBuilder compressed = new StringBuilder();
        int counter = 0;

        for (int i = 0; i < str.length(); i++) {
            counter++;
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(counter);
                counter = 0;
            }
        }
        return compressed.length() >= str.length() ? str : compressed.toString();
    }
}
