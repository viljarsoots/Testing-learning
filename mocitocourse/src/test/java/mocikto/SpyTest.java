package mocikto;

import org.junit.Test;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SpyTest {

    @Spy
    List arrayListSpy = spy(ArrayList.class);


    @Test
    public void testCaseUsingSpy(){

        assertEquals(0,arrayListSpy.size());
        //mocks return default values
        arrayListSpy.add("Dummy");
        assertEquals(1, arrayListSpy.size());
        arrayListSpy.remove("Dummy");
        assertEquals(0,arrayListSpy.size());

    }
    @Test
    public void testCaseUsingSpy_override(){

        stub(arrayListSpy.size()).toReturn(5);
        assertEquals(5, arrayListSpy.size());
    }

    @Test
    public void testCaseUsingSpy_verify(){

        arrayListSpy.add("Dummy");
        verify(arrayListSpy).add("Dummy");
        verify(arrayListSpy, never()).clear();


    }


}
