
public class Expression {
	private CalculationType expressionType;
	private String input;
	private String argument;
	private String variable;
	private Parser expressionParser;
	private boolean validExpression;
	
	public Expression(String function) {
		input = removeSpace(function.toLowerCase());
		expressionParser = new Parser(input);
		expressionType = expressionParser.getType();
		variable = expressionParser.getVar();
		argument = expressionParser.getArg();
	}
	
	public String removeSpace(String in) {
		StringBuilder res = new StringBuilder("");
		for(int i = 0; i < in.length(); i++) {
			if(in.charAt(i) != ' ') res.append(in.charAt(i));
		}
		return res.toString();
	}
	
	public boolean isValidExpression() {
		return this.validExpression;
	}
	
	public CalculationType getExpressionType() {
		return this.expressionType;
	}
	
	public String getInput() {
		return this.input;
	}
	
	public String getArgument() {
		return this.argument;
	}
	
	public String getVariable() {
		return this.variable;
	}
	
}
