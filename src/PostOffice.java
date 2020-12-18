import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostOffice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\|");
        String first = input[0];
        String second = input[1];
        String third = input[2];

        String firstRegex = "([#$*%&])(?<String>[A-Z]+)\\1";
        Pattern firstPattern = Pattern.compile(firstRegex);
        Matcher firstMatcher = firstPattern.matcher(first);
        if (firstMatcher.find()) {
            String capitals = firstMatcher.group("String");
            for (int i = 0; i < capitals.length(); i++) {
                String askiiNum = (int) capitals.charAt(i) + ":";
                Pattern secondPattern = Pattern.compile(askiiNum + "\\d{2}");
                Matcher secondMatcher = secondPattern.matcher(second);
                if (secondMatcher.find()) {
                    String num = secondMatcher.group();
                    int length = Integer.parseInt(num.substring(num.length() - 2));
                    Pattern thirdPattern = Pattern.compile("(^|\\s)" + capitals.charAt(i) + "[\\S]+\\b");
                    Matcher thirdMatcher = thirdPattern.matcher(third);
                    while (thirdMatcher.find()) {
                        String foundWord = thirdMatcher.group().trim();
                        if (foundWord.length() == (length + 1))
                            System.out.println(foundWord);
                    }
                }
            }
        }
    }
}
