import java.util.*;
import java.util.function.Consumer;

public class BSTmap<K extends Comparable<? super K>, V> implements Iterable<Map.Entry<K, V>> {

    protected class BNode {

        protected K key;
        protected V value;
        protected BNode left;
        protected BNode right;

        BNode(K k, V v) {
            this.key = k;
            this.value = v;
            this.left = null;
            this.right = null;
        }

        public boolean isLeaf() {
            return this.key == null;
        }
    }

    private BNode root;
    private int size;
    private int state;

    public BSTmap() {
        this.state = -1;
        this.clear();
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.root = new BNode(null, null);
        this.size = 0;
        this.state++;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    private BNode findKey(K key, BNode curr) {
        if (curr.isLeaf()) {
            return curr;
        }
        int comparison = key.compareTo(curr.key);
        if (comparison < 0) {
            return this.findKey(key, curr.left);
        } else if (comparison > 0) {
            return this.findKey(key, curr.right);
        } else {
            return curr;
        }
    }

    public V get(K key) {
        return this.get(key, this.root);
    }

    public V get(K key, BNode curr) {
        return this.findKey(key, curr).value;
    }

    public V put(K key, V val) {
        return this.put(key, val, this.root);
    }

    private V put(K key, V val, BNode curr) {
        if (curr.isLeaf()) {
            curr.key = key;
            curr.value = val;
            curr.left = new BNode(null, null);
            curr.right = new BNode(null, null);
            this.state++;
            this.size++;
            return null;
        }
        int comparison = key.compareTo(curr.key);
        if (comparison < 0) {
            return this.put(key, val, curr.left);
        } else if (comparison > 0) {
            return this.put(key, val, curr.right);
        } else {
            V old = curr.value;
            curr.value = val;
            return old;
        }
    }

    public V delete(K key) {
        BNode toDelete = this.findKey(key, this.root);
        if (toDelete.isLeaf()) {  // key not found
            return null;
        } else {
            V val = toDelete.value;
            this.size--;
            this.state++;
            this.root = this.delete(key, this.root);
            return val;
        }
    }

    public BNode delete(K key, BNode curr) {
        if (curr.isLeaf()) {
            return curr;
        }
        int comparison = key.compareTo(curr.key);
        if (comparison < 0) {
            curr.left = this.delete(key, curr.left);
        } else if (comparison > 0) {
            curr.right = this.delete(key, curr.right);
        } else {
            if (curr.left.isLeaf()) {
                curr = curr.right;
            } else if (curr.right.isLeaf()) {
                curr = curr.left;
            } else {
                BNode min = curr.right;
                while (!min.left.isLeaf()) {
                    min = min.left;
                }
                curr.key = min.key;
                curr.value = min.value;
                curr.right = this.delete(min.key, curr.right);
            }
        }
        return curr;
    }


    public Set<Map.Entry<K, V>> entries() {
        return new HashSet<Map.Entry<K, V>>(this.inOrder(this.root));
    }

    public Set<K> keys() {
        Set<K> toReturn = new HashSet<>();
        for (Map.Entry<K, V> entry : this.inOrder()) {
            toReturn.add(entry.getKey());
        }
        return toReturn;
    }

    public Collection<V> values() {
        Collection<V> toReturn = new LinkedList<>();
        for (Map.Entry<K, V> entry : this.inOrder()) {
            toReturn.add(entry.getValue());
        }
        return toReturn;
    }

    public K minKey(BNode curr) {
        if (curr == null || curr.isLeaf()) {
            return null;
        }
        if (curr.left.isLeaf()) {
            return curr.key;
        } else {
            return this.minKey(curr.left);
        }
    }

    public K biggestKey(BNode curr) {
        if (curr == null || curr.isLeaf()) {
            return null;
        }
        if (curr.right.isLeaf()) {
            return curr.key;
        } else {
            return this.biggestKey(curr.right);
        }
    }

    public Iterable<Map.Entry<K, V>> inOrder() {
        return this.inOrder(this.root);
    }

    private Collection<Map.Entry<K, V>> inOrder(BNode curr) {
        Collection<Map.Entry<K, V>> toReturn = new LinkedList<>();
        if (curr.isLeaf()) {
            return toReturn;
        }
        toReturn.addAll(this.inOrder(curr.left));
        toReturn.add(new AbstractMap.SimpleEntry(curr.key, curr.value));
        toReturn.addAll(this.inOrder(curr.right));
        return toReturn;
    }

    public BSTmap<K, V> subMap(K fromKey, K toKey) {
        BSTmap<K, V> toReturn = new BSTmap<>();
        for (Map.Entry<K, V> entry : this.inOrder()) {
            if (entry.getKey().compareTo(fromKey) >= 0
                    && entry.getKey().compareTo(toKey) <= 0) {
                toReturn.put(entry.getKey(), entry.getValue());
            }
        }
        return toReturn;
    }

    private class BSTMapIterator implements Iterator<Map.Entry<K, V>> {
        private Iterator<Map.Entry<K, V>> iter;
        private int state;

        public BSTMapIterator() {
            this.iter = BSTmap.this.inOrder().iterator();
            this.state = BSTmap.this.state;
        }

        public Map.Entry<K, V> next() {
            if (BSTmap.this.state != this.state) {
                throw new ConcurrentModificationException();
            }
            return this.iter.next();
        }

        public boolean hasNext() {
            if (BSTmap.this.state != this.state) {
                throw new ConcurrentModificationException();
            }
            return this.iter.hasNext();
        }

        public void remove() {
            return;
        }
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return this.inOrder().iterator();
    }

    @Override
    public void forEach(Consumer<? super Map.Entry<K, V>> action) { }

    @Override
    public Spliterator<Map.Entry<K, V>> spliterator() {
        return null;
    }

    public static <PK extends Comparable<? super PK>, PV> void
    print(Iterable<Map.Entry<PK, PV>> iter) {
        for (Map.Entry<PK, PV> n : iter) {
            System.out.print(n.getKey() + ": " + n.getValue());
        }
        System.out.println();
    }

}