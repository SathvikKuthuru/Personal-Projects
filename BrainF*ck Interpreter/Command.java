public class Command {
	String subData;
	char label;

	public Command(char s) {
		this.label = s;
		this.subData = "empty";
	}

	public Command(char s, String b) {
		this.label = s;
		this.subData = b;
	}
}
