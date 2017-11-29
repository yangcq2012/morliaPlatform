package unitTest;

import org.junit.Test;

import com.morlia.dao.FindUserDao;
import com.morlia.entity.UserInfo;


public class TestFindUser extends BaseTest{
	@Test
	public void test1() {
		FindUserDao dao = ctx.getBean("findUserDao", FindUserDao.class);
		UserInfo user = new UserInfo();
		user.setFacebook("107266659989799");
		user.setFacebook("000");
		UserInfo res = dao.findUser(user);
		System.out.println(res);
	}
	@Test
	public void test2() {
		FindUserDao dao = ctx.getBean("findUserDao", FindUserDao.class);
		UserInfo user = new UserInfo();
		user.setUsername("liuyifei");
		user.setPassword("123456");
		int n = dao.saveUserInfo(user);
		System.out.println(n);
	}

	@Test
	public void test3() {
		FindUserDao dao = ctx.getBean("findUserDao", FindUserDao.class);
		int n = dao.changePwd(5, "654321","testEmail");
		System.out.println(n);
	}
}
