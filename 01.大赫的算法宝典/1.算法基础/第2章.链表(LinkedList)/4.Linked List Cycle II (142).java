//142.Linked List Cycle II
//方法一:Set（HashSet）
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> nodeSeen = new HashSet<>();
        while(head != null){
            if(nodeSeen.contains(head)){
                return head;
            }
            else{
                nodeSeen.add(head);
            }
            head = head.next;
        }
        return null;
    }
}


//方法二：双指针
public class Solution {
    //a:环外路程
    //b:在环中，slow走过b后，与fast相遇
    //c:环中的总距离-b
    //n:fast在环中走过了n圈
    //target:环中的第一个点，即为所求答案
    //fast走过的路程：a + n(b+c) + b = a + (n+1)b + nc
    //因为总有，fast走过的路程是slow的两倍。所以：a + (n+1)b + nc = a (a+b) -> a = (n-1)(b+c) + c
    //a到target的路程 = fast到target的路程
    public ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }
        //1.快慢指针
        ListNode slow = head;
        ListNode fast = head;
        ListNode ptr = head;
        
        //2.寻找环
        while(true){
            //如果没有环
            if(fast == null || fast.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            
            //如果fast与slow相遇，则跳出循环
            if (fast == slow){
                break;
            }
        }
        
        //3.此时slow和fast相遇且相等
        while(ptr != fast){
            ptr = ptr.next;
            fast = fast.next;
        }
        return ptr;
    }
}