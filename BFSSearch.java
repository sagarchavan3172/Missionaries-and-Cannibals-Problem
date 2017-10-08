import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFSSearch {

	int count = 0;
	Queue<MissionariesCannibalsState> open;
	ArrayList<MissionariesCannibalsState> closed;
	Stack<MissionariesCannibalsState> path;

	BFSSearch() {
		open = new LinkedList<MissionariesCannibalsState>();
		closed = new ArrayList<MissionariesCannibalsState>();
		path = new Stack<MissionariesCannibalsState>();
	}

	void printState(MissionariesCannibalsState st) {
		String boatPosition;
		int spaceCount = 0;

		if (st.currentState.boat == 1)
			boatPosition = "Left";
		else
			boatPosition = "Right";

		System.out.println();
		System.out.print("Left:");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1; j++) {
				if (st.currentState.missionaries[i][j] == 1)
					System.out.print("M" + (i + 1) + " ");
				else
					spaceCount++;

			}
		}
		for (int i = spaceCount; i > 0; i--)
			System.out.print("   ");

		spaceCount = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1; j++) {
				if (st.currentState.cannibals[i][j] == 1)
					System.out.print("C" + (i + 1) + " ");
				else
					spaceCount++;
			}
		}

		for (int i = spaceCount; i > 0; i--)
			System.out.print("   ");

		System.out.print("	 Right:");
		spaceCount = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1; j++) {
				if (st.currentState.missionaries[i][j] == 2)
					System.out.print("M" + (i + 1) + " ");
				else
					spaceCount++;
			}
		}

		for (int i = spaceCount; i > 0; i--)
			System.out.print("   ");

		spaceCount = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1; j++) {
				if (st.currentState.cannibals[i][j] == 2)
					System.out.print("C" + (i + 1) + " ");
				else
					spaceCount++;
			}
		}

		for (int i = spaceCount; i > 0; i--)
			System.out.print("   ");

		System.out.print("	Boat Position:" + boatPosition);

	}

	void startSearch() {

		MissionariesCannibalsState rootNode = new MissionariesCannibalsState(new State(), null);

		open.add(rootNode);

		while (!open.isEmpty()) {
			count++;

			MissionariesCannibalsState currentNode = new MissionariesCannibalsState();
			currentNode = open.remove();

			if (currentNode.currentState.checkIfGoalState()) {
				System.out.println("Goal State Found");

				MissionariesCannibalsState Node = new MissionariesCannibalsState();

				Node = currentNode;

				path.push(Node);

				while (Node.parentState != null) {

					Node = Node.parentState;
					path.push(Node);
				}

				while (!path.isEmpty()) {

					printState(path.pop());

				}

				break;

			} else {

				ArrayList<State> childNodes = new ArrayList<State>();

				childNodes = currentNode.generateChilds();

				closed.add(currentNode);

				for (State childNode : childNodes) {
					if (!AlreadyEvaluated(childNode)) {
						MissionariesCannibalsState tempNode = new MissionariesCannibalsState(childNode, currentNode);

						open.add(tempNode);
					}

				}

			}

		}

	}

	boolean AlreadyEvaluated(State childNode) {
		boolean check;

		check = (checkIfStateIsInOpen(childNode) || checkIfStateIsInClosed(childNode)) ? true : false;

		return check;

	}

	boolean checkIfStateIsInOpen(State node) {
		for (MissionariesCannibalsState openNode : open) {
			if (equals(openNode, node))
				return true;
		}

		return false;

	}

	boolean checkIfStateIsInClosed(State node) {

		for (MissionariesCannibalsState closedNode : closed) {
			if (equals(closedNode, node))
				return true;
		}

		return false;
	}

	boolean equals(MissionariesCannibalsState openState, State node) {

		int openStatemissionariesCountOnRiver1 = 0, openStatecannibalsCountonRiver1 = 0;
		int openStatemissionariesCountOnRiver2 = 0, openStatecannibalsCountonRiver2 = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1; j++) {

				if (openState.currentState.missionaries[i][j] == 1)
					openStatemissionariesCountOnRiver1++;

				if (openState.currentState.cannibals[i][j] == 1)
					openStatecannibalsCountonRiver1++;

				if (openState.currentState.missionaries[i][j] == 2)
					openStatemissionariesCountOnRiver2++;

				if (openState.currentState.cannibals[i][j] == 2)
					openStatecannibalsCountonRiver2++;
			}
		}

		int nodemissionariesCountOnRiver1 = 0, nodecannibalsCountonRiver1 = 0;
		int nodemissionariesCountOnRiver2 = 0, nodecannibalsCountonRiver2 = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1; j++) {

				if (node.missionaries[i][j] == 1)
					nodemissionariesCountOnRiver1++;

				if (node.cannibals[i][j] == 1)
					nodecannibalsCountonRiver1++;

				if (node.missionaries[i][j] == 2)
					nodemissionariesCountOnRiver2++;

				if (node.cannibals[i][j] == 2)
					nodecannibalsCountonRiver2++;
			}
		}

		if (openStatemissionariesCountOnRiver1 == nodemissionariesCountOnRiver1
				&& openStatecannibalsCountonRiver1 == nodecannibalsCountonRiver1
				&& openStatemissionariesCountOnRiver2 == nodemissionariesCountOnRiver2
				&& openStatecannibalsCountonRiver2 == nodecannibalsCountonRiver2
				&& openState.currentState.boat == node.boat)
			return true;
		else
			return false;

	}

}
