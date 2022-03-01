public class StringCalculator {
    public int add(String numbers) {
        if (numbers.equals("")) {
            return 0;
        }
        else {
            String[] numSplit = numbers.split(",|\n");
            int sum = 0;
            for (String s : numSplit) {
                sum += Integer.parseInt(s);
            }
            return sum;
        }
    }
}
