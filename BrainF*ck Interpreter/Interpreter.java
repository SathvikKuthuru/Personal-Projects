public class Interpreter {
        int[] memory;
        int index;
        
        public Interpreter() {
            this.memory = new int[30000];
            this.index = 0;
        }
        
        public void interpret(Parser parser) {
            for(Command c : parser.cmd) {
                if(c.label == '>') this.index++;
                if(c.label == '<') this.index--;
                if(c.label == '+') this.memory[this.index]++;
                if(c.label == '-') this.memory[this.index]--;
                if(c.label == '.') System.out.print((char) this.memory[this.index]);
                if(c.label == 'R') {
                    Parser subParser = new Parser(c.subData);
                    subParser.parse();
                    while(this.memory[this.index] > 0) {
                        interpret(subParser);
                    }
                }
            }
        }
    }
