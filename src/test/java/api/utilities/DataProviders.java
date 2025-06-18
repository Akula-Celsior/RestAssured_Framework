package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	/**
	 * Provides test data from an Excel file for parameterized tests.
	 *
	 * @return A two-dimensional array containing all test data.
	 * @throws Exception If an error occurs while reading the file.
	 */
	@DataProvider(name="Data")
	public String[][] getAllData() throws Exception
	{
		String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);

		int rownum = xl.getRowCount("sheet1");
		int colcount = xl.getCellCount("sheet1",1);

		String apidata[][] = new String [rownum][colcount];

		for(int i=1 ; i<=rownum ; i++)
		{
			for(int j=0; j<colcount ; j++) 
			{
				apidata[i-1][j] = xl.getCellData("sheet1", i, j);
			}
		}

		return apidata;

	}

	/**
	 * Provides usernames from an Excel file for parameterized tests.
	 *
	 * @return A one-dimensional array containing usernames.
	 * @throws Exception If an error occurs while reading the file.
	 */
	@DataProvider(name="UserNames")	
	public String[] getUserNames() throws Exception
	{
		String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);

		int rownum = xl.getRowCount("sheet1");


		String apidata[] = new String [rownum];

		for(int i=1 ; i<=rownum ; i++)
		{	
			apidata[i-1] = xl.getCellData("sheet1", i, 1);

		}

		return apidata;

	}

}



























