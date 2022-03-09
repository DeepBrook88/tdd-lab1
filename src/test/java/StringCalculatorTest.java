import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StringCalculatorTest {

    StringCalculator test;
    Logger mockedLogger;

    @BeforeEach
    void setUp() {
        mockedLogger = mock(Logger.class);
        test = new StringCalculator(mockedLogger);
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
    @Test
    void addAnyNumStringWithNegativeNumThrowsException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> test.add("//,\n1,-2,3")
        );
        assertEquals("Negatives not allowed - -2", exception.getMessage());
    }
    @Test
    void addLargeNumberLogged(){
        assertEquals(1001, test.add("1001"));
        verify(mockedLogger).log(1001);
    }
}
