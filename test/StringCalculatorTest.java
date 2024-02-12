import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private static StringCalculator stringCalculator;

    @BeforeAll
    static void setup() {
        stringCalculator = new StringCalculator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void addReturnsZeroWhenInputStringIsEmpty(String inputString) {
        assertEquals(0, stringCalculator.add(inputString));
    }

    @Test
    void addReturnsSumOfNumbersWithDefaultDelimiter() {
        assertEquals(3, stringCalculator.add("1,2"));
        assertEquals(6, stringCalculator.add("1,2,3"));
        assertEquals(6, stringCalculator.add("1\n2,3"));
    }

    @Test
    void addReturnsSumOfNumbersWithDelimiter() {
        assertEquals(3, stringCalculator.add("//;\n1;2"));
    }

    @Test
    void addThrowsExceptionWithNegativeNumbers() {
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, ()->stringCalculator.add("-1,2"));
        assertEquals("Negatives not allowed: -1", ex.getMessage());
    }

    @Test
    void addReturnsSumOfNumbersIgnoringNumbersGreaterThan1000() {
        assertEquals(2, stringCalculator.add("1001,2"));
    }

    @Test
    void addReturnsSumOfNumbersWithVariableDelimiterLength() {
        assertEquals(6, stringCalculator.add("//[|||]\n1|||2|||3"));
    }
}
