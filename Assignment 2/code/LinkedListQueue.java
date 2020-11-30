public class LinkedListQueue<E> implements Queue<E>{
  private SinglyLinkedList<E> queue;
  public LinkedListQueue(){
    queue = new SinglyLinkedList<E>();
  }
  public int size(){
    return queue.size();
  }
  public boolean isEmpty(){
    return queue.isEmpty();
  }
  public E first(){
    return queue.first();
  }
  public void enqueue(E node){
    queue.addLast(node);
  }
  public E dequeue(){ return queue.removeFirst(); }
}
