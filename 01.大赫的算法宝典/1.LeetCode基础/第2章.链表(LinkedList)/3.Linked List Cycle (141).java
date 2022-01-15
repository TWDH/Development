//141.Linked List Cycle
//方法一：双指针
public class Solution {
    public boolean hasCycle(ListNode head) {
        //1.特殊情况
        if(head == null){
            return false;
        }
        //2.双指针，slow每次走一步，fast每次走两步。直到slow.next变为null,代表走到底。
        //保证fast的两步内都不为null
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow.next != null && fast.next !=null && fast.next.next != null){//因为没有判断是否为null，所以会有NullPointerException
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }
}
//方法二：官方解法（双指针）
public class Solution {
    public boolean hasCycle(ListNode head) {
        //1.特殊情况
        if(head == null){
            return false;
        }
        //2.双指针，slow每次走一步，fast每次走两步。直到slow.next变为null,代表走到底。
        //保证fast的两步内都不为null
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(fast == null || fast.next == null){ //如果判断是否为null则不会有NullPointerException
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}