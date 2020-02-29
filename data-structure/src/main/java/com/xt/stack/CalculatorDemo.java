package com.xt.stack;

/**
 * 栈实现综合计算器
 */
public class CalculatorDemo {
    public static void main(String[] args) {
        String expr = "7*2*2-5+1-5+3-4"; // 如何处理多位数的问题？？？
        // 创建两个栈：一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 定义相关的变量
        int index = 0; // 用于扫面
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; // 将每次扫描得到char保存到ch
        String keepNum = ""; // 用于拼接多位数
        // 开始while循环的扫描expr
        while (true) {
            // 一次得到 expr 的每一个字符
            ch = expr.substring(index, index + 1).charAt(0);
            // 判断 ch 是什么，然后做相应的处理
            if (operStack.isOper(ch)) { // 如果是运算符
                // 判断当前符号栈是否为空
                if (operStack.isEmpty()) { // 如果符号栈为空直接入栈
                    operStack.push(ch);
                } else {
                    /**
                     * 如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或等于栈中的操作符，
                     * 就需要从数栈中pop出两个数，再从符号栈中pop出一个符号，进行运算，将得到的结果入数栈，
                     * 然后将当前的操作符入符号栈
                     */
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 把运算结果入数栈
                        numStack.push(res);
                        // 将当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        // 如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }
            } else { // 如果是数，直接入数栈
                /**
                 * 分析思路：
                 * 1. 当处理多为数时，不能发现是一个数就立即入栈，因为它可能是多位数
                 * 2.在处理数，需要向expr的表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                 * 3. 因此需要定义一个字符串变量，用于拼接
                 */
                // 处理多位数
                keepNum += ch;

                // 如果ch已经是expr的最后以为，就直接入栈
                if (index == expr.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    // 判断下一个字符是不是数字，如果是，就继续扫描，如果是运算符，则入栈
                    if (operStack.isOper(expr.substring(index+1, index+2).charAt(0))) {
                        // 如果后一位是运算符，则入栈 keepNum = "1" 或者 "123"
                        numStack.push(Integer.parseInt(keepNum));
                        // 记得 keepNum 清空
                        keepNum = "";
                    }
                }
            }
            // 让 index+1, 并判断是否扫描到expr末尾
            index++;
            if (index >= expr.length()) {
                break;
            }
        }

        // 当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号数，并运算
        while (true) {
            // 如果符号栈为空，则计算到最后的结果，数栈中只有一个数字[结果]
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int result = numStack.pop();
        System.out.printf("表达式 %s = %d：", expr, result);
    }
}

// 数组模拟栈, 扩展功能
class ArrayStack2 {
    private int maxSize; // 栈的大小
    private int[] array; // 数组模拟栈，存放数据
    private int top = -1; // 栈顶，初始化-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    // 入栈
    public void push(int value) {
        // 先判断栈是否满
        if (isFull()) {
            System.out.println("栈已满，不能添加数据");
            return;
        }
        top++;
        array[top] = value;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空，没有数据");
        }
        int value = array[top];
        top--;
        return value;
    }

    // 显示
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空, 没有数据~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("array[%d]=%d\n", i, array[i]);
        }
    }

    // 栈顶
    public int peek() {
        if (isEmpty()) {
            System.out.println("栈空, 没有数据");
        }
        return array[top];
    }

    // 栈满
    public boolean isFull () {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 返回运算符的优先级，优先级使用数字表示，数字越大，则优先级越高
     * 假定目前的表达式只有 +、-、*、/
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; // res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; // 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}