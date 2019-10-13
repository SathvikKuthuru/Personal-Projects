
public enum CalculationType {
	DERIVATIVE("derive", "derivative"), 
	INTEGERAL("integral", "integrate", "antiderivative");
	
	private String[] equivalent;
	
	public String[] getEquivalent() {
		return this.equivalent;
	}
	
	private CalculationType(String... possible) {
		this.equivalent = new String[possible.length];
		for(int i = 0; i < possible.length; i++) {
			this.equivalent[i] = possible[i];
		}
	}
	
}
