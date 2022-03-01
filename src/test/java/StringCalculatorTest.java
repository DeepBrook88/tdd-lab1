import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
    @Test
    void addAnyNumStringWithLineBreaks(){
        assertEquals(6,test.add("1\n2,3"));
        assertEquals(1,test.add("1,\n"));
    }
    @Test
    void addAnyNumStringWithSeparators(){
        assertEquals(6,test.add("//;\n1;2;3"));
        assertEquals(6,test.add("//,\n1,2,3"));
    }
}
