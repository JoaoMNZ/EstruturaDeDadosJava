package HashMap;

public class LinkedList<K,V> {
    private Node<K,V> head;
    private int size;

    public LinkedList() {
        this.head = null;
    }

    public Node<K, V> getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void putAtHead(K key, V value){
        Node<K,V> newNode = new Node<>(key,value);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    public V put(K key, V value) {
        if(head == null){
            head = new Node<>(key,value);
            size++;
            return null;
        }
        Node<K,V> previousNode = null;
        Node<K,V> currentNode = head;
        while(currentNode != null){
            if(currentNode.getKey().equals(key)){
                V oldValue = currentNode.getValue();
                currentNode.setValue(value);
                return oldValue;
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        previousNode.setNext(new Node<>(key,value));
        size++;
        return null;
    }

    public V remove(K key){
        Node<K,V> previousNode = null;
        Node<K,V> currentNode = head;
        while(currentNode != null){
            if(currentNode.getKey().equals(key)){
                if(previousNode == null){
                    head = currentNode.getNext();
                }else{
                    previousNode.setNext(currentNode.getNext());
                }
                size--;
                return currentNode.getValue();
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public V get(K key){
        Node<K,V> currentNode = head;
        while(currentNode != null){
            if(currentNode.getKey().equals(key)){
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }
}
