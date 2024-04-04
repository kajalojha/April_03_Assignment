package April_03_Assignment;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q1_Text_Justification {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int left = 0;

        while (left < words.length) {
            int right = findRight(words, left, maxWidth);
            result.add(justify(words, left, right, maxWidth));
            left = right + 1;
        }

        return result;
    }

    private static int findRight(String[] words, int left, int maxWidth) {
        int right = left;
        int sum = words[right++].length();

        while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth) {
            sum += 1 + words[right++].length();
        }

        return right - 1;
    }

    private static String justify(String[] words, int left, int right, int maxWidth) {
        if (right - left == 0) {
            return padRight(words[left], maxWidth);
        }

        boolean isLastLine = right == words.length - 1;
        int numOfSpaces = right - left;
        int totalSpaces = maxWidth - wordLength(words, left, right);
        int spaceBetweenWords = isLastLine ? 1 : totalSpaces / numOfSpaces;
        int extraSpaces = isLastLine ? 0 : totalSpaces % numOfSpaces;

        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            sb.append(words[i]);
            if (i < right) {
                int spaces = spaceBetweenWords + (extraSpaces-- > 0 ? 1 : 0);
                sb.append(padRight("", spaces));
            }
        }

        return sb.toString();
    }

    private static String padRight(String word, int spaces) {
        StringBuilder sb = new StringBuilder(word);
        for (int i = 0; i < spaces; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private static int wordLength(String[] words, int left, int right) {
        int length = 0;
        for (int i = left; i <= right; i++) {
            length += words[i].length();
        }
        return length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of words:");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        String[] words = new String[n];
        System.out.println("Enter the words:");
        for (int i = 0; i < n; i++) {
            words[i] = scanner.nextLine();
        }

        System.out.println("Enter the maximum width:");
        int maxWidth = scanner.nextInt();

        List<String> justifiedText = fullJustify(words, maxWidth);
        System.out.println("Justified Text:");
        for (String line : justifiedText) {
            System.out.println(line);
        }
    }
}
