public class Main {
    public static void main(String[] args) {
        HashTable<TestingClass, String> table = new HashTable<>(11);
        for (int i = 0; i < 10000; i++) {
            table.put(new TestingClass("Name" + i, i), "Value" + i);
        }
        System.out.println("HashTable size: " + table.size());
        table.printBucketSizes();
        System.out.println(table.get(new TestingClass("Name500", 500)));
        BST<Integer, String> tree = new BST<>();
        tree.put(50, "A");
        tree.put(30, "B");
        tree.put(70, "C");
        tree.put(20, "D");
        tree.put(40, "E");
        System.out.println("BST size: " + tree.size());
        System.out.println("Get 40: " + tree.get(40));
        for (BST.Entry<Integer, String> e : tree) {
            System.out.println("key is " + e.getKey() + " and value is " + e.getValue());
        }
        tree.delete(30);
        System.out.println("After delete:");
        for (BST.Entry<Integer, String> e : tree) {
            System.out.println("key is " + e.getKey() + " and value is " + e.getValue());
        }
    }
}