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
    @Test
    void addOneNumString(){
        assertEquals(1,test.add("1"));
    }
    @Test
    void addTwoNumString(){
        assertEquals(3,test.add("1,2"));
    }
    @Test
    void addAnyNumString(){
        assertEquals(6,test.add("1,2,3"));
    }
}
