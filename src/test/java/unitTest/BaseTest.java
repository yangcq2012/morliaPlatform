package unitTest;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class BaseTest {

	protected ClassPathXmlApplicationContext ctx;

	public BaseTest() {
		super();
	}

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext(
				"conf/spring-mvc.xml","conf/spring-mybatis.xml");
	}

	@After
	public void closeCtx() {
		ctx.close();
	}
	

}