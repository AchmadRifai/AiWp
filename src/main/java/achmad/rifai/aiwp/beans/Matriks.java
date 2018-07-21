package achmad.rifai.aiwp.beans;

public class Matriks {
	private String nama;
	private int baris,kolom;
	private double[]val;

	public Matriks(String nama, int p) {
		super();
		this.nama = nama;
		this.baris = p;
		kolom=p;
		val=new double[baris*kolom];
		for(int x=0;x<baris;x++)
			for(int y=0;y<kolom;y++)
				val[y+(x*kolom)]=0;
	}

	public Matriks(String nama, int baris, int kolom) {
		super();
		this.nama = nama;
		this.baris = baris;
		this.kolom = kolom;
		val=new double[baris*kolom];
		for(int x=0;x<baris;x++)
			for(int y=0;y<kolom;y++)
				val[y+(x*kolom)]=0;
	}

	public int getBaris() {
		return baris;
	}

	public int getKolom() {
		return kolom;
	}

	public double get(int x, int y) {
		double d=0;
		if(x<baris&&y<kolom)d=val[y+(x*kolom)];
		else System.out.println(nama+" Index terlalu besar");
		return d;
	}

	public void set(int x, int y, double v) {
		if(x<baris&&y<kolom)val[y+(x*kolom)]=v;
		else System.out.println(nama+" Index terlalu besar");
	}

	@Override
	public String toString() {
		String s=nama+"\n";
		for(int x=0;x<baris;x++) {
			for(int y=0;y<kolom;y++)
				s+=val[y+(x*kolom)]+" ";
			s+="\n";
		} return s;
	}
}
