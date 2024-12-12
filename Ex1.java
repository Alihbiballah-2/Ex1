package assignments.Ex1;
/**
 * This class represents a simple solution for Ex1.
 * As defined here: https://docs.google.com/document/d/1AJ9wtnL1qdEs4DAKqBlO1bXCM6r6GJ_J/r/edit/edit
 * In this assignment, we will design a number formatting converter and calculator.
 * In general, we will use Strings as numbers over basis of binary till Hexa.
 * [2-16], 10-16 are represented by A,B,..G.
 * The general representation of the numbers is as a String with the following format:
 * <number><b><base> e.g., “135bA” (i.e., “135”, as 10 is the default base), “100111b2”, “12345b6”,”012b5”, “123bG”, “EFbG”.
 * The following are NOT in the format (not a valid number):
 * “b2”, “0b1”, “123b”, “1234b11”, “3b3”, “-3b5”, “3 b4”, “GbG”, "", null,
 * You should implement the following static functions:
 */
public class Ex1 {
        /**
         * Convert the given number (num) to a decimal representation (as int).
         * It the given number is not in a valid format returns -1.
         * @param num a String representing a number in basis [2,16]
         * @return
         */
        public static int number2Int(String num) {
            // First check if the number is valid using isNumber function
            if (!isNumber(num)) {
                return -1;
            }

            // Find position of 'b' character
            int bPosition = num.indexOf('b');

            // If no 'b' found, treat as decimal
            if (bPosition == -1) {
                try {
                    return Integer.parseInt(num);
                } catch (NumberFormatException e) {
                    return -1;
                }
            }

            // Split into number and base parts
            String numberPart = num.substring(0, bPosition);
            char baseChar = num.charAt(bPosition + 1);

            // Convert base character to actual base number
            int base;
            if (Character.isDigit(baseChar)) {
                base = baseChar - '0';
            } else {
                base = 10 + (baseChar - 'A');
            }

            // Convert number using the determined base
            int result = 0;
            int power = 0;

            // Process digits from right to left
            for (int i = numberPart.length() - 1; i >= 0; i--) {
                char digit = numberPart.charAt(i);
                int digitValue;

                if (Character.isDigit(digit)) {
                    digitValue = digit - '0';
                } else {
                    digitValue = 10 + (Character.toUpperCase(digit) - 'A');
                }

                result += digitValue * Math.pow(base, power);
                power++;
            }

            return result;
        }
        /**
         * This static function checks if the given String (g) is in a valid "number" format.
         * @param a a String representing a number
         * @return true iff the given String is in a number format
         */
        public static boolean isNumber(String a) {
            // Check for null or empty string
            if (a == null || a.isEmpty()) {
                return false;
            }

            // Find position of 'b' character
            int bPosition = a.indexOf('b');

            // If no 'b' is found, treat as decimal
            if (bPosition == -1) {

                // Validate each character is a valid decimal digit
                for (char digit : a.toCharArray()) {
                    if (!Character.isDigit(digit)) {
                        return false;
                    }
                }
                return !a.isEmpty();
            }

            // If 'b' is at first or last position, format is invalid
            if (bPosition <= 0 || bPosition == a.length() - 1) {
                return false;
            }

            // Split into number and base parts
            String numberPart = a.substring(0, bPosition);
            String basePart = a.substring(bPosition + 1);

            // Convert base character to actual base number
            int base;
            if (basePart.length() == 1) {
                char baseChar = basePart.charAt(0);
                if (Character.isDigit(baseChar)) {
                    base = baseChar - '0';
                } else if (baseChar >= 'A' && baseChar <= 'G') {
                    base = 10 + (baseChar - 'A');
                } else {
                    return false;
                }
            } else {
                return false;
            }

            // Check if base is valid (2-16)
            if (base < 2 || base > 16) {
                return false;
            }

            // Validate number part against the base
            for (char digit : numberPart.toCharArray()) {
                int digitValue;
                if (Character.isDigit(digit)) {
                    digitValue = digit - '0';
                } else if (Character.toUpperCase(digit) >= 'A' &&
                        Character.toUpperCase(digit) <= 'F') {
                    digitValue = 10 + (Character.toUpperCase(digit) - 'A');
                } else {
                    return false;
                }

                // Check if digit is valid for the given base
                if (digitValue >= base) {
                    return false;
                }
            }

            return true;
        }

        /**
         * Calculate the number representation (in basis base)
         * of the given natural number (represented as an integer).
         * If num<0 or base is not in [2,16] the function should return "" (the empty String).
         * @param num the natural number (include 0).
         * @param base the basis [2,16]
         * @return a String representing a number (in base) equals to num, or an empty String (in case of wrong input).
         */
        public static String int2Number(int num, int base) {
            // Handle invalid cases
            if (num < 0 || base < 2 || base > 16) {
                return "";
            }

            // Handle zero case
            if (num == 0) {
                return "0";
            }

            StringBuilder result = new StringBuilder();
            int number = num;

            // Convert number to specified base
            while (number > 0) {
                int remainder = number % base;
                char digit;

                // Convert remainder to appropriate character (0-9 or A-F)
                if (remainder < 10) {
                    digit = (char) ('0' + remainder);
                } else {
                    digit = (char) ('A' + (remainder - 10));
                }

                result.insert(0, digit);
                number = number / base;
            }

            // Add base indicator if not decimal
            if (base != 10) {
                result.append('b');
                // Convert base to character (1-9 or A-G)
                if (base < 10) {
                    result.append((char)('0' + base));
                } else {
                    result.append((char)('A' + (base - 10)));
                }
            }

            return result.toString();
        }

        /**
         * Checks if the two numbers have the same value.
         * @param n1 first number
         * @param n2 second number
         * @return true iff the two numbers have the same values.
         */
        public static boolean equals(String n1, String n2) {
            boolean ans = true;
            if(number2Int(n1) != number2Int(n2)){
                return false;
            }
            return ans;
        }

        /**
         * This static function search for the array index with the largest number (in value).
         * In case there are more than one maximum - returns the first index.
         * Note: you can assume that the array is not null and is not empty, yet it may contain null or none-valid numbers (with value -1).
         * @param arr an array of numbers
         * @return the index in the array in with the largest number (in value).
         *
         */
        public static int maxIndex(String[] arr) {
            int ans = 0;
            for(int i=0;i<arr.length;i++){
                if(ans < number2Int(arr[i])){
                    ans = number2Int(arr[i]);
                }
            }
            return ans;
        }
}
