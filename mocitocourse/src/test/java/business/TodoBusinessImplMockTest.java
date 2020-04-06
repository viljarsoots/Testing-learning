package business;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import service.TodoService;
import service.TodoServiceStub;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


public class TodoBusinessImplMockTest {

	@Test
	public void usingAMock() {
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");
		assertEquals(2, todos.size());
	}

	@Test
	public void usingBDD() {
		//Given
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

		//When

		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");

		//Then

		assertThat( todos.size(),is(2));
	}
	@Test
	public void testDeleteTodos_usingBDD() {
		//Given
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

		//When

		todoBusinessImpl
				.deleteTodosNotRelatedToSpring("Ranga");

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

		// Declare Argument Captor
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		// Define Argument captor on specific method call
		// Capture the argument

		//Given
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		//Then
		then(todoService).should().deleteTodo(stringArgumentCaptor.capture());
		assertThat(stringArgumentCaptor.getValue(),is("Learn to Dance"));
	}

	@Test
	public void testDeleteTodos_CapturingArgumentsCalledMultipleTimes() {

		// Declare Argument Captor
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		// Define Argument captor on specific method call
		// Capture the argument

		//Given
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Rock & Roll",
				"Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		//Then
		then(todoService).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
		assertThat(stringArgumentCaptor.getAllValues().size(),is(2));
	}

}
