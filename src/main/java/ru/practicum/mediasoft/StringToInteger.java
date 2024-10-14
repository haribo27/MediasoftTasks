package ru.practicum.mediasoft;

public class StringToInteger {

    public static void main(String[] args) {
        System.out.println(stringToInteger("546", 10));
    }

    public static Integer stringToInteger(String str, int radix) {

        if (str == null) {
            throw new NumberFormatException("String is null");
        }

        if (radix > Character.MAX_RADIX || radix < Character.MIN_RADIX) {
            throw new NumberFormatException("Radix must be between 2 and 36");
        }

        boolean negative = false;
        int i = 0, len = str.length();
        int limit = -Integer.MAX_VALUE;

        if (len > 0) {
            char firstChar = str.charAt(0);
            if (firstChar < '0') {
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+') {
                    throw new NumberFormatException("Incorrect input string");
                }
                if (len == 1) {
                    throw new NumberFormatException(" + or - cannot be lonely at the start of the string");
                }
                i++;
            }
            int multmin = limit / radix;
            int result = 0;
            while (i < len) {
                int digit = Character.digit(str.charAt(i++), radix);
                if (digit < 0 || result < multmin) {
                    throw new NumberFormatException("Incorrect input string");
                }
                if (result < multmin + digit) {
                    throw new NumberFormatException("Overflow");
                }
                result *= radix;
                if (result < limit + digit) {
                    throw new NumberFormatException("Overflow");
                }
                result -= digit;
            }
            return negative ? result : -result;
        } else {
            throw new NumberFormatException("String is empty");
        }
    }
}
