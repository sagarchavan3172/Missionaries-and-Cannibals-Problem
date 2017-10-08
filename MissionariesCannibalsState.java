import java.util.ArrayList;

public class MissionariesCannibalsState {

	State currentState;
	MissionariesCannibalsState parentState;

	MissionariesCannibalsState(State MCState, MissionariesCannibalsState parent) {
		currentState = MCState;
		parentState = parent;

	}

	MissionariesCannibalsState() {
		currentState = null;
		parentState = null;
	}

	ArrayList<State> generateChilds() {

		ArrayList<State> childNodes = new ArrayList<State>();
		State temp = new State();
		int MissionaryIndex;
		int cannibalIndex;
		int boat;
		int count;

		int missionariesCount = 0, cannibalsCount = 0;

		if (currentState.boat == 1)
			boat = 2;
		else
			boat = 1;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1; j++) {

				if (currentState.missionaries[i][j] == currentState.boat)
					missionariesCount++;

				if (currentState.cannibals[i][j] == currentState.boat)
					cannibalsCount++;

			}
		}

		if (missionariesCount >= 1) {

			// send one Missionary
			MissionaryIndex = onlyOneMissionary(boat);
			copyState(currentState, temp);

			temp.missionaries[MissionaryIndex][0] = boat;
			temp.boat = boat;

			if (checkIfValid(temp))
				childNodes.add(temp);

			// send one missionary and one cannibal
			if (cannibalsCount >= 1) {
				MissionaryIndex = onlyOneMissionary(boat);
				cannibalIndex = onlyOneCannibals(boat);

				temp = new State();
				copyState(currentState, temp);

				temp.missionaries[MissionaryIndex][0] = boat;
				temp.cannibals[cannibalIndex][0] = boat;
				temp.boat = boat;

				if (checkIfValid(temp))
					childNodes.add(temp);

			}

			// send two missionary
			count = 0;

			if (missionariesCount > 1) {

				temp = new State();
				copyState(currentState, temp);

				for (int i = 0; i < 3; i++) {

					for (int j = 0; j < 1; j++) {

						if (currentState.missionaries[i][j] == currentState.boat && count < 2) {
							temp.missionaries[i][j] = boat;
							count++;
						}

					}
				}

				temp.boat = boat;

				if (checkIfValid(temp))
					childNodes.add(temp);

			}

		}

		// send one cannibal
		if (cannibalsCount >= 1) {
			MissionaryIndex = onlyOneCannibals(boat);
			temp = new State();
			copyState(currentState, temp);

			temp.cannibals[MissionaryIndex][0] = boat;
			temp.boat = boat;

			if (checkIfValid(temp))
				childNodes.add(temp);

			// send two cannibals

			count = 0;

			if (cannibalsCount > 1) {
				temp = new State();
				copyState(currentState, temp);

				for (int i = 0; i < 3; i++) {

					for (int j = 0; j < 1; j++) {

						if (currentState.cannibals[i][j] == currentState.boat && count < 2) {
							temp.cannibals[i][j] = boat;
							count++;
						}

					}
				}

				temp.boat = boat;

				if (checkIfValid(temp))
					childNodes.add(temp);

			}

		}

		return childNodes;
	}

	int onlyOneMissionary(int boat) {
		if (currentState.missionaries[0][0] != boat)
			return 0;
		else if (currentState.missionaries[1][0] != boat)
			return 1;
		else
			return 2;

	}

	int onlyOneCannibals(int boat) {
		if (currentState.cannibals[0][0] != boat)
			return 0;
		else if (currentState.cannibals[1][0] != boat)
			return 1;
		else
			return 2;

	}

	boolean checkIfValid(State currentState) {
		int missionariesCountOnRiver1 = 0, cannibalsCountonRiver1 = 0;
		int missionariesCountOnRiver2 = 0, cannibalsCountonRiver2 = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1; j++) {

				if (currentState.missionaries[i][j] == 1)
					missionariesCountOnRiver1++;

				if (currentState.cannibals[i][j] == 1)
					cannibalsCountonRiver1++;

				if (currentState.missionaries[i][j] == 2)
					missionariesCountOnRiver2++;

				if (currentState.cannibals[i][j] == 2)
					cannibalsCountonRiver2++;
			}
		}

		if (missionariesCountOnRiver1 >= cannibalsCountonRiver1) {
			if (missionariesCountOnRiver2 >= cannibalsCountonRiver2)
				return true;
			else if (missionariesCountOnRiver2 == 0 && cannibalsCountonRiver2 >= 1) // valid case when 1 cannibal and
																					// zero missionary
				return true;
			else
				return false;

		}
		if (missionariesCountOnRiver2 >= cannibalsCountonRiver2) {
			if (missionariesCountOnRiver1 >= cannibalsCountonRiver1)
				return true;
			else if (missionariesCountOnRiver1 == 0 && cannibalsCountonRiver1 >= 1)
				return true;
			else
				return false;
		}

		return false;
	}

	void copyState(State node, State temp) {
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 1; j++) {

				temp.missionaries[i][j] = node.missionaries[i][j];
				temp.cannibals[i][j] = node.cannibals[i][j];

			}
		}

		temp.boat = node.boat;

	}

}
