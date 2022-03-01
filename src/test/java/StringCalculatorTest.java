import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {

    StringCalculator test;

    @BeforeEach
    void setUp() {
        test = new StringCalculator();
    }

    @Test
    void addEmptyString() {
        assertEquals(0,test.add(""));
    }
}
