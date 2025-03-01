// This HashMap avoids null keys.

package HashMap;

public class HashMap<K,V> {
    private int capacity;
    private final float loadFactor;
    private LinkedList<K,V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        this.capacity = 16;
        this.loadFactor = 0.75F;
        this.table = (LinkedList<K, V>[]) new LinkedList[capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.loadFactor = 0.75F;
        this.table = (LinkedList<K, V>[]) new LinkedList[capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity, float loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.table = (LinkedList<K, V>[]) new LinkedList[capacity];
        this.size = 0;
    }

    private int hashing(K key){
        int hash = key.hashCode();
        return (hash & 0x7FFFFFFF) % capacity;
    }

    private void putAtHead(K key, V value){
        int hash = hashing(key);
        if(table[hash] == null) {
            table[hash] = new LinkedList<K,V>();
        }
        table[hash].putAtHead(key,value);
    }

    public V put(K key, V value){
        if(key == null) return null;
        int hash = hashing(key);
        if(table[hash] == null) {
            table[hash] = new LinkedList<K,V>();
        }
        int oldSize = table[hash].getSize();
        V oldValue = table[hash].put(key,value);
        if(oldSize != table[hash].getSize()) size++;
        this.resize();
        return oldValue;
    }

    public V remove(K key){
        if(key == null) return null;
        int hash = hashing(key);
        if(table[hash] == null) {
            return null;
        }
        V value = table[hash].remove(key);
        if(value != null) size--;
        if(table[hash].isEmpty()) table[hash] = null;
        return value;
    }

    public V get(K key){
        if(key == null) return null;
        int hash = hashing(key);
        if(table[hash] == null) return null;
        return table[hash].get(key);
    }

    @SuppressWarnings("unchecked")
    private void resize(){
        if(size > capacity * loadFactor){
            capacity *= 2;
            LinkedList<K,V>[] oldTable = table;
            table = (LinkedList<K, V>[]) new LinkedList[capacity];
            for (LinkedList<K, V> list : oldTable) {
                if (list == null) {
                    continue;
                }
                Node<K, V> currentNode = list.getHead();
                while (currentNode != null) {
                    this.putAtHead(currentNode.getKey(), currentNode.getValue());
                    currentNode = currentNode.getNext();
                }
            }
        }
    }
}
