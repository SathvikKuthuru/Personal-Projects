public class Command {
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