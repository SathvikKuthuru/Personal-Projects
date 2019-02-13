//Created By Sathvik Kuthuru
import java.util.*;
public class Main {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String input  = "";
        while(true) {
            String in = scan.next();
            if(in.equals("0")) break;
            input += in;
        }
        System.out.println(input);
        Compiler compiler = new Compiler(input);
        compiler.compile();
    }
    
    static class Compiler {
        Parser parser;
        Interpreter interpreter;
        
        public Compiler(String s) {
            parser = new Parser(s);
            interpreter = new Interpreter();
        }
        
        public void compile() {
            interpreter.interpret(parser);
        }
    }
    
    static class Interpreter {
        int[] a;
        int index;
        
        public Interpreter() {
            a = new int[30000];
            index = 0;
        }
        
        public void interpret(Parser parser) {
            parser.parse();
            for(Command c : parser.cmd) {
                if(c.label == '>') index++;
                if(c.label == '<') index--;
                if(c.label == '+') a[index]++;
                if(c.label == '-') a[index]--;
                if(c.label == '.'); //System.out.print((char) a[index]);
                if(c.label == 'R') {
                    Parser subParser = new Parser(c.subData);
                    //System.out.println(c.sub);
                    while(a[index] > 0) {
                        System.out.println(index);
                        interpret(subParser);
                    }
                }
            }
        }
    }
    
    
    static class Parser {
        char[] rawData;
        ArrayList<Command> cmd;
        Integer index = 0;
        
        public Parser(String g) {
            rawData = g.toCharArray();
            cmd = new ArrayList<>();
        }
        
        public void parse() {
            for(index = 0; index < rawData.length; index++) {
                if(rawData[index] == ']' || rawData[index] == ' ') continue;
                if(rawData[index] == '[') {
                    String subCmd = getRepeat();
                    cmd.add(new Command('R', subCmd));
                }
                else cmd.add(new Command(rawData[index]));
            }
        }
        
        public String getRepeat() {
            String res = "";
            int b = 1;
            int e = 0;
            index+=1;
            while(true) {
                if(rawData[index] == '[') b++;
                else if(rawData[index] == ']') e++;
                if(b == e) break;
                res += rawData[index];
                index+=1;
            }
            return res;
        }
    }
    
    static class Command {
        String subData;
        char label;
        
        public Command(char s) {
            label = s;
            subData = "empty";
        }
        
        public Command(char s, String b) {
            label = s;
            subData = b;
        }
    }
}
