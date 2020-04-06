package powermock;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)

public class InvokingPrivateMethodTest {

	@Mock
	Dependency dependencyMock;

	@InjectMocks
	SystemUnderTest systemUnderTest;

	@Ignore
	@Test
	public void failingCase(){
		List<Integer> stats = Arrays.asList(1,2,3);
		when(dependencyMock.retrieveAllStats()).thenReturn(stats);
		systemUnderTest.methodCallingAStaticMethod();
	}


	@Test
	public void powerMockito_invokingPrivateMethodCall() throws Exception {

		when(dependencyMock.retrieveAllStats()).thenReturn(
				Arrays.asList(1, 2, 3));
		//int result = systemUnderTest.privateMethodUnderTest();

		long result = Whitebox.invokeMethod(systemUnderTest, "privateMethodUnderTest");

		assertEquals(6, result);



	}
}
