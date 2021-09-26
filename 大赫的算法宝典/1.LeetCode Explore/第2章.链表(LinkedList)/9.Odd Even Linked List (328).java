//9.Odd Even Linked List (328)
class Solution {
    public ListNode oddEvenList(ListNode head) {
        //1.特殊情况
        if(head == null){
            return null;
        }
        //2.设置odd，even指针
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = head.next;
        //3.even,odd交替，并把奇偶分为两个不同的链表
        while(even != null && even.next != null){ //都保证了even后面跟的是null；even.next != null保证odd最后一个不应该为null，因为需要跟even链接。even总领先一步
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}