public enum ErrorType {
    NO_ERROR("No Error Found"),
    OPEN_PARENTHESES("Missing Matching Open Parentheses"),
    CLOSED_PARENTHESES("Missing Matching Closed Parentheses"),
    CONSECUTIVE_OPERATOR("Consecutive Operators Found"),
    INVALID_CHARACTER("Invalid Symbol/Character Found"),
    NULL_ARGUMENT("Missing Argument");

    private String errorReturnMessage;
    public String getMessage() {
        return errorReturnMessage;
    }

    private ErrorType(String m) {
        this.errorReturnMessage = new String(m);
    }
}
