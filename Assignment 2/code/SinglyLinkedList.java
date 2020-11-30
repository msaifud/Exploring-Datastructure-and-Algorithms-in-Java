public class SinglyLinkedList<E>{

  private static class Node<E>{
    private E element;
    private Node<E> next;
    public Node(E e, Node<E> n){
      element = e;
      next = n;
    }
    public E getElement(){
      return element;
    }
    public Node<E> getNext(){
      return next;
    }
    public void setNext(Node<E> n){
      next = n;
    }
  }
  private Node<E> first;
  private Node<E> last;
  int size;
  public SinglyLinkedList(){
    first = null;
    last = null;
    size = 0;
  }

  public int size(){
    return size;
  }

  public boolean isEmpty(){
    return size == 0;
  }

  public E first(){
    if (!isEmpty())
      return first.getElement();
    return null;
  }

  public E last(){
    if (!isEmpty())
      return last.getElement();
    return null;
  }

  public void addFirst(E element){
    first = new Node<E>(element , first);
    if (isEmpty()){
      last = first;
    }
    size++;
  }

  public void addLast(E element){
    Node<E> end = new Node<E>(element , null);
    if (isEmpty()){
      first = end;
    }else
      last.setNext(end);
    last = end;
    size++;
  }

  public E removeFirst(){
    E pass = first.getElement();
    first = first.getNext();
    size --;
    if (size == 0)
      last = null;
    return pass;
  }
}