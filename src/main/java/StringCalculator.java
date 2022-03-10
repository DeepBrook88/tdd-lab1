import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        while (sc.hasNextLine()){
            String token = sc.nextLine();
            StringCalculator calc = new StringCalculator(new StubLogger());
            if(token.matches("scalc '.*'")) {
                String regex = "(?<=\\').*(?=\\')";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(token);
                m.find();
                System.out.println("The result is " + calc.add(m.group(0)));
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
