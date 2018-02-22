package easytools.ch.bean.converter;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BeanConverterTest {

	@Mock
	BeanConverter converter;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(converter.convert(argThat(new ArgumentMatcher<String>() {

			@Override
			public boolean matches(Object argument) {
				return true;
			}
		}),argThat(new ArgumentMatcher<Class<String>>() {

			@Override
			public boolean matches(Object argument) {
				return true;
			}
		}))).thenReturn("result");
	}
	
	@Test
	public void test() {
		String result = converter.convert("test", String.class);
		System.out.println(result);
	}

}
