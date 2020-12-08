package Driverfactory;

import org.testng.annotations.Test;

public class Apptest {
	@Test
	public void kickStart()
	{
		driverScript ds=new driverScript();
		try {
			ds.startTest();
			
		} catch (Throwable t) 
		{
			System.out.println(t.getMessage());
		}
	}

}
