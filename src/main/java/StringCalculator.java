import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
    Logger logger;
    StringCalculator(Logger logger){
        this.logger = logger;
    }

    public static void main(String[] args) {
        String help = "StringCalculator\nUsage: enter a sequence of positive integers in one of the following ways:\n" +
                "comma separated: \"scalc '1,2,3'\"\n";
        System.out.println(help);
        Scanner sc = new Scanner(System.in);
        String token;
        while (sc.hasNextLine() && !(token = sc.nextLine()).equals("")){
            StringCalculator calc = new StringCalculator(new StubLogger());
            if(token.matches("scalc '.*'")) {
                String regex = "(?<=\\').*(?=\\')";
                Matcher m = Pattern.compile(regex).matcher(token);
                m.find();
                String out = m.group(0);
                List<String> list = Arrays.stream(m.group(0).split("\\.")).collect(Collectors.toList());
                Matcher m2 = Pattern.compile("\\[([^\\]]+)").matcher(list.get(0));
                List<String> delims = new ArrayList<>();
                int pos = -1;
                while (m2.find(pos+1)) {
                    pos = m2.start();
                    delims.add(m2.group(1));
                }
                if ( !delims.isEmpty()) {
                    delims.replaceAll(Pattern::quote);
                    String tmp = String.join("|", delims);
                    out = list.get(1).replaceAll(tmp,",");
                }
                System.out.println("The result is " + calc.add(out));
            }
        }
    }

    public int add(String numbers) {
        String[] tokens = numbers.split("\n");
        String[] numSplit;
        int sum = 0;
        if (numbers.equals("")) {
            return 0;
        } else if (tokens[0].contains("//")) {
            String separator = tokens[0].replace("//","");
            numSplit = tokens[1].split(separator);
        }
        else {
            numSplit = numbers.split(",|\n");
        }
        for (String s : numSplit) {
            int num = Integer.parseInt(s);
            if(num < 0) {
                throw new IllegalArgumentException("Negatives not allowed - " + s);
            }
            if(num > 1000) {
                logger.log(num);
            }
            sum += num;
        }
        return sum;
    }
}
