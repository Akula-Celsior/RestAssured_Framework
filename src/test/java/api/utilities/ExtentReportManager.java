package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	/**
	 * Initializes the test report at the start of the test execution.
	 *
	 * @param testContext The test context containing test parameters.
	 */
	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter =new ExtentSparkReporter(".\\reports\\" + repName);  // specifying the location of the report

		sparkReporter.config().setDocumentTitle("RestAssured Automation Report"); // Title of report		
		sparkReporter.config().setReportName("Pet Store User API"); // Name of report	
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pet Store User API");
		extent.setSystemInfo("Module", "User");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os =	testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser =	testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	/**
	 * Logs test execution details when a test passes.
	 *
	 * @param result The test result object containing execution details.
	 */
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.PASS, result.getName() + " got successfully executed");
	}

	/**
	 * Logs test execution details when a test fails.
	 *
	 * @param result The test result object containing execution details.
	 */
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report

		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	/**
	 * Logs test execution details when a test is skipped.
	 *
	 * @param result The test result object containing execution details.
	 */
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	/**
	 * Flushes the test report after the test execution is completed.
	 *
	 * @param testContext The test context containing execution details.
	 */
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}

