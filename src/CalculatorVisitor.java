import java.util.EmptyStackException;

public class CalculatorVisitor implements Calculator, Visitor{
    private final LinkedStack<Token> tokenStack;

    public CalculatorVisitor() {
        tokenStack = new LinkedStack<>();
    }

    private void pushOperand(Operand operand){
    tokenStack.push(operand);
    }

    private void performOperation(Operator operator) {
        Operand left = (Operand) tokenStack.pop();
        Operand right = (Operand) tokenStack.pop();

        switch (operator.getOperation()) {
            case PLUS -> tokenStack.push(new Operand(right.getValue() + left.getValue()));
            case MINUS -> {
                // Convert negative value to its positive value
                if (left.getValue() < 0) {
                    left.setValue(left.getValue() + left.getValue() * 2);
                }
                if (right.getValue() < 0) {
                    right.setValue(right.getValue() + right.getValue() * 2);
                }
                tokenStack.push(new Operand(right.getValue() - left.getValue()));
            }
            case MULTIPLY -> tokenStack.push(new Operand(right.getValue() * left.getValue()));
            case DIVIDE -> tokenStack.push(new Operand(right.getValue() / left.getValue()));
        }
    }

    @Override
    public int getResult() throws MalformedException{
        try{
            Token token = tokenStack.pop();
            if (token instanceof Operand op) {
                return op.getValue();
            } else {
                return Integer.MIN_VALUE;
            }
        }
        catch(EmptyStackException e){
            throw new MalformedException();
        }
    }

    @Override
    public void visit(Operand operand) {
        pushOperand(operand);
    }

    @Override
    public void visit(Operator operator) {
        performOperation(operator);
    }
}
