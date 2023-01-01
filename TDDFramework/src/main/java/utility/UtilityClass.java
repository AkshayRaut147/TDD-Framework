package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;

public class UtilityClass extends TestBase {

	static String path = ""; // path of excel file

	static File file;

	static FileInputStream FIS;

	static XSSFWorkbook wb;

	static XSSFSheet sheet;
	
	static int count;

	public void screenshot(String ScreenshotName) 
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File("../TDDFramework/Screenshots/"+ScreenshotName+count+".png");
		try 
		{
			FileHandler.copy(src, des);
			count++;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void expliWaitClickable(WebElement element) 
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void expliWaitAlert()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public String exelRead(int sheetIndex, int rownum, int cellnum)
	{
		file = new File(path);
		String data="";
		
		try 
		{
			FIS = new FileInputStream(file);
			wb = new XSSFWorkbook(FIS);
		
			sheet = wb.getSheetAt(sheetIndex);

			XSSFRow row = sheet.getRow(rownum);

			XSSFCell cell = row.getCell(cellnum);

			data = cell.getStringCellValue();
			wb.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return data;
	}

	public void exelWrite(int sheetIndex, int rownum, int cellnum, String data) 
	{
		file = new File(path);

		try 
		{
			FIS = new FileInputStream(file);
			wb = new XSSFWorkbook(FIS);

			sheet = wb.getSheetAt(sheetIndex);

			XSSFRow row = sheet.getRow(rownum);

			row.createCell(cellnum).setCellValue(data);

			FileOutputStream fos = new FileOutputStream(file);

			wb.write(fos);
			wb.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public int exelRowCount(int sheetIndex) 
	{
		file = new File(path);
		int rownum = 0;
		
		try 
		{
			FIS = new FileInputStream(file);
			wb = new XSSFWorkbook(FIS);

			sheet = wb.getSheetAt(sheetIndex);

			rownum = sheet.getLastRowNum();

			wb.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return rownum;
	}

	public int exelColumnCount(int sheetIndex)
	{
		file = new File(path);
		int cellnum = 0;

		try 
		{
			FIS = new FileInputStream(file);
			wb = new XSSFWorkbook(FIS);

			sheet = wb.getSheetAt(sheetIndex);

			cellnum = sheet.getRow(0).getLastCellNum();

			wb.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return cellnum;
	}

	public void scroll(int x, int y) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + x + "," + y + ")");
	}
	
	public static void switchToChild()
	{
		Set<String> IDs = driver.getWindowHandles();
		Iterator <String> It = IDs.iterator();
		String Parent_ID = It.next();
		String Child1_ID = It.next();
		
		boolean b = It.hasNext();
		
		if(b)
		{
			String Child2_ID = It.next();
			driver.switchTo().window(Child2_ID);
		}
		else
		{
			driver.switchTo().window(Child1_ID);
		}
	}
	
	public static void dropdownHandler(WebElement element,String OptionValue)
	{
		Select s = new Select(element);
		s.selectByVisibleText(OptionValue);
	}
}
