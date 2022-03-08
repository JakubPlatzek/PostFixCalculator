import org.junit.After;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest<T> {
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
    void testEvaluateExpressionOne() throws MalformedException
    {
        list.add(new Operand(4));
        list.add(new Operand(7));
        list.add(new Operand(2));
        list.add(new Operator(Operation.PLUS));
        list.add(new Operator(Operation.MULTIPLY));
        int result = client.evaluateExpression(list);
        assertEquals(36, result, "Result");
    }

    @Test
    void testEvaluateExpressionTwo() throws MalformedException
    {
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
    void testEvaluateExpressionThree() throws MalformedException
    {
        list.add(new Operand(4));
        list.add(new Operand(7));
        list.add(new Operator(Operation.MULTIPLY));
        list.add(new Operand(20));
        list.add(new Operator(Operation.MINUS));
        int result = client.evaluateExpression(list);
        assertEquals(8, result, "Result");
    }

    @Test
    void testEvaluateExpressionFour() throws MalformedException
    {
        list.add(new Operand(4));
        list.add(new Operand(7));
        list.add(new Operator(Operation.MULTIPLY));
        list.add(new Operand(2));
        list.add(new Operator(Operation.DIVIDE));
        int result = client.evaluateExpression(list);
        assertEquals(14, result, "Result");
    }

    @Test
    void testEvaluateExpressionFive() throws MalformedException
    {
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

    @Test
    void testExpectedExceptionMalformed(){
    Assertions.assertThrows(MalformedException.class, () ->{
    client.evaluateExpression(list);
    });
    }

    @Test
    void testExpectedExceptionEmptyList(){
        Assertions.assertThrows(EmptyListException.class, () ->{
            LinkedList linkedList = new LinkedList();
            linkedList.removeFirst();
        });
    }

    @Test
    void testExpectedExceptionEmptyStack(){
        Assertions.assertThrows(EmptyStackException.class, () ->{
            LinkedStack linkedstack = new LinkedStack();
            linkedstack.pop();
        });
    }

    @Test
    void testLinkedStackOne(){
            LinkedStack linkedstack = new LinkedStack();
            linkedstack.push(new Node<>(new Operand(1)));
            linkedstack.push(new Node<>(new Operand(2)));
            Node<Operand> result = (Node<Operand>) linkedstack.pop();
            assertEquals(2, result.getData().getValue());
    }

    @Test
    void testLinkedStackIsEmptyTrue(){
        LinkedStack linkedstack = new LinkedStack();
        boolean result = linkedstack.isEmpty();
        assertEquals(true, result);
    }

    @Test
    void testLinkedStackIsEmptyFalse(){
        LinkedStack linkedstack = new LinkedStack();
        linkedstack.push(new Node<>(new Operand(2)));
        boolean result = linkedstack.isEmpty();
        assertEquals(false, result);
    }

    @Test
    void testLinkedListOne() throws EmptyListException
    {
        LinkedList linkedList = new LinkedList();
        linkedList.addToFront(new Node<>(new Operand(1)));
        linkedList.addToFront(new Node<>(new Operand(2)));
        Node<Operand> result = (Node<Operand>) linkedList.removeFirst();
        assertEquals(2, result.getData().getValue());
    }

    @Test
    void testLinkedListSizeOne() throws EmptyListException
    {
        LinkedList linkedList = new LinkedList();
        linkedList.addToFront(new Node<>(new Operand(1)));
        linkedList.addToFront(new Node<>(new Operand(2)));
        int result = linkedList.size();
        assertEquals(2, result);
    }

    @Test
    void testLinkedListSizeTwo() throws EmptyListException
    {
        LinkedList linkedList = new LinkedList();
        int result = linkedList.size();
        assertEquals(0, result);
    }

    @Test
    void testLinkedListSizeMany() throws EmptyListException
    {
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < 999; i++){
            linkedList.addToFront(new Node<>(new Operand(2)));
        }
        int result = linkedList.size();
        assertEquals(999, result);
    }

    @Test
    void testLinkedListIsEmptyTrue(){
        LinkedList linkedList = new LinkedList();
        boolean result = linkedList.isEmpty();
        assertEquals(true, result);
    }

    @Test
    void testLinkedListIsEmptyFalse(){
        LinkedList linkedList = new LinkedList();
        linkedList.addToFront(new Node<>(new Operand(1)));
        boolean result = linkedList.isEmpty();
        assertEquals(false, result);
    }
}
