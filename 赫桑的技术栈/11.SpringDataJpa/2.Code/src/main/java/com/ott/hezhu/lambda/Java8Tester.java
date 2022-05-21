package com.ott.hezhu.lambda;

/**
 * @author He Zhu
 * @date 2021-08-16 3:44 p.m.
 */
public class Java8Tester {
    // 函数式场景实例化， 重写方法吗
    MathOperation addition = (int a, int b) -> a + b;

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {

        Java8Tester java8Tester = new Java8Tester();

        int operate = java8Tester.operate(10, 5, java8Tester.addition);

        System.out.println(operate);
    }
}
