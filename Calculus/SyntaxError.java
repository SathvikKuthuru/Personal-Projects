public class SyntaxChecker {
    Expression expression;
    String validCharacters = "()+^-*/0123456789";
    static String operators = "+^-*/";

    public SyntaxChecker(Expression in) {
        expression = in;
        if(expression.getVariable() != null) validCharacters += expression.getVariable();
    }

    public ErrorType checkTotalValidity() {
        if(expression.getArgument() == null) return ErrorType.NULL_ARGUMENT;
        ErrorType[] allErrorCheck = {this.checkCharacters(), this.checkConsecutive(), this.checkParentheses()};
        for(ErrorType e : allErrorCheck) if(e != ErrorType.NO_ERROR) return e;
        return ErrorType.NO_ERROR;
    }

    public ErrorType checkConsecutive() {
        String arg = expression.getArgument();
        boolean operator = false;
        for(int i = 0; i < arg.length(); i++) {
            if(arg.charAt(i) == '(' || arg.charAt(i) == ')') continue;
            if(operator != operators.contains(arg.charAt(i)+"")) return ErrorType.CONSECUTIVE_OPERATOR;
            operator = !operator;
        }
        return ErrorType.NO_ERROR;
    }

    public ErrorType checkParentheses() {
        String arg = expression.getArgument();
        int parenCount = 0;
        for(int i = 0; i < arg.length(); i++) {
            if(arg.charAt(i) == '(') parenCount++;
            if(arg.charAt(i) == ')') parenCount--;
            if(parenCount < 0) return ErrorType.OPEN_PARENTHESES;
        }
        if(parenCount != 0) return ErrorType.CLOSED_PARENTHESES;
        return ErrorType.NO_ERROR;
    }

    public ErrorType checkCharacters() {
        String arg = expression.getArgument();
        for(int i = 0; i < arg.length(); i++) {
            if(!validCharacters.contains(arg.charAt(i)+"")) return ErrorType.INVALID_CHARACTER;
        }
        return ErrorType.NO_ERROR;
    }
}
