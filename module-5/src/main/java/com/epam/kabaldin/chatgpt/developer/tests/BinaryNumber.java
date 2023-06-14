package com.epam.kabaldin.chatgpt.developer.tests;

public class BinaryNumber {
    /**
     * Removes all unset bits ('0's) from the given binary number string and returns the corresponding number.
     *
     * @param number the binary number string to process
     * @return the resulting number after removing unset bits
     */
    public static long eliminateUnsetBits(String number) {
        long result = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '1') {
                result = (result << 1) | 1;
            }
        }
        return result;
    }
}
