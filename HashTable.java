// Hash Table implementation with arrays
public class HashTable {

    private int size;
    Entry[] entries;
    int elements;

    public void setSize(int size) {
        this.size = size;
    }

    public HashTable(int size) {
        this.size = size;
        this.elements = 0;
        this.entries = new Entry[size];
    }
// adding entries to hashtable giving key and value
    public void addEntry(int key, String value) {
        Entry entry = new Entry(key, value);
        // checking if the number of elements of
        // already existing does not exceed capacity
        if (elements < size) {
            // hashing
            int index = firstHashFunction(key);
            int step = secondHashFunction(key);
            //insertion
            // if the raw index is full, implement double hashing
            while (entries[index] != null) {
                index = (index + step) % size;
            }
            // when a null position is found, add entry
            entries[index] = entry;
            System.out.println("Element added successfully at index");
            System.out.println(index);
            // increment the total elements added
            elements++;
            //check if size limit is reached
        } else if(elements ==size) {
            System.out.println("Table is full. Rehashing");
            // if capacity has been reached, resize and rehash
            this.resizeAndRehash();
            addEntry(entry.key,entry.studentID);
        }
    }
//primary hashing function
    public int firstHashFunction(int key) {
        return key % size;
    }
// secondary hashing function
    public int secondHashFunction(int key) {
        return 1 + (key % (size + 1));
    }
// function to retrieve student id based on key
    public String get(int key) {
        int index = firstHashFunction(key);
        int step = secondHashFunction(key);
        while (entries[index]!= null && entries[index].key != key) {
            index = (index + step) % size;
        }
        if (entries[index]==null){
            return "Key does not exist";
        }
    return "Entry with the key is: " + entries[index].studentID;
    }
    // deleting entry based on a given key
    public void delete(int key) {
        int index = firstHashFunction(key);
        int step = secondHashFunction(key);
        // loop through to find key
        while (entries[index].key != key) {
            index = (index + step) % size;
        }
        entries[index] = null;
        elements--;
    }

    public void resizeAndRehash() {
        Entry[] tempEntries = entries;
        int oldSize = size;
        size = size * 2;
        //resize the hashtable
        entries = new Entry[size];
        System.out.println(entries.length);
        System.out.println("Beginning");
        // reinsert values into the resized hashtable
        for(int i =0;i<oldSize;i++){
            if (tempEntries[i]!=null){
                this.addEntry(tempEntries[i].key,tempEntries[i].studentID);
            }
        }
        System.out.println("done");
    }
// Demonstrating implementation of methods
    public static void main(String[] args) {
        //initialization and insertion
        HashTable hashTable = new HashTable(3);
        hashTable.addEntry(2,"A");
        hashTable.addEntry(13,"B");
        hashTable.addEntry(3,"C");
        System.out.println("The current table size is "+ hashTable.size);
        // this should initiate resizing and rehashing
        hashTable.addEntry(34,"D");
        hashTable.addEntry(31,"E");
        System.out.println("The current table size is "+ hashTable.size);
        // testing the retrieval function
        System.out.println(hashTable.get(2));
        // testing hashing
        hashTable.resizeAndRehash();
        System.out.println("The current table size is "+ hashTable.size);
        System.out.println(hashTable.get(2));
        hashTable.delete(2);
        System.out.println(hashTable.get(2));

    }
}