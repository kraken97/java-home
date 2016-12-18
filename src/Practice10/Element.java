package Practice10;

import java.util.Iterator;

/**
 * Created by kraken on 19.11.16.
 */
public class Element implements Comparable<Element> {
    public  boolean visited;
    public  int value;
    Element(int n){
        this.value=n;
    }

    @Override
    public int compareTo(Element element) {
        return Integer.compare(this.value,element.value);
    }
}
