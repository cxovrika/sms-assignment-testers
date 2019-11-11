import static org.junit.Assert.fail;

import java.awt.Component;
import java.awt.Container;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import acm.graphics.GLabel;
import acm.graphics.GLine;

public class NameSurferTest {
	private NameSurfer nameSurfer;

	private JLabel nameLabel = null;
	private JButton graphButton = null;
	private JButton clearButton = null;
	private JTextField nameField = null;

	private NameSurferGraph graph = null;
	private NameSurferGraphTester graphTester = null;

	private int defaultGraphWidth = 784;
	private int defaultGraphHeight = 525;

	@Before
	public void init() {
		nameSurfer = new NameSurfer();
		nameSurfer.init();

		Container pane = nameSurfer.getContentPane();
		for (int i = 0; i < pane.getComponentCount(); i++) {
			Component component = pane.getComponent(i);
			if (component instanceof JPanel) {
				JPanel panel = (JPanel) component;
				for (Component panelComp : panel.getComponents()) {
					if (panelComp instanceof JButton) {
						if (((JButton) panelComp).getText().toLowerCase().equals("graph")) {
							graphButton = (JButton) panelComp;
						} else if (((JButton) panelComp).getText().toLowerCase().equals("clear")) {
							clearButton = (JButton) panelComp;
						}
					} else if (panelComp instanceof JLabel) {
						if (((JLabel) panelComp).getText().toLowerCase().equals("name")) {
							nameLabel = (JLabel) panelComp;
						}
					} else if (panelComp instanceof JTextField) {
						if (nameField == null) {
							nameField = (JTextField) panelComp;
						} else {
							fail("Multiple text fields were found");
						}
					} else if (panelComp instanceof NameSurferGraph) {
						graph = (NameSurferGraph) panelComp;
						graph.setSize(784, 525);
						graphTester = new NameSurferGraphTester(graph);
					}
				}
			}
		}
		if (nameLabel == null) {
			fail("Name label was not found");
		}

		if (graphButton == null) {
			fail("Graph button was not found");
		}

		if (clearButton == null) {
			fail("Clear button was not found");
		}

		if (nameField == null) {
			fail("Name field was not found");
		}
	}

	@Test
	public void testGraphAction() {
		if (graph == null) {
			fail("Graph was not found");
		}

		graph.setSize(defaultGraphWidth, defaultGraphHeight);
		sleep();
		graph.update();

		nameField.setText("Elena");
		graphButton.doClick();

		NameSurferGraphTester tester = new NameSurferGraphTester(graph);
		
		NameSurferEntry entry = new NameSurferEntry("Elena 410 393 391 464 419 463 442 412 360 329 232");
		String errorMessage = tester.hasGraphForEntry(entry);

		if (errorMessage != null) {
			fail("After click on \"Graph\" button, with entry name Elena, graph should be painted");
		}
	}
	
	@Test
	public void testGraphActionLabels() {
		if (graph == null) {
			fail("Graph was not found");
		}

		graph.setSize(defaultGraphWidth, defaultGraphHeight);
		sleep();
		graph.update();

		nameField.setText("Elena");
		graphButton.doClick();

		NameSurferGraphTester tester = new NameSurferGraphTester(graph);
		
		NameSurferEntry entry = new NameSurferEntry("Elena 410 393 391 464 419 463 442 412 360 329 232");
		String errorMessage = tester.hasLabelsForEntry(entry);

		if (errorMessage != null) {
			fail("After click on \"Graph\" button, " + errorMessage.substring(0,1).toLowerCase() + errorMessage.substring(1));
		}
	}
	
	@Test
	public void testGraphActionLines() {
		if (graph == null) {
			fail("Graph was not found");
		}

		graph.setSize(defaultGraphWidth, defaultGraphHeight);
		sleep();
		graph.update();

		nameField.setText("Elena");
		graphButton.doClick();

		NameSurferGraphTester tester = new NameSurferGraphTester(graph);
		
		NameSurferEntry entry = new NameSurferEntry("Elena 410 393 391 464 419 463 442 412 360 329 232");
		String errorMessage = tester.hasGraphForEntry(entry);

		if (errorMessage != null) {
			fail("After click on \"Graph\" button, " + errorMessage.substring(0,1).toLowerCase() + errorMessage.substring(1));
		}
	}

	@Test
	public void testClearAction() {
		if (graph == null) {
			fail("Graph was not found");
		}
		
		graph.setSize(defaultGraphWidth, defaultGraphHeight);
		sleep();
		graph.update();

		String message = graphTester.isBackgroundValid(true);
		if (message != null) {
			fail(message);
		}

		nameField.setText("Elena");
		graphButton.doClick();

		clearButton.doClick();

		message = graphTester.isBackgroundValid(true);
		if (message != null) {
			fail(message);
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
