import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class KarelTester {
	
	public static void main(String[] args) {
		Result collectNewspaperKarelResult = JUnitCore.runClasses(CollectNewspaperKarelTest.class);
		
		printResult("CollectNewspaperKarel", collectNewspaperKarelResult);
		
		Result stoneMasonKarelResult = JUnitCore.runClasses(StoneMasonKarelTest.class);
		
		printResult("StoneMasonKarel", stoneMasonKarelResult);
		
		Result midpointFindingKarelResult = JUnitCore.runClasses(MidpointFindingKarelTest.class);
		
		printResult("MidpointFindingKarel", midpointFindingKarelResult);
		
		Result checkboardKarelResult = JUnitCore.runClasses(CheckerboardKarelTest.class);
		
		printResult("CheckboardKarel", checkboardKarelResult);
		
		
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
