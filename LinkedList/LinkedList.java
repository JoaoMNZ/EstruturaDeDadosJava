public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addAtHead(T data){
        Node<T> newNode = new Node<>(data);
        newNode.setNext(head);
        head = newNode;
        if(isEmpty()){
            tail = newNode;
        }
        size++;
    }

    public void addAtTail(T data) {
        Node<T> newNode = new Node<>(data);
        if(isEmpty()){
            head = newNode;
        }
        else{
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    public void addAtIndex(int index, T data){
        if(index < 0 || index > size){
            return;
        }
        if(index == 0){
            addAtHead(data);
            return;
        }
        Node<T> newNode = new Node<>(data);
        Node<T> currentNode = head;
        for(int i = 1 ; i < index ; i++){
            currentNode = currentNode.getNext();
        }
        newNode.setNext(currentNode.getNext());
        currentNode.setNext(newNode);
        if(index == size){
            tail = newNode;
        }
        size++;
    }

    public T removeAtHead(){
        if(isEmpty()){
            return null;
        }
        Node<T> currentNode = head;
        head = head.getNext();
        T currentNodeData = currentNode.getData();
        currentNode.setNext(null);
        currentNode.setData(null);
        size--;
        if(isEmpty()){
            tail = null;
        }
        return currentNodeData;
    }

    public T removeAtTail(){
        if(isEmpty()){
            return null;
        }
        Node<T> currentNode = tail;
        T currentNodeData = currentNode.getData();
        tail = tail.getNext();
        currentNode.setNext(null);
        currentNode.setData(null);
        size--;
        if(isEmpty()){
            head = null;
        }
        return currentNodeData;
    }

    public T removeAtIndex(int index){
        if(index < 0 || index >= size){
            return null;
        }
        Node<T> currentNode = head;
        Node<T> previousNode = null;
        for(int i = 0 ; i < index ; i++){
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
        if(previousNode == null){
            return removeAtHead();
        }
        T currentNodeData = currentNode.getData();
        currentNode.setData(null);
        previousNode.setNext(currentNode.getNext());
        currentNode.setNext(null);
        size--;
        if(index == size) {
            tail = previousNode;
        }
        return currentNodeData;
    }

    public T getAtHead(){
        if(isEmpty()){
            return null;
        }
        return head.getData();
    }

    public T getAtTail(){
        if(isEmpty()){
            return null;
        }
        return tail.getData();
    }

    public T getAtIndex(int index){
        if(index < 0 || index >= size){
            return null;
        }
        Node<T> currentNode = head;
        for(int i = 0 ; i < index ; i++){
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }

    public void clear(){
        while(head != null){
            Node<T> nextNode = head.getNext();
            head.setData(null);
            head.setNext(null);
            head = nextNode;
        }
        tail = null;
        size = 0;
    }

    @Override
    public String toString(){
        if(isEmpty()){
            return "[]";
        }
        StringBuilder listStringBuilder = new StringBuilder("[");
        Node<T> tempNode;
        for(tempNode = head ; tempNode.getNext() != null ; tempNode = tempNode.getNext()){
            listStringBuilder.append(tempNode.getData()).append(", ");
        }
        listStringBuilder.append(tempNode.getData()).append("]");
        return listStringBuilder.toString();
    }
}
