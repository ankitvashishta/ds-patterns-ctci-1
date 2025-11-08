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
        String testString1 = "aaaaabbaaacccc"; // Should print : a5b2a3c4
        String testString2 = "aaaaabbaaaccccd"; // Should print : a5b2a3c4d1
        String testString3 = "aabbccdd"; // Should print : aabbccdd - as the compressed String would be the same size as original String.
        String testString4 = "abcd"; // Should print : abcd - as the compressed String would be of greater size as original String.

        System.out.println("***** StringBuilder Compression Method *****");
        System.out.println(compressString(testString1));
        System.out.println(compressString(testString2));
        System.out.println(compressString(testString3));
        System.out.println(compressString(testString4));

        System.out.println("***** StringBuilder Comparison & Compression Method *****");
        System.out.println(checkAndCompress(testString1));
        System.out.println(checkAndCompress(testString2));
        System.out.println(checkAndCompress(testString3));
        System.out.println(checkAndCompress(testString4));

    }

    /**
     * The algorithm here uses a {@link StringBuilder} instead of using {@link String} concatenations - as the String
     * concatenations operates in O(n^2) time. And the runtime of the algorithm would become O(p + k^2), where p is the
     * size of the original String and k is the number of character sequences. Also every concatenation operation
     * on String would create a new object adding to Space Complexity as well.
     * <p>
     * While when using StringBuilder, the runtime complexity of appending to a StringBuilder in Java is
     * generally considered to be amortized O(1) for appending single characters or small strings, and O(m)
     * when appending a string of length m - StringBuilder internally uses a resizable character array, similar to
     * how ArrayList works with elements. When appending, if there is enough capacity in the internal array,
     * the character or string is simply copied to the next available position, which is an O(1) operation.
     * When the capacity is exceeded, the StringBuilder allocates a new, larger array and copies the contents of the
     * old array to the new one. While this copying operation is O(N) (where N is the current length of the StringBuilder),
     * it happens infrequently enough that the average cost of appending over many operations is amortized O(1).
     * <p>
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

    /**
     * Instead of creating the compressed String and returning the shorter of the original vs compressed String,
     * here, we check in advance. It avoids us having to create a String that we'll never use. However, the downside is
     * that it causes a second loop through the characters of the String.
     * However, the benefit of this approach is that we initialise the {@link StringBuilder} to its required capacity.
     * Without this, the  StringBuilder needs to double its capacity every time it hits capacity.
     * Runtime Complexity : O(n) + O(n) - for iterating through the loop two times. = ~ O(n)
     * Space Complexity : O(N) - for storing the compressed String. Here N is the length of the final String.
     * @param str - the String to compress
     * @return - the compressed String if its size is lesser than the original String.
     */
    public static String checkAndCompress(String str) {
        int compressedLength = requiredLengthOfCompressedString(str);
        if (compressedLength >= str.length())
            return str;
        StringBuilder compressed = new StringBuilder(compressedLength);
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            counter++;
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(counter);
                counter = 0;
            }
        }
        return compressed.toString();

    }

    private static int requiredLengthOfCompressedString(String str) {
        int compressedLength = 0;
        int counter = 0;

        for (int i = 0; i < str.length(); i++) {
            counter++;
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressedLength = compressedLength + 1 + String.valueOf(counter).length();
                counter = 0;
            }
        }
        return compressedLength;
    }
}
