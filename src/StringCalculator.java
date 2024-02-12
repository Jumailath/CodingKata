import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String numbers) {
        if(numbers==null || numbers.length()==0)
            return 0;

        String[] delimiterAndInput = findDelimiterAndInput(numbers);

        List<Integer> cleanedUpString = Arrays.stream(delimiterAndInput[1].split(delimiterAndInput[0]))
                .flatMap(String::lines)
                .filter(e -> e.length() > 0)
                .map(Integer::valueOf)
                .filter(e -> e<1001)
                .toList();

        String negativeNumbers = cleanedUpString.stream()
                .filter(e -> e < 0)
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        if(negativeNumbers.length()>0) {
            throw new RuntimeException("Negatives not allowed: "+negativeNumbers);
        }
        return cleanedUpString.stream().reduce(0,Integer::sum);
    }

    private String[] findDelimiterAndInput(String numbers) {
        if(numbers.startsWith("//")) {
            return numbers.lines()
                    .findFirst()
                    .map(l -> new String[] {l.substring(2), numbers.substring(l.length())})
                    .orElse(new String[] {",", numbers});
        }
        return new String[]{",", numbers};
    }
}
