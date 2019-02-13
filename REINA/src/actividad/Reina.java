package actividad;

public class Reina {
	public int x, y;
	String name;

	public Reina(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public Reina(String name) {
		this.name = name;
	}

	public void setString(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
