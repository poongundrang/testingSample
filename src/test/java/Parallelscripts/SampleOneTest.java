package Parallelscripts;

import org.testng.annotations.Test;

public class SampleOneTest {
  @Test
  public void testOne() {
	  long id = Thread.currentThread().getId();
	  System.out.println("TestOne from SampleOne "+id);
  }
  @Test
  public void testTwo() {
	  long id = Thread.currentThread().getId();
	  System.out.println("TestTwo from SampleOne "+id);
  }
  @Test
  public void testThree() {
	  long id = Thread.currentThread().getId();
	  System.out.println("TestThree from SampleOne "+id);
  }
  @Test(threadPoolSize =3, invocationCount = 8, timeOut=1000)
  public void testFour() {
	  long id = Thread.currentThread().getId();
	  System.out.println("TestFour from SampleOne "+id);
  }
}
