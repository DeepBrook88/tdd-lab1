public class StringCalculator {
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
            sum += Integer.parseInt(s);
        }
        return sum;
    }
}
