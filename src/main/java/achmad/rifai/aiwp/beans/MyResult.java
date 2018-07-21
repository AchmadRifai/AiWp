package achmad.rifai.aiwp.beans;

public class MyResult {
	private String nama;
	private double v;

	public MyResult(String nama, double v) {
		super();
		this.nama = nama;
		this.v = v;
	}

	@Override
	public String toString() {
		return "MyResult [nama=" + nama + ", v=" + v + "]";
	}
}
