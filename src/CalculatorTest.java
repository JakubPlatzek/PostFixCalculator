import org.junit.After;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private Client client;
    private ArrayList<Token> list;

    @BeforeEach
    void setUp() {
        client = new Client();
        list = new ArrayList<>();
        System.out.println("--> setUp()");
    }
    @AfterEach
    void tearDown() {
        list.clear();
        System.out.println("<-- tearDown()");
    }

    @Test
    void testEvaluateExpressionOne() {
        list.add(new Operand(4));
        list.add(new Operand(7));
        list.add(new Operand(2));
        list.add(new Operator(Operation.PLUS));
        list.add(new Operator(Operation.MULTIPLY));
        int result = client.evaluateExpression(list);
        assertEquals(36, result, "Result");
    }

    @Test
    void testEvaluateExpressionTwo() {
        list.add(new Operand(3));
        list.add(new Operand(4));
        list.add(new Operand(7));
        list.add(new Operator(Operation.MULTIPLY));
        list.add(new Operand(2));
        list.add(new Operator(Operation.DIVIDE));
        list.add(new Operator(Operation.PLUS));
        int result = client.evaluateExpression(list);
        assertEquals(17, result, "Result");
    }

    @Test
    void testEvaluateExpressionThree() {
        list.add(new Operand(4));
        list.add(new Operand(7));
        list.add(new Operator(Operation.MULTIPLY));
        list.add(new Operand(20));
        list.add(new Operator(Operation.MINUS));
        int result = client.evaluateExpression(list);
        assertEquals(8, result, "Result");
    }

    @Test
    void testEvaluateExpressionFour() {
        list.add(new Operand(4));
        list.add(new Operand(7));
        list.add(new Operator(Operation.MULTIPLY));
        list.add(new Operand(2));
        list.add(new Operator(Operation.DIVIDE));
        int result = client.evaluateExpression(list);
        assertEquals(14, result, "Result");
    }

    @Test
    void testEvaluateExpressionFive() {
        list.add(new Operand(7));
        list.add(new Operand(4));
        list.add(new Operand(-3));
        list.add(new Operator(Operation.MULTIPLY));
        list.add(new Operand(1));
        list.add(new Operand(5));
        list.add(new Operator(Operation.PLUS));
        list.add(new Operator(Operation.DIVIDE));
        list.add(new Operator(Operation.MULTIPLY));
        int result = client.evaluateExpression(list);
        assertEquals(-14, result, "Result");
    }
}
