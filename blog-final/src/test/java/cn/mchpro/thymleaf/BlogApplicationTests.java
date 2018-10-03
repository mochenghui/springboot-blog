package cn.mchpro.thymleaf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class BlogApplicationTests {

	@Test
	public void contextLoads() {
		int i = 10;
		if(i>5) {
			System.out.println("i>5");
		}else if(i>6) {
			System.out.println("i>6");

		}else if(i>7) {
			System.out.println("i>7");

		}
	}

}
