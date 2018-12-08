package card.util;

public interface Game {
	public boolean finished();
	public void deal(int n);
	public void play();
	public void printOptions();
	public String end();
}
