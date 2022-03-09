public class StringCalculator {
    Logger logger;
    StringCalculator(Logger logger){
        this.logger = logger;
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
