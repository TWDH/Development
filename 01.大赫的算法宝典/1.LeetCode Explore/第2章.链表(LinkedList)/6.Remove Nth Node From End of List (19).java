//6.Remove Nth Node From End of List(19)
//方法一：双指针
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //1.特殊情况
        if(head == null){
            return null;
        }
        //2.dummyHead
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //2.fast先走一步
        ListNode slow = dummy;
        ListNode fast = dummy;
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}