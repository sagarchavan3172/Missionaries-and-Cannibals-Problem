
public class State {

	int[][] missionaries;
	int[][] cannibals;

	int boat;

	State() {

		missionaries = new int[3][1];
		cannibals = new int[3][1];
		boat = 1;

		for (int i = 0; i < 3; i++) {

			missionaries[i] = new int[1];
			cannibals[i] = new int[1];

			for (int j = 0; j < 1; j++) {

				missionaries[i][j] = 1;
				cannibals[i][j] = 1;

			}
		}

	}

	boolean checkIfGoalState() {

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 1; j++) {

				if (missionaries[i][j] != 2 || cannibals[i][j] != 2 || boat != 2)
					return false;
			}
		}

		return true;
	}

}
