package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.handler.timeout.TimeoutException;

public class Util {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
			+ "/src/test/resources/testdata/TestData.xlsx";

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;

	// for excel reading based on heading

	@SuppressWarnings("resource")
	public static String getDataFromExcel(String sheetName, String cellHeading) {
		String excelFilePath = System.getProperty("user.dir") + "/src/test/resources/testdata/TestData.xlsx";
		try {
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			Workbook workbook = new XSSFWorkbook(inputStream); // For .xlsx files

			// If you are working with .xls files, use HSSFWorkbook instead:
			// Workbook workbook = new HSSFWorkbook(inputStream);

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet not found: " + sheetName);
			}

			Row headerRow = sheet.getRow(0); // Assuming headers are in the first row

			if (headerRow != null) {
				int columnIndex = -1;
				for (int i = 0; i < headerRow.getLastCellNum(); i++) {
					Cell cell = headerRow.getCell(i);
					if (cell != null && cell.getCellType() == CellType.STRING) {
						String headerValue = cell.getStringCellValue();
						if (cellHeading.equalsIgnoreCase(headerValue)) {
							columnIndex = i;
							break;
						}
					}
				}

				if (columnIndex != -1) {
					Row dataRow = sheet.getRow(1); // Assuming data starts from the second row
					if (dataRow != null) {
						Cell cell = dataRow.getCell(columnIndex);
						if (cell != null) {
							return cell.toString();
						}
					}
				}
			}

			// Close the workbook and input stream when done
			workbook.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public int getUnique() {
		Random rand = new Random();
		// Generate random integers in range 0 to 999
		int random = rand.nextInt(1000);
		return random;
	}

	public void waitingThread(long sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String alertsWithGetText(WebDriver driver) {
		String msg = driver.switchTo().alert().getText();
		driver.switchTo().alert().dismiss();
		return msg;
	}

	// byExplicitwait and argument as two driver and
	public WebElement explicitWait(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement foundElement = wait.until(ExpectedConditions.visibilityOf(element));
		return foundElement;
	}

	/*
	 * it will check if alerts are present then handle it and get the text else it
	 * will not do anything
	 */

	public String isAlertPresent(WebDriver driver) {
		// boolean foundAlert = false;
		String Text = "";
		// WebDriverWait wait = new WebDriverWait(driver, 0 );
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Text = driver.switchTo().alert().getText();
			driver.switchTo().alert().dismiss();
		} catch (TimeoutException eTO) {
			Text = "No Alert Present";
		}
		return Text;
	}

	// js wait

	// public boolean waitForJStoLoad(WebDriver driver) {
	//
	// // WebDriverWait wait = new WebDriverWait(driver, 30);
	// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	// final JavascriptExecutor js = (JavascriptExecutor) driver;
	// // wait for Javascript to load
	// ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
	// @Override
	// public Boolean apply(WebDriver driver) {
	// return js.executeScript("return
	// document.readyState").toString().equals("complete");
	// }
	// };
	//
	// return wait.until(jsLoad);
	// }

}
