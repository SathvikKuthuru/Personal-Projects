import java.util.Scanner;

public class Program {
	String input;
	Parser parser;
	Interpreter interpreter;

	public void execute() {
		this.interpreter.interpret(parser);
	}

	public void read() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			String in = scan.next();
			if (in.equals("0"))
				break;
			this.input += in;
		}
		this.parser = new Parser(input);
		this.interpreter = new Interpreter();
	}
}
