import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class FacePamphletTester {
	public static void main(String[] args) {
		Result facePamphletResult = JUnitCore.runClasses(FacePamphletTest.class);

		printResult("FacePamphlet", facePamphletResult);

		Result facePamphletCanvasResult = JUnitCore.runClasses(FacePamphletCanvasTest.class);

		printResult("FacePamphletCanvas", facePamphletCanvasResult);

		Result facePamphletDatabaseResult = JUnitCore.runClasses(FacePamphletDatabaseTest.class);

		printResult("FacePamphletDatabase", facePamphletDatabaseResult);

		Result facePamphletProfileResult = JUnitCore.runClasses(FacePamphletProfileTest.class);

		printResult("FacePamphletProfile", facePamphletProfileResult);

	}

	public static void printResult(String testName, Result result) {
		System.out.println("********************");
		System.out.print(testName + " result: ");
		System.out.println("passed " + (result.getRunCount() - result.getFailureCount() - result.getIgnoreCount()) + "/" + result.getRunCount() + ".");

		int testNum = 1;
		for (Failure failure : result.getFailures()) {
			System.out.println("Test " + Integer.toString(testNum) + " failed: " + failure.getMessage());
			testNum++;
		}
	}
}
