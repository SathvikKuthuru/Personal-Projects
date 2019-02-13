import java.util.ArrayList;

public class Parser {
	char[] rawData;
	ArrayList<Command> cmd;
	Integer index = 0;

	public Parser(String g) {
		this.rawData = g.toCharArray();
		this.cmd = new ArrayList<>();
	}

	public void parse() {
		for (this.index = 0; this.index < this.rawData.length; this.index++) {
			if (this.rawData[this.index] == ']' || this.rawData[this.index] == ' ')
				continue;
			if (this.rawData[this.index] == '[') {
				String subCmd = getRepeat();
				this.cmd.add(new Command('R', subCmd));
			} else
				this.cmd.add(new Command(this.rawData[this.index]));
		}
	}

	public String getRepeat() {
		String res = "";
		int b = 1;
		int e = 0;
		this.index++;
		while (true) {
			if(this.rawData[this.index] == ' ') {
				this.index++;
				continue;
			}
			if (this.rawData[this.index] == '[')
				b++;
			else if (this.rawData[this.index] == ']')
				e++;
			if (b == e)
				break;
			res += this.rawData[this.index];
			this.index++;
		}
		return res;
	}
}
