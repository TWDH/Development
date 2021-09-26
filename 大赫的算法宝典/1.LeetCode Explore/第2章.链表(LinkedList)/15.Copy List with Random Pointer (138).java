//15.Copy List with Random Pointer (138)

class Solution {
    public Node copyRandomList(Node head) {
        //1.特殊情况
        if(head == null){
            return null;
        }
        //2.复制链表：原链表每个元素后面都复制一个相同的元素
        copy(head);
        //3.复制random，将原链表的random关系重新复制到新元素中
        randomDirect(head);
        //4.返回新复制的链表
        return reList(head);
    }
    
    //复制链表
    public void copy(Node head){
        while(head != null){
            //存储下一个节点
            Node nextNode = head.next;
            //创建和head一样值的cloneNode
            Node cloneNode = new Node(head.val);
            //将cloneNode接入
            head.next = cloneNode;
            cloneNode.next = nextNode;
            //更新head指针坐标
            head = cloneNode.next;
        }
    }
    
    //复制random关系
    public void randomDirect(Node head){
        while(head != null){
            //指定cloneNode位置
            Node cloneNode = head.next;
            //将原链表中的random关系复制到cloneNode链表中
            if(head.random != null){
                Node direct = head.random;
                cloneNode.random = direct.next;
            }
            //更新head
            head = cloneNode.next;
        }
    }
    
    //返回新的链表
    public Node reList(Node head){
        //定义cloneNode和头节点cloneHead
        Node cloneNode = head.next;
        Node cloneHead = cloneNode;
        //更改head的next链接指向
        head.next = cloneNode.next;
        //更新head位置
        head = head.next;
        while(head != null){
            //更新cloneNode路线
            cloneNode.next = head.next;
            cloneNode = cloneNode.next;
            //更新head路线
            head.next = head.next.next;
            head = head.next;
        }
        return cloneHead;
    }
    
}