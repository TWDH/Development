//11.Design Doubly Linked List (707)
//定义ListNode
public class ListNode{
    int val;
    ListNode next;
    ListNode prev;
    ListNode(int x){
        val = x;
    }
}

class MyLinkedList {
    //定义长度，哨兵结点(头,尾)
    int size;
    ListNode head, tail;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        //边界情况
        if(index >= size || index < 0){
            return -1;
        }
        
        //选择头尾最快路径找到该节点
        ListNode curr = head;
        if(index + 1 < size - index){
            for(int i = 0; i < index + 1; i++){
                curr = curr.next;
            }
        }
        else{
            curr = tail;
            for(int i = 0; i < size - index; i++){
                curr = curr.prev;
            }
        }
        return curr.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        //设置前驱后继节点
        ListNode prev = head;
        ListNode succ = head.next;
        
        size++;
        
        //添加
        ListNode toAdd = new ListNode(val);
        toAdd.prev = prev;
        toAdd.next = succ;
        prev.next = toAdd;
        succ.prev = toAdd;     
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        //设置前驱后继节点
        ListNode prev = tail.prev;
        ListNode succ = tail;
        
        size++;
        
        //添加
        ListNode toAdd = new ListNode(val);
        toAdd.prev = prev;
        toAdd.next = succ;
        prev.next = toAdd;
        succ.prev = toAdd;  
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        //边界情况
        if(index > size){
            return;
        }
        if(index < 0){
            index = 0;
        }
        
        //找到前驱后继节点
        ListNode pred, succ;
        //正方向：pred，succ在目标上
        if(index < size - index){
            pred = head;
            for(int i = 0; i < index; i++){
                pred = pred.next;
            }
            succ = pred.next;
        }
        //反方向：pred，succ在目标上
        else{
            succ = tail;
            for(int i = 0; i < size - index; i++){
                succ = succ.prev;
            }
            pred = succ.prev;
        }
        
        //添加
        size++;
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        //特殊情况
        if(index < 0 || index >= size){
            return;
        }
        
        //找到前驱后继节点
        
        ListNode pred, succ;
        if(index < size - index){
            pred = head;
            for(int i = 0; i < index; i++){
                pred = pred.next;
            }
            succ = pred.next;
        }
        else{
            succ = tail;
            for(int i = 0; i < size - index; i++){
                succ = succ.prev;
            }
            pred = succ.prev;
        }
        
        //删除
        size--;
        pred.next = succ.next;
        succ.next.prev = pred;
    }
}
