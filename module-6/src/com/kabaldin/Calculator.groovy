package com.kabaldin

class Calculator {
    static void main(String[] args) {
        String expression = "2+2*(5-7)"
        int result = calculate(expression)
        println("Result: ${result}")
    }

    static int calculate(String expression) {
        Deque<Integer> numberStack = new ArrayDeque<>()
        Deque<Character> operatorStack = new ArrayDeque<>()

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i)

            if (Character.isDigit(ch)) {
                int num = ch - ('0' as Character)
                while (i + 1 < expression.length() && Character.isDigit(expression.charAt(i + 1))) {
                    num = num * 10 + (expression.charAt(i + 1) - ('0' as Character))
                    i++
                }
                numberStack.push(num)
            } else if (ch == '(') {
                operatorStack.push(ch)
            } else if (ch == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    performOperation(numberStack, operatorStack)
                }
                operatorStack.pop()
            } else if (isOperator(ch)) {
                while (!operatorStack.isEmpty() && hasHigherPriority(operatorStack.peek(), ch)) {
                    performOperation(numberStack, operatorStack)
                }
                operatorStack.push(ch)
            }
        }

        while (!operatorStack.isEmpty()) {
            performOperation(numberStack, operatorStack)
        }

        return numberStack.pop()
    }

    static void performOperation(Deque<Integer> numberStack, Deque<Character> operatorStack) {
        int operand2 = numberStack.pop()
        int operand1 = numberStack.pop()
        char operator = operatorStack.pop()
        int result = applyOperator(operand1, operand2, operator)
        numberStack.push(result)
    }

    static int applyOperator(int operand1, int operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2
            case '-':
                return operand1 - operand2
            case '*':
                return operand1 * operand2
            case '/':
                return operand1 / operand2
            case '%':
                return operand1 % operand2
            default:
                throw new IllegalArgumentException("Invalid operator: ${operator}")
        }
    }

    static def isOperator = { operator ->
        return operator == '+' || operator == '-' || operator == '*' || operator == '/' || operator == '%'
    }

    static def hasHigherPriority = { operator1, operator2 ->
        if ((operator1 in ['*', '/']) && (operator2 in ['+', '-'])) {
            return true
        }
        return false
    }
}

