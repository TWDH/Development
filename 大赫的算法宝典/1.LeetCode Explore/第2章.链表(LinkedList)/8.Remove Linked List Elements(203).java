//8.Remove Linked List Elements(203)
//方法一：经典例题
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = head;
        ListNode prev = dummy;
        while(curr != null){
            if(curr.val == val){
                prev.next = curr.next; //prev.next = prev.next.next;
            }
            else{
                prev = prev.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}