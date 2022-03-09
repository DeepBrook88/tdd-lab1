import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StringCalculatorTest {

    StringCalculator test;
    Logger mockedLogger;
    InputStream in;
    PrintStream out;

    String text;

    @BeforeEach
    void setUp() {
        mockedLogger = mock(Logger.class);
        test = new StringCalculator(mockedLogger);
        in = System.in;
        out = System.out;
        text = "StringCalculator\nUsage: enter a sequence of positive integers in one of the following ways:\n" +
                "comma separated: \"scalc '1,2,3'\"\n" + System.lineSeparator();
    }
    @AfterEach
    void tearDown() {
        System.setIn(in);
        System.setOut(out);
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
    @Test
    void mainWelcomeHelpText(){
        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        InputStream stringStream = new ByteArrayInputStream("".getBytes());
        System.setIn(stringStream);
        StringCalculator.main(new String[]{});
        assertEquals(text, outputStream.toString());
    }
}
