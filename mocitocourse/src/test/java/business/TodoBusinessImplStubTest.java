package business;

import static org.junit.Assert.*;

import java.util.List;

import business.TodoBusinessImpl;
import org.junit.Test;
import service.TodoService;
import service.TodoServiceStub;


public class TodoBusinessImplStubTest {

	@Test
	public void usingAStub() {
		TodoService todoService = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");
		assertEquals(2, todos.size());
	}
}