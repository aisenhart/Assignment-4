import java.util.LinkedList;

class HashTableSeparateChaining {
    private LinkedList<Entry>[] table;
    private int M = 20000; // Fixed size of the hash table

    // Entry class for the hash table
    static class Entry {
        String key;
        int value;

        Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor
    public HashTableSeparateChaining() {
        table = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hashCode(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * 31) + key.charAt(i);
        }
        return Math.abs(hash) % M;
    }

    // Insert using a specified hash function
    public void insert(String key, int value) {
        int hashIndex = hashCode(key);
        for (Entry entry : table[hashIndex]) {
            if (entry.key.equals(key)) {
                entry.value = value; // Update if the key already exists
                return;
            }
        }
        table[hashIndex].add(new Entry(key, value)); // Add new entry
    }

    // Search for a key using a specified hash function
    public int search(String key) {
        int hashIndex = hashCode(key);
        for (Entry entry : table[hashIndex]) {
            if (entry.key.equals(key)) {
                return entry.value; // Return the associated value
            }
        }
        return -1; // Not found
    }

    public void display() {
        for (int i = 0; i < M; i++) {
            System.out.print("Bucket " + i + ": ");
            for (Entry entry : table[i]) {
                System.out.print("(" + entry.key + ", " + entry.value + ") ");
            }
            System.out.println();
        }
    }
}
