//------------------------------------------ Singly Linked List ------------------------------------------
//定义 Definition
public class SinglyListNode {
    int val;
    SinglyListNode next;
    SinglyListNode(int x) { val = x; }
}

//添加 Add
ListNode toAdd = new ListNode(val);
toAdd.next = pred.next;
pred.next = toAdd;

//删除 Del O(N)
pred.next = pred.next.next;

//------------------------------------------ 注意事项 ------------------------------------------
//链表最后一个点，还需要处理
while(headB != null)

//链表最后一个点，不需要处理
while(headB.next != null)

//dummyHead保证prev指针永远不为0

//在最后一个节点停住
while(ptr.next != null){
    ptr = ptr.next;
}
//在最后一个节点之后(null)挺住
while(ptr != null){
    ptr = ptr.next;
}

//------------------------------------------ Two Pointer ------------------------------------------ 
//模板
// Initialize slow & fast pointers
ListNode slow = head;
ListNode fast = head;
/**
 * Change this condition to fit specific problem.
 * Attention: remember to avoid null-pointer error
 **/
while (slow != null && fast != null && fast.next != null) {
    slow = slow.next;           // move slow pointer one step each time
    fast = fast.next.next;      // move fast pointer two steps each time
    if (slow == fast) {         // change this condition to fit specific problem
        return true;
    }
}
return false;   // change return value to fit specific problem

//注意事项
//1.Always examine if the node is null before you call the next field.
//Getting the next node of a null node will cause the null-pointer error. 当使用null节点的下一个节点null.next时，会出现error
//For example, before we run fast = fast.next.next, we need to examine both fast and fast.next is not null.

//2. Carefully define the end conditions of your loop.
//Make sure not result in an endless loop

//3.If there is a cycle, the fast pointer needs M times to catch up the slower pointer, 
//where M is the length of the cycle in the list.(fast走两步，slow走一步)当slow和fast都从环第一个节点开始时

//------------------------------------------ 经典例题 ------------------------------------------ 
//翻转链表
ListNode prev = null;
ListNode curr = head;
while(curr != null){
    ListNode nextTemp = curr.next;
    curr.next = prev;
    prev = curr;
    curr = nextTemp;
}
return prev; //这时curr还没有被翻转

//奇偶-分离为两组链表
ListNode odd = head;
ListNode even = head.next;
ListNode evenHead = head.next;
while(even != null && even.next != null){
    odd.next = even.next;
    odd = odd.next;
    even.next = odd.next;
    even = even.next;
}

//------------------------------------------ Doubly Linked List ------------------------------------------ 
//定义 Definition
class DoublyListNode {
    int val;
    DoublyListNode next, prev;
    DoublyListNode(int x) {val = x;}
}
//添加 Add (succ指向需要添加节点的索引)
ListNode toAdd = new ListNode(val);
toAdd.prev = prev;
toAdd.next = succ;
prev.next = toAdd;
succ.prev = toAdd;

//删除 Del (pred，succ紧挨着；succ指向需要删除的元素)
size--;
pred.next = succ.next;
succ.next.prev = pred;