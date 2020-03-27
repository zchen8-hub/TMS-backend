package CS542.group6.TMS;

import CS542.group6.TMS.util.Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TmsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testGenerateRandomString(){
		System.out.println(Util.GenerateRandomString(10));
	}

}
