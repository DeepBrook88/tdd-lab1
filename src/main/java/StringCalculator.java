import java.util.Scanner;

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
        if (sc.hasNextLine()){

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
