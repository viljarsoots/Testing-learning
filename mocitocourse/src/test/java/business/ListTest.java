package business;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void mockListSizeMethod(){
       List listMock = mock(List.class);
       when(listMock.size()).thenReturn(2);
       assertEquals(2, listMock.size());
    }

    @Test
    public void mockListSize_ReturnMultipleValues(){
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3);
        assertEquals(2, listMock.size());
        assertEquals(3, listMock.size());
    }

    @Test
    public void mockListGet(){
        List listMock = mock(List.class);
        when(listMock.get(0)).thenReturn("Hello World");
        assertEquals("Hello World", listMock.get(0));
        assertEquals(null, listMock.get(1));

    }

    @Test(expected = RuntimeException.class)
    public void mockList_throwAnException(){
        List listMock = mock(List.class);
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("I am a nasty EXCEPTION"));
        listMock.get(0);
    }

    @Test(expected = RuntimeException.class)
    public void mockList_mixingUp(){
        List listMock = mock(List.class);
        when(listMock.subList(anyInt(),5)).thenThrow(new RuntimeException("I am a nasty EXCEPTION"));
        listMock.get(0);
    }


    @Test
    public void mockListGet_BDD(){
        //Given
        List listMock = mock(List.class);
        given(listMock.get(0)).willReturn("Hello World");

        //When
        String firstElement = (String) listMock.get(0);

        // Then
        assertThat( listMock.get(0), CoreMatchers.<Object>is("Hello World"));


    }

}
