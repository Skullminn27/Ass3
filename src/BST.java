import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {
    public static class Entry<K, V> {
        private K key;
        private V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
    }
    private class Node {
        K key;
        V val;
        Node left, right;
        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    private Node root;
    private int size;
    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }
        Node current = root, parent = null;
        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else {
                current.val = val;
                return;
            }
        }
        if (key.compareTo(parent.key) < 0) parent.left = new Node(key, val);
        else parent.right = new Node(key, val);
        size++;
    }
    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return current.val;
        }
        return null;
    }
    public void delete(K key) {
        Node current = root, parent = null;
        while (current != null && !current.key.equals(key)) {
            parent = current;
            if (key.compareTo(current.key) < 0) current = current.left;
            else current = current.right;
        }
        if (current == null) return;
        if (current.left != null && current.right != null) {
            Node sParent = current;
            Node s = current.right;
            while (s.left != null) {
                sParent = s;
                s = s.left;
            }
            current.key = s.key;
            current.val = s.val;
            parent = sParent;
            current = s;
        }
        Node child;
        if (current.left != null) child = current.left;
        else child = current.right;
        if (parent == null) root = child;
        else if (parent.left == current) parent.left = child;
        else parent.right = child;
        size--;
    }
    public int size() {
        return size;
    }
    @Override
    public Iterator<Entry<K, V>> iterator() {
        List<Entry<K, V>> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            list.add(new Entry<>(current.key, current.val));
            current = current.right;
        }
        return list.iterator();
    }
}