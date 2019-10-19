
public class Parser {

    private String input;

    public Parser(String in) {
        input = new String(in);
    }

    public CalculationType getType() {
        if(!input.contains("(")) return null;
        String result = input.substring(0, input.indexOf("("));
        CalculationType[] possibleTypes = CalculationType.values();
        for(CalculationType currentType : possibleTypes) {
            for(String value : currentType.getEquivalent()) {
                if(result.equals(value)) return currentType;
            }
        }
        return null;
    }

    public String getVar() {
        int num = countComma();
        if(num == 0) {
            String value = null;
            String deduceFromArg = getArg();
            if(deduceFromArg == null) return null;
            for(int i = 0; i < deduceFromArg.length(); i++) {
                char current = deduceFromArg.charAt(i);
                if(Character.isAlphabetic(current)) {
                    value = current+"";
                    break;
                }
            }
            return value;
        }
        else if(num == 1) {
            if(!input.contains(")")) return null;
            int endParen = input.lastIndexOf(")");
            int openComma = input.indexOf(",");
            if(endParen != input.length()-1) return null;
            String result = input.substring(openComma+1, endParen);
            if(result.length() != 1) return null;
            return result;
        }
        return null;
    }

    public String getArg() {
        if(!input.contains("(") || !input.contains(")")) return null;
        int openParen = input.indexOf("(");
        int endParen = input.lastIndexOf(")");
        int num = countComma();
        if(num == 0) {
            if(endParen != input.length()-1) return null;
            return input.substring(openParen+1, endParen);
        }
        else if(num == 1) {
            int endComma = input.lastIndexOf(",");
            if(endComma < openParen || endComma > endParen) return null;
            return input.substring(openParen+1, endComma);
        }
        return null;
    }

    public int countComma() {
        int count = 0;
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == ',') count++;
        }
        return count;
    }

}
