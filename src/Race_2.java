import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Race_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String regexDigit = "\\d";
        String regexLetter = "[A-Za-z]";

        Pattern digitPatern = Pattern.compile(regexDigit);
        Pattern letterPatern = Pattern.compile(regexLetter);

        List<String> participants = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());
        Map<String, Integer> rece = new HashMap<>();

        String input = scanner.nextLine();
        while (!input.equals("end of race")) {
            StringBuilder sb = new StringBuilder();
            Matcher letterMatcher = letterPatern.matcher(input);
            while (letterMatcher.find()) {
                sb.append(letterMatcher.group());
            }
            if (participants.contains(sb.toString())) {
                rece.putIfAbsent(sb.toString(), 0);
                int km = rece.get(sb.toString());

                Matcher digitMatcher = digitPatern.matcher(input);
                while (digitMatcher.find()) {
                    km += Integer.parseInt(digitMatcher.group());
                }
                rece.put(sb.toString(), km);

            }

            input = scanner.nextLine();
        }
        List<String> output = new ArrayList<>();
        output.add("1st place: ");
        output.add("2nd place: ");
        output.add("3rd place: ");

        rece.entrySet().stream()
                .sorted((r1, r2) -> r2.getValue().compareTo(r1.getValue()))
                .limit(3)
                .forEach(r -> {
                    System.out.println(output.remove(0) + r.getKey());
                });
    }
}
