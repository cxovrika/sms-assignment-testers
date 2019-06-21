import static org.junit.Assert.fail;

import java.awt.Color;
import java.util.Iterator;

import org.junit.Test;

import acm.graphics.GLabel;
import acm.graphics.GObject;

public class NameSurferGraphTest {
	private int defaultGraphWidth = 784;
	private int defaultGraphHeight = 525;

	@Test
	public void testEmptyWithDefaultConstants() {
		int width = defaultGraphWidth;
		int height = defaultGraphHeight;

		NameSurferGraph graph = new NameSurferGraph();
		graph.setSize(width, height);
		sleep();
		graph.update();

		NameSurferGraphTester tester = new NameSurferGraphTester(graph);
		String errorMessage = tester.isBackgroundValid(true);

		if (errorMessage != null) {
			fail(errorMessage);
		}
	}

	@Test
	public void testClear() {
		NameSurferGraph graph = new NameSurferGraph();
		graph.setSize(defaultGraphWidth, defaultGraphHeight);
		sleep();
		graph.update();

		NameSurferEntry entry = new NameSurferEntry("A 83 140 228 286 426 612 486 577 836 0 0");
		graph.addEntry(entry);
		graph.update();

		graph.clear();
		graph.update();

		NameSurferGraphTester tester = new NameSurferGraphTester(graph);
		String errorMessage = tester.isBackgroundValid(true);

		if (errorMessage != null) {
			fail("After adding an entry and clearing canvas, canvas should be valid again");
		}
	}

	@Test
	public void testBackgroundAfterEntryUsingDefaultConstants() {
		NameSurferGraph graph = new NameSurferGraph();
		graph.setSize(defaultGraphWidth, defaultGraphHeight);
		sleep();
		graph.update();

		NameSurferEntry entry = new NameSurferEntry("A 83 140 228 286 426 612 486 577 836 0 0");
		graph.addEntry(entry);
		graph.update();

		NameSurferGraphTester tester = new NameSurferGraphTester(graph);
		String errorMessage = tester.isBackgroundValid(false);

		if (errorMessage != null) {
			fail("After adding an entry, canvas should be valid");
		}
	}

	@Test
	public void testNameLabelsWithOneEntryUsingDefaultConstants() {
		NameSurferGraph graph = new NameSurferGraph();
		graph.setSize(defaultGraphWidth, defaultGraphHeight);
		sleep();
		graph.update();

		NameSurferEntry entry = new NameSurferEntry("A 83 140 228 286 426 612 486 577 836 0 0");
		graph.addEntry(entry);
		graph.update();

		NameSurferGraphTester tester = new NameSurferGraphTester(graph);

		String errorMessage = tester.hasLabelsForEntry(entry);

		if (errorMessage != null) {
			fail(errorMessage);
		}

	}

	@Test
	public void testNameLabelsDiffColorUsingDefaultConstants() {
		NameSurferGraph graph = new NameSurferGraph();
		graph.setSize(defaultGraphWidth, defaultGraphHeight);
		sleep();
		graph.update();

		NameSurferEntry entry1 = new NameSurferEntry("A 83 140 228 286 426 612 486 577 836 0 0");
		graph.addEntry(entry1);
		graph.update();

		NameSurferEntry entry2 = new NameSurferEntry("Abby 0 0 0 0 0 906 782 548 233 211 209");
		graph.addEntry(entry2);
		graph.update();

		Color color1 = null, color2 = null;
		
		Iterator<GObject> it = graph.iterator();
		while (it.hasNext()) {
			Object object = it.next();
			if (object instanceof GLabel) {
				GLabel label = (GLabel) object;
				if (label.getLabel().contains("A") && !label.getLabel().contains("Abby")) {
					color1 = label.getColor();
				}
				if (label.getLabel().contains("Abby")) {
					color2 = label.getColor();
				}
			}
		}

		if (color1 == null || color2 == null || color1 == color2) {
			fail("After adding two entries on graph, colors should be different");
		}
	}

	@Test
	public void testLinesWithOneEntryUsingDefaultConstants() {
		NameSurferGraph graph = new NameSurferGraph();
		graph.setSize(defaultGraphWidth, defaultGraphHeight);
		sleep();
		graph.update();

		graph.update();

		NameSurferEntry entry = new NameSurferEntry("A 83 140 228 286 426 612 486 577 836 0 0");
		graph.addEntry(entry);
		graph.update();

		NameSurferGraphTester tester = new NameSurferGraphTester(graph);

		String errorMessage = tester.hasGraphForEntry(entry);

		if (errorMessage != null) {
			fail(errorMessage);
		}
	}
	
	@Test
	public void testResizeUsingDefaultConstants() {
		NameSurferGraph graph = new NameSurferGraph();
		graph.setSize(defaultGraphWidth, defaultGraphHeight);
		sleep();

		NameSurferGraphTester tester = new NameSurferGraphTester(graph);

		String errorMessage = tester.isBackgroundValid(true);

		if (errorMessage != null) {
			fail("After resizing canvas, update was not made correctly");
		}
	}

	private void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
