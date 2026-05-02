public class HashTable<K, V> {
    private class HashNode {
        K key;
        V value;
        HashNode next;
        HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private HashNode[] chainArray;
    private int M;
    private int size;
    public HashTable() {
        this(11);
    }
    public HashTable(int M) {
        this.M = M;
        chainArray = new HashTable.HashNode[M];
        size = 0;
    }
    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }
    public void put(K key, V value) {
        int index = hash(key);
        HashNode current = chainArray[index];
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        HashNode node = new HashNode(key, value);
        node.next = chainArray[index];
        chainArray[index] = node;
        size++;
    }
    public V get(K key) {
        int index = hash(key);
        HashNode current = chainArray[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
    public V remove(K key) {
        int index = hash(key);
        HashNode current = chainArray[index];
        HashNode prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }
    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode current = chainArray[i];
            while (current != null) {
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }
    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode current = chainArray[i];
            while (current != null) {
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }
    public int size() {
        return size;
    }
    public void printBucketSizes() {
        for (int i = 0; i < M; i++) {
            int count = 0;
            HashNode current = chainArray[i];
            while (current != null) {
                count++;
                current = current.next;
            }
            System.out.println("Bucket " + i + ": " + count);
        }
    }
}