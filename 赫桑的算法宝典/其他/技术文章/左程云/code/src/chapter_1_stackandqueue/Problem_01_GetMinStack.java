package chapter_1_stackandqueue;

import java.util.Stack;

public class Problem_01_GetMinStack {
	public static class MinStack {
			private final Stack<Integer> data;
			private final Stack<Integer> minArr;

			public MinStack() {
				data = new Stack<>();
				minArr = new Stack<>();
			}

			void push(int newNum) {
				if (minArr.isEmpty()) {
					minArr.push(newNum);
				} else if (newNum <= getMin()) {
					minArr.push(newNum);
				}
				data.push(newNum);
			}

			void pop() {
				if (data.isEmpty()) {
					return ;
				}
				int value = data.pop();
				if (value == getMin()) {
					minArr.pop();
				}
			}
			int top(){
				return data.peek();
			}

			int getMin() {
				if (minArr.isEmpty()) {
					return -1;
				}
				return minArr.peek();
			}
		}

	public static class MyStack2 {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public MyStack2() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}

		public void push(int newNum) {
			if (this.stackMin.isEmpty()) {
				this.stackMin.push(newNum);
			} else if (newNum < this.getmin()) {
				this.stackMin.push(newNum);
			} else {
				int newMin = this.stackMin.peek();
				this.stackMin.push(newMin);
			}
			this.stackData.push(newNum);
		}

		public int pop() {
			if (this.stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			this.stackMin.pop();
			return this.stackData.pop();
		}

		public int getmin() {
			if (this.stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return this.stackMin.peek();
		}
	}

	public static void main(String[] args) {
		MinStack stack1 = new MinStack();
		stack1.push(3);
		System.out.println(stack1.getMin());
		stack1.push(4);
		System.out.println(stack1.getMin());
		stack1.push(1);
		System.out.println(stack1.getMin());
		//System.out.println(stack1.pop());
		System.out.println(stack1.getMin());

		System.out.println("=============");

		MinStack stack2 = new MinStack();
		stack2.push(3);
		System.out.println(stack2.getMin());
		stack2.push(4);
		System.out.println(stack2.getMin());
		stack2.push(1);
		System.out.println(stack2.getMin());
		//System.out.println(stack2.pop());
		System.out.println(stack2.getMin());
	}

}
