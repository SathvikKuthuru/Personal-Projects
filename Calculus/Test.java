
public class Test {

    public static void main(String[] args) {
        Expression test = new Expression("((x+4)++(x+5), x)", CalculationType.DERIVATIVE);
        System.out.println(test.getExpressionType());
        System.out.println(test.getArgument());
        System.out.println(test.getVariable());
        SyntaxChecker syntaxChecker = new SyntaxChecker(test);
        System.out.println(syntaxChecker.checkTotalValidity().getMessage());
    }

}
