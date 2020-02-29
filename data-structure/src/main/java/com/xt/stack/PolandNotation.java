package com.xt.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 后缀
 */
public class PolandNotation {
    public static void main(String[] args) {

        /**
         * 完成将一个中缀表达式转成后缀表达式的功能
         * 1. 1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 – => 中缀表达式对应的List
         * 2. 因为直接对 str 进行操作不方便，因此先将 1+((2+3)×4)-5 => ArrayList[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
         * 3. 将中缀表达式对应的list => 后缀表达式对应的list
         */
        String expr = "1+((2+3)*4)-5";
        List<String> InfixExpressionList = toInfixExpressionList(expr);
        System.out.println("中缀表达式List = " + InfixExpressionList);

        List<String> suffixExpressionList = parseSuffixExpressionList(InfixExpressionList);
        System.out.println("后缀表达式List = " + suffixExpressionList);

        int result = calculate(suffixExpressionList);
        System.out.printf("%s = %d", expr, result);

//        // 先定义逆波兰表达式 (3+4)*5-6 => 3 4 + 5 * 6 - ,使用空格分割
//        String suffixExpr = "3 4 + 5 * 6 -";
//        /**
//         * 思路：
//         * 1. 先将"3 4 + 5 * 6 -" => 放入到ArrayList中
//         * 2. 将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
//         */
//        List<String> list = Arrays.asList(suffixExpr.split(" "));
//        System.out.println("list = " + list);
//        int res = calculate(list);
//        System.out.println("计算的结果是：" + res);
    }

    /**
     * 完成对逆波兰表达式的运算
     * 1. 从左至右扫描，将3和4压入堆栈；
     * 2. 遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
     * 3. 将5入栈；
     * 4. 接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
     * 5. 将6入栈；
     * 6. 最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     */
    public static int calculate(List<String> list) {
        // 创建栈
        Stack<String> stack = new Stack<>();
        int res;
        for (String s : list) {
            if (s.matches("\\d+")) { // 匹配多位数
                stack.push(s);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                if (s.equals("+")) {
                    res = num1 + num2;
                } else if (s.equals("-")) {
                    res = num1 - num2;
                } else if (s.equals("*")) {
                    res = num1 * num2;
                } else if (s.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("符号参数有误~");
                }
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    // 方法：将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {
        List<String> list = new ArrayList<>(); // 存放中缀表达式对应的内容
        int index = 0; // 指针，用于遍历中缀表达式字符串
        String str; // 对多位数的拼接
        char c; // 每遍历到一个字符就放入到c
        do {
            c = s.charAt(index);
            // 如果c是一个非数字，直接加入list
            if (c < 48 || c > 57) {
                list.add(c + "");
                index++; // index后移
            } else {
                str = "";
                while (index < s.length() && s.charAt(index) >= 48 && s.charAt(index) <= 57) {
                    str += c; // 拼接
                    index++;
                }
                list.add(str);
            }
        } while (index < s.length());
        return list;
    }

    /**
     * 方法：中缀表达式对应List => 后缀表达式对应List
     * ArrayList[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] => ArrayList[1, 2, 3, +, 4, *, +, 5, -]
     * 具体步骤如下:
     * 1. 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     * 2. 从左至右扫描中缀表达式；
     * 3. 遇到操作数时，将其压s2；
     * 4. 遇到运算符时，比较其与s1栈顶运算符的优先级：
     *  4.1 如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     *  4.2 否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     *  4.3 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
     * 5. 遇到括号时：
     *  5.1 如果是左括号“(”，则直接压入s1
     *  5.2 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6. 重复步骤2至5，直到表达式的最右边
     * 7. 将s1中剩余的运算符依次弹出并压入s2
     * 8. 依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     */
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        Stack<String> s1 = new Stack<>(); // 符号栈
        /**
         * 说明：因为s2栈在整个转换过程中，没有pop操作，而且后面还需要逆序输出
         * 因此比较麻烦，这里用List替代
         */
//        Stack<String> s2 = new Stack<>(); // 存储中间结果的栈
        List<String> list = new ArrayList<>(); // 存储中间结果
        // 遍历中缀ls
        for (String item : ls) {
            // 如果是一个数，直接加入list
            if (item.matches("\\d+")) {
                list.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    list.add(s1.pop());
                }
                s1.pop(); // 将"("弹出，消除小括号
            } else {
                // 当item的优先级<=s1栈顶运算符，将s1栈顶的运算符弹出并加入list，再次转到4.1
                while (s1.size() != 0 && Operation.getValue(item) <= Operation.getValue(s1.peek())) {
                    list.add(s1.pop());
                }
                // 还需要将item压入栈
                s1.push(item);
            }
        }
        // 将s1中剩余的运算符依次弹出并加入是s2
        while (s1.size() > 0) {
            list.add(s1.pop());
        }
        return list;
    }
}

// Operation类
class Operation {
    public static final int ADD = 1;
    public static final int SUB = 1;
    public static final int MUL = 2;
    public static final int DIV = 2;
    // 返回运算符对应的优先级
    public static int getValue(String oper) {
        int res = 0;
        switch (oper) {
            case "+" :
                res = ADD;
                break;
            case "-" :
                res = SUB;
                break;
            case "*" :
                res = MUL;
                break;
            case "/" :
                res = DIV;
                break;
            default:
                System.out.println("运算符不存在");
                break;
        }
        return res;
    }
}
