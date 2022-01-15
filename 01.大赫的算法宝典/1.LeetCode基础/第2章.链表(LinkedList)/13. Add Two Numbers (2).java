//13. Add Two Numbers (2)
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //1.创建dummy头节点
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        
        //2.遍历两链表
        int carry = 0;
        while(ptr1 != null || ptr2 != null){
            //x,y存储两链表中的值，不存在则为0
            int x = (ptr1 != null) ? ptr1.val : 0;
            int y = (ptr2 != null) ? ptr2.val : 0;
            //计算每一位的总和
            int sum = x + y + carry;
            //计算这一位的“进位”
            carry = sum / 10;
            //将此位应得的数放入LinkedList，只要个位
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            //ptr最后一定是null才能结束循环
            if(ptr1 != null){
                ptr1 = ptr1.next;
            }
            if(ptr2 != null){
                ptr2 = ptr2.next;
            }
        }
        //3.如果最高位还有进位
        if(carry > 0){
            curr.next = new ListNode(carry);
        }
        return dummy.next;
    }
}