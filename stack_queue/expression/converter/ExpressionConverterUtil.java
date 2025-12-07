package stack_queue.expression.converter;

public class ExpressionConverterUtil {

     /**
     * Decide if stack operator should be popped before incoming operator.
     *
     * '^' is right-associative → pop only when stack precedence > incoming
     * Others are left-associative → pop when stack precedence >= incoming
     */
    public static boolean shouldPop(char stackOp, char incomingOp) {
        if (stackOp == '(') return false;

        int stackPrec = precedence(stackOp);
        int incomingPrec = precedence(incomingOp);

        if (incomingOp == '^') {
            return stackPrec > incomingPrec;  // strict
        }
        return stackPrec >= incomingPrec;     // normal left-assoc
    }

    /**
     * Precedence mapping.
     */
    public static int precedence(char op) {
        switch (op) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
            default:  return -1;
        }
    }
}
