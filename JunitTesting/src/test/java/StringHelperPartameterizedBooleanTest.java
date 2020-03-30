import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class StringHelperPartameterizedBooleanTest {

    StringHelper helper = new StringHelper();
    private String input;
    private boolean expectedAnswer;

    public StringHelperPartameterizedBooleanTest(String input, boolean expectedAnswer) {
        this.input = input;
        this.expectedAnswer = expectedAnswer;
    }
    @Parameterized.Parameters
    public static Collection testConditions(){
        return Arrays.asList(new Object[][]{
                {"ABCD", false},
                {"ABAB", true},
                {"AB", true},
                {"A", false}
        });
    }

    // Some cases to check
    // ABCD => false, ABAB => true, AB => true, A => false;

    @Test
    public void testAreFirstAndLastTwoCharactersTheSame_Parameters() {
        assertEquals(expectedAnswer, helper.areFirstAndLastTwoCharactersTheSame(input));
    }


}