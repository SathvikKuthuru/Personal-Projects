
public class Expression {
    private CalculationType expressionType;
    private String input;
    private String argument;
    private String variable;
    private Parser expressionParser;

    public Expression(String function) {
        input = removeSpace(function.toLowerCase());
        expressionParser = new Parser(input);
        expressionType = expressionParser.getType();
        variable = expressionParser.getVar();
        argument = expressionParser.getArg();
        this.fixArgument();
    }

    public Expression(String function, CalculationType calculationType) {
        input = removeSpace(function.toLowerCase());
        expressionParser = new Parser(input);
        expressionType = calculationType;
        variable = expressionParser.getVar();
        argument = expressionParser.getArg();
        this.fixArgument();
    }

    public ErrorType fixArgument() {
        if(argument == null) return ErrorType.NULL_ARGUMENT;
        StringBuilder resArg = new StringBuilder("");
        for(int i = 0; i < argument.length(); i++) {
            resArg.append(argument.charAt(i));
            if(i < argument.length()-1 && argument.charAt(i) == ')' && argument.charAt(i+1) == '(') {
                resArg.append('*');
            }
        }
        argument = resArg.toString();
        return ErrorType.NO_ERROR;
    }

    public String removeSpace(String in) {
        StringBuilder res = new StringBuilder("");
        for(int i = 0; i < in.length(); i++) {
            if(in.charAt(i) != ' ') res.append(in.charAt(i));
        }
        return res.toString();
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
