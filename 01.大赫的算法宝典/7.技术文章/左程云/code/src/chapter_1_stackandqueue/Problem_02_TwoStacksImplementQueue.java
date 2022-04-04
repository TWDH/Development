package chapter_1_stackandqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Problem_02_TwoStacksImplementQueue {

	public static class MyQueue {
		Deque<Integer> left;
		Deque<Integer> right;
		public MyQueue() {
			left = new ArrayDeque<>();
			right = new ArrayDeque<>();
		}

		public void push(int x) {
			right.push(x);
		}
		public void selfUpdate(){
			if(left.isEmpty()){
				while(!right.isEmpty()){
					left.push(right.pop());
				}
			}
		}

		public int pop() {
			selfUpdate();
			return left.pop();
		}

		public int peek() {
			selfUpdate();
			return left.peek();
		}

		public boolean empty() {
			return left.isEmpty() && right.isEmpty();
		}
	}


	public static void main(String[] args) {
		MyQueue test = new MyQueue();
		test.push(1);
		test.push(2);
		test.push(3);
		System.out.println(test.peek());
		System.out.println(test.pop());
		System.out.println(test.peek());
		System.out.println(test.pop());
		System.out.println(test.peek());
		System.out.println(test.pop());
	}

}
