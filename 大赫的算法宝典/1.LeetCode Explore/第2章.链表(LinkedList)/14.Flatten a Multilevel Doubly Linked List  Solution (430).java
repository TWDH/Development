//14.Flatten a Multilevel Doubly Linked List  Solution (430)
//方法一：链表操作
class Solution {
    public Node flatten(Node head) {
        //1.特殊情况
        if(head == null){
            return null;
        }
        //2.初始化指针p
        Node p = head;
        while(p != null){
            //3.当有child节点时
            if(p.child != null){
                //3.1存储下一个节点next
                Node next = p.next;
                //3.2存储child节点，一会连接就没有了
                Node child = p.child;
                //3.3双向连接
                p.next = child;
                p.child = null;
                child.prev = p;
                //3.4继续遍历child所在“层”
                while(child.next != null){
                    child = child.next;
                }
                //3.5child所在层的末尾，连回上一层
                child.next = next;
                //3.5.1保证最后一个不是null向左连接
                if(next != null){
                    next.prev = child;
                }
            }
            p = p.next;
        }
        return head;
    }
}
//方法二：DFS(递归+迭代)