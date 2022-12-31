package utility;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import testBase.TestBase;



public class DataProv extends TestBase{

	@DataProvider(name="LoginCredentials")
	public String[][] getLoginData() throws IOException
	{
		UtilityClass uc = new UtilityClass();
		
		int r = uc.exelRowCount(0);
		int c = uc.exelColumnCount(0);
		
		String data[][] = new String[r][c-1];
		
		for(int i=1; i<=r; i++)
		{
			for(int j=0; j<c-1; j++)
			{
				data[i-1][j] = uc.exelRead(0, i, j);
			}
		}
		return data;
	}
}
