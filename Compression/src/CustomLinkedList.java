
public class CustomLinkedList {
	
	private class Node {
        private Dictionary data;
        private Node next;
        
        public Node(Dictionary a, Node n) {
            data = a;
            next = n;
        }
    }
    
    private Node first;
    private Node curr=first;
    private Node tail=null;
    private int length=this.size();
    
    public CustomLinkedList() {
        first = null;
        length = 0;
    }
    
    public boolean isEmpty() {
        return (first == null);
    }
    
    public void addFirst(Dictionary d) {       
        first = new Node(d,first);
        length++;
    }
    
    public int size() {
     int count = 0;
     for (Node curr = first; curr != null; curr = curr.next)
     count++;
     length=count;
     return count;
    }
    
    public short contains(String value) {
        for (Node curr = first; curr != null; curr = curr.next) {
            if (value.equals(curr.data.getCode())) {
                return curr.data.getKey();
            }
        }
        return -1;
    }
    public String contains(short value) {
        for (Node curr = first; curr != null; curr = curr.next) {
            if (value == curr.data.getKey()) {
                return curr.data.getCode();
            }
        }
        return "-1";
    }
    public Dictionary get(int index) {
        if (index < 0 || index >= length) {
            System.out.println("Index of " + index + " out of range");
            return null;
        }
        
        Node curr = first;
        for (int i = 0; i < index; i++)
            curr = curr.next;
        return curr.data;
    }
    
    public void add(Dictionary value) {
    	
    	if(curr!=null)
        {
            Node temp = new Node(value, curr.next);
            curr.next = temp;
            curr = temp;
        }
        else
        {
            first = tail = new Node(value, null);
            curr = first;
        }
    }
    
    
}
