//12.Merge Two Sorted Lists (21)
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //1.创建dummy
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        //2.取值小者插入新建的链表
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                curr.next = l1;
                l1 = l1.next;
            }
            else{
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        //3.当l1或l2中某一个为空，将另一个链表直接接在后面
        if(l1 == null){
            curr.next = l2;
        }
        else{
            curr.next = l1;
        }
        return dummy.next;
    }
}