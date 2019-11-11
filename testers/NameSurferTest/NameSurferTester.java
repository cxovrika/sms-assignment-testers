import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class NameSurferTester {
	public static void main(String[] args) {
		Result nameSurferResult = JUnitCore.runClasses(NameSurferTest.class);

		printResult("NameSurfer", nameSurferResult);

		Result nameSurferGraphResult = JUnitCore.runClasses(NameSurferGraphTest.class);

		printResult("NameSurferGraph", nameSurferGraphResult);

		Result nameSurferDataBaseResult = JUnitCore.runClasses(NameSurferDataBaseTest.class);

		printResult("NameSurferDataBase", nameSurferDataBaseResult);

		Result nameSurferEntryResult = JUnitCore.runClasses(NameSurferEntryTest.class);

		printResult("NameSurferEntry", nameSurferEntryResult);

	}

	public static void printResult(String testName, Result result) {
		System.out.println("********************");
		System.out.print(testName + " result: ");
		System.out.println("passed " + result.getRunCount() + "/"
				+ (result.getRunCount() - result.getFailureCount() - result.getIgnoreCount()) + ".");

		int testNum = 1;
		for (Failure failure : result.getFailures()) {
			System.out.println("Test " + Integer.toString(testNum) + " failed: " + failure.getMessage());
			testNum++;
		}
	}
}
