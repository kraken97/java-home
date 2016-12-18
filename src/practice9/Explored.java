package practice9;

import java.util.Arrays;

public class Explored {
    int exploredState[] = new int[16];

    public Explored(Puzzle node) {
        System.arraycopy(node.getState(), 0, exploredState, 0, 16);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(exploredState);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Explored other = (Explored) obj;
        return Arrays.equals(this.exploredState, other.exploredState);
    }

}