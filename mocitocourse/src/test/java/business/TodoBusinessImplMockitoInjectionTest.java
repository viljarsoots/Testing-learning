package business;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;
import service.TodoService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class) needed without @Rule
public class TodoBusinessImplMockitoInjectionTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	TodoService todoService;

	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;

	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	public void usingAMock() {

		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");
		assertEquals(2, todos.size());
	}

	@Test
	public void usingBDD() {
		//Given
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		//When

		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");

		//Then

		assertThat( todos.size(),is(2));
	}
	@Test
	public void testDeleteTodos_usingBDD() {
		//Given
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);

		//When

		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		//Then
		// First option
		verify(todoService, atLeast(1)).deleteTodo("Learn to Dance");
		// Second Option
		then(todoService).should().deleteTodo("Learn to Dance");
		// First Option
		verify(todoService, never()).deleteTodo("Learn Spring MVC");
		// Second Option
		then(todoService).should(never()).deleteTodo("Learn Spring MVC");
		// First Option
		verify(todoService, never()).deleteTodo("Learn Spring");
		// Second Option
		then(todoService).should(never()).deleteTodo("Learn Spring");
	}


	@Test
	public void testDeleteTodos_CapturingArguments() {

		//Given
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);

		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		//Then
		then(todoService).should().deleteTodo(stringArgumentCaptor.capture());
		assertThat(stringArgumentCaptor.getValue(),is("Learn to Dance"));
	}

	@Test
	public void testDeleteTodos_CapturingArgumentsCalledMultipleTimes() {

		//Given
		List<String> allTodos = Arrays.asList("Learn Rock & Roll",
				"Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);

		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		//Then
		then(todoService).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
		assertThat(stringArgumentCaptor.getAllValues().size(),is(2));
	}

}
