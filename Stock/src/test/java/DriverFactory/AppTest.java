package DriverFactory;

public class AppTest {
	@org.testng.annotations.Test
	public void kickStart() throws Throwable
	{
		DriverScript ds=new DriverScript();
		try{
			ds.startTest();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
