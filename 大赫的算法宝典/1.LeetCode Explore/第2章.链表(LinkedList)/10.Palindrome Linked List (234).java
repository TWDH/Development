//10.Palindrome Linked List (234)
class Solution {
    public boolean isPalindrome(ListNode head) {
        //1.设置快慢指针，使slow正好停在链表中间
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //2.偶数：fast指向null；奇数：fast指向最后一个，将slow后移。
        if(fast != null){
            slow = slow.next;
        }
        
        //3.翻转后半部分链表
        slow = reverse(slow);
        fast = head;
        
        //4.前后链表一一对比
        //偶数：左右链表数量相等；奇数：左链表多一个，当slow=null时证明除了这一个其他元素都相等，那么一定是回文。
        while(slow != null){
            if(fast.val != slow.val){
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }
    
    //翻转链表函数
    public ListNode reverse(ListNode curr){
        ListNode prev = null;
        while(curr != null){
            ListNode tempNext = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNext;
        }
        return prev;
    }
}