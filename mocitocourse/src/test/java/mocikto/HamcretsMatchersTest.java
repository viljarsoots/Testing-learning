package mocikto;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class HamcretsMatchersTest {

    @Test
    public void test(){
        List<Integer> scores = Arrays.asList(99,100,101,105);
        // scores has 4 items
        assertThat(scores, hasSize(4));
        // scores has items
        assertThat(scores, hasItems(99,100));
        // every item is < || > than something
        assertThat(scores, everyItem(greaterThan(90)));
        assertThat(scores, everyItem(lessThan(190)));

        //String
        assertThat("",isEmptyString());
        assertThat(null, isEmptyOrNullString());

        //Arrays

        Integer[] marks = {1,2,3};
        assertThat(marks, Matchers.<Integer>arrayWithSize(3));
        assertThat(marks,arrayContaining(1,2,3));
        assertThat(marks, arrayContainingInAnyOrder(2,1,3));
    }
}
