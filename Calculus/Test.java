
public class Test {

	public static void main(String[] args) {
		Expression test = new Expression("Derive(x^2 + x/4 * x, x)");
		System.out.println(test.getExpressionType());
		System.out.println(test.getArgument());
		System.out.println(test.getVariable());
	}

}
