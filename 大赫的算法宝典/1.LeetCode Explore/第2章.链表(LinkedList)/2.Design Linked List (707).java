//707.Design Linked List
//1.定义ListNode
public class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        this.val = x;
    }
}

class MyLinkedList {
    int size;
    ListNode head; //sentinel哨兵结点

    /** Initialize your data structure here. */
    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);        
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        //边界条件
        if(index < 0 || index >= size){
            return -1;
        }
        
        ListNode cur = head;
        for(int i = 0; i < index + 1; i++){
            cur = cur.next;
        }
        return cur.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        //边界条件
        if(index > size){
            return;
        }
        if(index < 0){
            index = 0;
        }
        
        size++;
        //找到前驱结点
        ListNode pred = head;
        for(int i = 0; i < index; i++){
            pred = pred.next;
        }
        //插入
        ListNode toAdd = new ListNode(val);
        toAdd.next = pred.next;
        pred.next = toAdd;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        //边界条件
        if(index < 0 || index >= size){ //当size=1时，index只能到0. 这时index=1就会超出范围
            return;
        }
        size--;
        //找到前驱结点
        ListNode pred = head;
        for(int i = 0; i < index; i++){
            pred = pred.next;
        }
        //删除节点
        pred.next = pred.next.next;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */