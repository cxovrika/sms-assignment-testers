import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;

public class NameSurferGraphTester {

	private NameSurferGraph graph;

	public NameSurferGraphTester(NameSurferGraph graph) {
		this.graph = graph;
	}

	public String isBackgroundValid(boolean isEmpty) {
		boolean[] decadeSeparatorLines = new boolean[NameSurferConstants.NDECADES];
		boolean[] yearLabels = new boolean[NameSurferConstants.NDECADES];

		Iterator it = graph.iterator();

		boolean isUpperBounderDrawn = false;
		boolean isLowerBounderDrawn = false;

		while (it.hasNext()) {
			Object object = it.next();
			if (object instanceof GLine) {
				GLine line = (GLine) object;
				int ind = findDecadeSeparatorIndex(line);
				if (ind != -1) {
					decadeSeparatorLines[ind] = true;
				} else if (isUpperBounder(line)) {
					isUpperBounderDrawn = true;
				} else if (isLowerBounder(line)) {
					isLowerBounderDrawn = true;
				} else if (isEmpty) {
					return "Some unexpected line detected.";
				}
			} else if (object instanceof GLabel) {
				GLabel label = (GLabel) object;
				int decade = isYearLabel(label);
				if (decade != -1) {
					yearLabels[decade] = true;
				} else if (isEmpty) {
					System.out.println(label.getLabel());
					return "Some unexpected label detected.";
				}
			}
		}

		for (int i = 1; i < decadeSeparatorLines.length; i++) {
			if (!decadeSeparatorLines[i]) {
				return "Separator for " + (NameSurferGraph.START_DECADE + (i - 1) * 10)
						+ " was not placed correctly or placed at all";
			}
		}

		for (int i = 0; i < yearLabels.length; i++) {
			if (!yearLabels[i]) {
				return "Label for " + (NameSurferGraph.START_DECADE + i * 10)
						+ " was not placed correctly or placed at all";
			}
		}

		if (!isUpperBounderDrawn) {
			return "Upper bounder line was not found";
		}

		if (!isLowerBounderDrawn) {
			return "Lower bounder line was not found";
		}

		return null;
	}
	
	
	public String hasLabelsForEntry(NameSurferEntry entry) {
		Iterator<GObject> it = graph.iterator();
		
		List<String> labelsToBeFound = new ArrayList();
		for (int i = 0; i < NameSurferConstants.NDECADES; i++) {
			labelsToBeFound.add(entry.getName() + " "
					+ ((entry.getRank(i) > NameSurferConstants.MAX_RANK || entry.getRank(i) == 0) ? "*"
							: entry.getRank(i)));
		}

		while (it.hasNext()) {
			Object object = it.next();
			if (object instanceof GLabel) {
				GLabel label = (GLabel) object;
				int decade = isYearLabel(label);
				if (decade != -1) {

				} else {
					boolean removed = labelsToBeFound.remove(label.getLabel());
					if (!removed) {
						return "Some unexpected label with text + \"" + label.getLabel() + "\" detected.";
					}
				}
			}
		}

		for (int i = 0; i < labelsToBeFound.size(); i++) {
			return "\"" + labelsToBeFound.get(i) + "\" label was not found";
		}
		
		return null;
	}
	
	public String hasGraphForEntry(NameSurferEntry entry) {
		int[] positions = new int[NameSurferConstants.NDECADES];
		for (int i = 0; i < NameSurferConstants.NDECADES; i++) {
			positions[i] = entry.getRank(i);
		}
		for (int decade = 0; decade < NameSurferConstants.NDECADES - 1; decade++) {
			GLine line = null;
			if (positions[decade] == 0) {
				double x1 = graph.getWidth() / NameSurferConstants.NDECADES * decade;
				double y1 = graph.getHeight() - NameSurferConstants.GRAPH_MARGIN_SIZE;
				GObject object = graph.getElementAt(x1, y1);
				if (object != null && object instanceof GLine) {
					line = (GLine) object;
				}
			} else {
				double x1 = graph.getWidth() / NameSurferConstants.NDECADES * decade;
				double y1 = NameSurferConstants.GRAPH_MARGIN_SIZE
						+ positions[decade] * (graph.getHeight() - 2 * NameSurferConstants.GRAPH_MARGIN_SIZE)
								/ NameSurferConstants.MAX_RANK;
				GObject object = graph.getElementAt(x1, y1);
				if (object != null && object instanceof GLine) {
					line = (GLine) object;
				}
			}
			if (line == null) {
				return "Line for " + (NameSurferConstants.START_DECADE + decade*10) + " year for entry \"" + entry.getName() + "\" was not found";
			}
		}
		return null;
	}

	public int isYearLabel(GLabel label) {
		if (label.getLocation().getY() > graph.getHeight() - NameSurferConstants.GRAPH_MARGIN_SIZE) {
			int decade = (Integer.parseInt(label.getLabel()) - NameSurferConstants.START_DECADE) / 10;

			if (label.getLocation().getX() > decade * graph.getWidth() / NameSurferConstants.NDECADES
					&& label.getLocation().getX() + label.getWidth() < (decade + 1) * graph.getWidth()
							/ NameSurferConstants.NDECADES) {
				return decade;
			}
		}
		return -1;
	}

	public int findDecadeSeparatorIndex(GLine line) {
		if (line.getStartPoint().getX() != line.getEndPoint().getX()) {
			return -1;
		}

		int index = -1;
		if ((line.getStartPoint().getY() == 0 && line.getEndPoint().getY() == graph.getHeight())
				|| (line.getStartPoint().getY() == graph.getHeight() && line.getEndPoint().getY() == 0)) {
			index = (int) (Math.round(line.getStartPoint().getX() / (graph.getWidth() / NameSurferConstants.NDECADES)));

		}

		if (index > 0 && index <= NameSurferConstants.NDECADES - 1) {
			return index;
		}

		return -1;
	}
	
	public boolean hasUpperBound() {
		GObject object = graph.getElementAt(NameSurferConstants.GRAPH_MARGIN_SIZE, 0);
		if (object != null && object instanceof GLine) {
			return true;
		}
		return false;
	}
	
	public boolean hasLowerBound() {
		GObject object = graph.getElementAt(graph.getHeight() - NameSurferConstants.GRAPH_MARGIN_SIZE, 0);
		if (object != null && object instanceof GLine) {
			return true;
		}
		return false;
	}

	public boolean isUpperBounder(GLine line) {
		if (line.getStartPoint().getY() != line.getEndPoint().getY()
				|| line.getStartPoint().getY() != NameSurferGraph.GRAPH_MARGIN_SIZE) {
			return false;
		}

		if (line.getStartPoint().getX() == 0 && line.getEndPoint().getX() == graph.getWidth()
				|| line.getStartPoint().getX() == graph.getWidth() && line.getEndPoint().getX() == 0) {
			return true;
		}
		return false;
	}

	public boolean isLowerBounder(GLine line) {
		if (line.getStartPoint().getY() != line.getEndPoint().getY()
				|| line.getStartPoint().getY() != graph.getHeight() - NameSurferGraph.GRAPH_MARGIN_SIZE) {
			return false;
		}

		if (line.getStartPoint().getX() == 0 && line.getEndPoint().getX() == graph.getWidth()
				|| line.getStartPoint().getX() == graph.getWidth() && line.getEndPoint().getX() == 0) {
			return true;
		}
		return false;
	}
}
