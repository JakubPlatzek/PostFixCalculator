import java.util.ArrayList;

public class Client {
    private final CalculatorVisitor visitor = new CalculatorVisitor();

    public int evaluateExpression(ArrayList<Token> tokenList) {
        for (Token t : tokenList) {
            t.accept(visitor);
        }
        return visitor.getResult();
    }
}
