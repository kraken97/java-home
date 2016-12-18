package practice9;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {

    public static void main(String[] args) {
        int input[] = {1, 5, 0, 3, 4, 9, 2, 6, 8, 10, 14, 7, 12, 13, 15, 11};
        System.out.println(Arrays.toString(input));
        int i;
        int goal[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int h = 1;

        Puzzle initState = new Puzzle(input);
        initState.setF(h);
        System.out.println("Start state f: " + initState.getF());

        System.out.println("Starting state is: ");
        initState.printState();

        if (initState.stateCompare(goal)) {
            System.out.println("Initial State is the goal!");
            return;
        }
        PriorityQueue<Puzzle> queue = new PriorityQueue<>(
                (Puzzle puz1, Puzzle puz2) -> Integer.valueOf(puz1.getF()).compareTo(puz2.getF()));
        queue.add(initState);
        HashSet explored = new HashSet();
        HashSet fringeHash = new HashSet();
        Explored init = new Explored(initState);
        fringeHash.add(init);
        try {
            while (true) {
                Puzzle current;
                Explored exp;
                if (queue.isEmpty()) {
                    System.out.println("NO SOLUTION!\n");
                    break;
                }
                current = (Puzzle) queue.poll();
                if (current.stateCompare(goal)) {
                    System.out.println(current.getCost() + " steps.\n");
                    int finalpath[] = new int[current.getCost()];
                    for (i = Array.getLength(finalpath) - 1; current.getParentAction() != 0; i--) {
                        finalpath[i] = current.getParentAction();
                        current = current.getParent();
                    }
                    System.out.println("The path to the solution is below: ");
                    initState.printState();

                    for (i = 0; i < Array.getLength(finalpath); i++) {
                        if (finalpath[i] == 4) {
                            System.out.println("Step " + (i + 1) + ": " + "LEFT");
                        } else if (finalpath[i] == 6) {
                            System.out.println("Step " + (i + 1) + ": " + "RIGHT");
                        } else if (finalpath[i] == 2) {
                            System.out.println("Step " + (i + 1) + ": " + "DOWN");
                        } else {
                            System.out.println("Step " + (i + 1) + ": " + "UP");
                        }
                        initState.performAction(initState.blankPosition(), finalpath[i]);
                        initState.printState();
                    }
                    break;
                }
                exp = new Explored(current);
                explored.add(exp);//each time I add a state to the explored Hashset... 
                fringeHash.remove(exp);//I remove it from the frontier Hashset
                int position = current.blankPosition(); //find blank's position
                int actions_count, j;
                int actionArr[];//in this array the possible actions are stored.
                //this is tha action format: 8 for UP, 2 for DOWN, 4 for LEFT and 6 for RIGHT
                if (position == 0) { //it can only move RIGHT and DOWN
                    actions_count = 2;
                    actionArr = new int[2];
                    actionArr[0] = 2;
                    actionArr[1] = 6;
                } else if (position == 1 || position == 2) {//it can move LEFT, DOWN and RIGHT
                    actions_count = 3;
                    actionArr = new int[3];
                    actionArr[0] = 2;
                    actionArr[1] = 4;
                    actionArr[2] = 6;
                } else if (position == 3) { //it can move LEFT and DOWN
                    actions_count = 2;
                    actionArr = new int[2];
                    actionArr[0] = 2;
                    actionArr[1] = 4;
                } else if (position == 4 || position == 8) {//it can move UP, RIGHT and DOWN
                    actions_count = 3;
                    actionArr = new int[3];
                    actionArr[0] = 2;
                    actionArr[1] = 6;
                    actionArr[2] = 8;
                } else if (position == 5 || position == 6 || position == 9 || position == 10) {//it can move towards ALL directions
                    actions_count = 4;
                    actionArr = new int[4];
                    actionArr[0] = 2;
                    actionArr[1] = 4;
                    actionArr[2] = 6;
                    actionArr[3] = 8;
                } else if (position == 7 || position == 11) {//it can move LEFT, UP and DOWN
                    actions_count = 3;
                    actionArr = new int[3];
                    actionArr[0] = 2;
                    actionArr[1] = 4;
                    actionArr[2] = 8;
                } else if (position == 12) {//it can move UP and RIGHT
                    actions_count = 2;
                    actionArr = new int[2];
                    actionArr[0] = 6;
                    actionArr[1] = 8;
                } else if (position == 13 || position == 14) {//it can move UP, LEFT and RIGHT
                    actions_count = 3;
                    actionArr = new int[3];
                    actionArr[0] = 4;
                    actionArr[1] = 6;
                    actionArr[2] = 8;
                } else {//then position is 15 -- it can move LEFT and UP 
                    actions_count = 2;
                    actionArr = new int[2];
                    actionArr[0] = 4;
                    actionArr[1] = 8;
                }
                for (j = 0; j < actions_count; j++) {//for each possible action
                    Puzzle child;
                    child = new Puzzle(current, actionArr[j]);
                    child.performAction(position, actionArr[j]); //change the state of the Puzzle according to the position of the blank and the action
                    child.setF(h);
                    Explored childexp = new Explored(child); //this will be used for searching the two Hashsets as well as for adding the state of the newly created object to the frontier Hashset
                    //if not in the frontier and not in the explored 
                    if (!explored.contains(childexp) && !fringeHash.contains(childexp)) {
                        queue.add(child); //if goal is not reached then add the newly created child to the frontier
                        fringeHash.add(childexp);//and add his state to the frontier HashSet
                    }
                }
            }
        } catch (OutOfMemoryError e) {
            System.err.println("Out of memory");
        }
    }
}
