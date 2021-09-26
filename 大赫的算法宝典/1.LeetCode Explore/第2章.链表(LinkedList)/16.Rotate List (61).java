//16.Rotate List (61)
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        //1.边界情况(没有节点和一个节点)
        if(head == null){
            return null;
        }
        if(head.next == null){
            return head;
        }
        //2.找出n：链表节点的个数
        ListNode oldTail = head;
        int n;
        for(n = 1; oldTail.next != null; n++){
            oldTail = oldTail.next;
        }
        
        //3.将链表连成环
        oldTail.next = head;
        
        //4.寻找rotate完后，新的tail
        //k: rotate的次数
        ListNode newTail = head;
        for(int i = 0; i < n - (k % n) - 1; i++){
             newTail = newTail.next;
        }
        
        //5.找到新的头节点newHead作为返回值
        ListNode newHead = newTail.next;
        
        //6.断开连接
        newTail.next = null;
        
        return newHead;
    }
}