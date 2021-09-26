//5.Intersection of Two Linked Lists(160)
//方法一：双指针
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //1.特殊情况
        if(headA == null || headB == null){
            return null;
        }
        //2.指针PA，PB指向两个链表。到末尾就指向另一联表的头部。
        //这样在相遇前的部分(node数量差)就可以抵消
        ListNode PA = headA;
        ListNode PB = headB;
        while(PA != PB){
            if(PA == null){ //如果PA.next==null，则PA，PB永远不会有null(如果为No intersection的情况)。普通情况不影响
                PA = headB;
            }
            else{
                PA = PA.next;
            }
            
            if(PB == null){
                PB = headA;
            }
            else{
                PB = PB.next;
            }
        }
        return PA;
    }
}

//方法二：HashSet
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //创建hashset
        Set<ListNode> set = new HashSet<>();
        while(headA != null){
            set.add(headA);
            headA = headA.next;
        }
        while(headB != null){
            if(set.contains(headB)){
                return headB;
            }
            else{
                headB = headB.next;
            }
        }
        return null;
    }
}