package practice9;

import java.util.Arrays;

public class Puzzle {

    int state[];
    int parentAction;
    int cost;
    Puzzle parent;
    int f;

    public Puzzle(int[] state) {
        this.state = state;
        parentAction = 0;
        cost = 0;
    }

    public Puzzle(Puzzle parent, int action) {
        state = new int[16];
        cost = 0;
        System.arraycopy(parent.state, 0, state, 0, 16);
        parentAction = action;
        cost = parent.cost + 1;
        this.parent = parent;
    }

    public int getF() {
        return f;
    }

    public void setF(int h) {
        int i;
        if (h == 1) {
            this.f = this.cost;
            for (i = 0; i < 16; i++) {
                if (this.state[i] == 0) {
                    continue;
                }
                if (this.state[i] != i) {
                    this.f++;
                }
            }
        }
    }

    public int[] getState() {
        return state;
    }

    public void setState(int[] state) {
        this.state = state;
    }

    public int getParentAction() {
        return parentAction;
    }

    public void setParentAction(int parentAction) {
        this.parentAction = parentAction;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Puzzle getParent() {
        return parent;
    }

    public void setParent(Puzzle parent) {
        this.parent = parent;
    }

    public void printState() {
        int i;
        for (i = 0; i < 16; i++) {
            System.out.print(state[i] + "\t");
            if (i == 3 || i == 7 || i == 11) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public boolean stateCompare(int comp[]) {
        return Arrays.equals(state, comp);
    }

    public int blankPosition() {
        int i;
        for (i = 0; i < 16; i++) {
            if (state[i] == 0) {
                return i;
            }
        }
        return i;//will never be executed 
    }

    public void performAction(int position, int action) {
        if (action == 2) {//DOWN
            int temp;
            temp = state[position + 4];
            state[position + 4] = state[position];
            state[position] = temp;
        }
        if (action == 8) {//UP
            int temp;
            temp = state[position - 4];
            state[position - 4] = state[position];
            state[position] = temp;
        }
        if (action == 4) {//LEFT
            int temp;
            temp = state[position - 1];
            state[position - 1] = state[position];
            state[position] = temp;
        }
        if (action == 6) {//RIGHT
            int temp;
            temp = state[position + 1];
            state[position + 1] = state[position];
            state[position] = temp;
        }
    }

}
