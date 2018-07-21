package achmad.rifai.aiwp.util;

import java.io.IOException;
import java.util.List;

import achmad.rifai.aiwp.beans.*;

public class Work {
	public static List<Krit> loadKrit(String krit_path) throws IOException {
		List<Krit>l=new java.util.LinkedList<>();
		java.io.File f=new java.io.File(krit_path);
		com.google.gson.JsonParser p=new com.google.gson.JsonParser();
		if(f.exists()) {
			java.io.FileReader r=new java.io.FileReader(f);
			com.google.gson.JsonArray a=p.parse(r).getAsJsonArray();
			for(int x=0;x<a.size();x++) {
				com.google.gson.JsonObject s=a.get(x).getAsJsonObject();
				Krit k=new Krit();
				k.setCb(s.get("cb").getAsString());
				k.setKep(s.get("kep").getAsFloat());
				k.setKrit(s.get("krit").getAsString());
				l.add(k);
			} r.close();
		}else System.out.println("Krit json not found");
		return l;
	}

	public static List<Data> loadData(String data_path) throws IOException {
		List<Data>l=new java.util.LinkedList<>();
		java.io.File f=new java.io.File(data_path);
		com.google.gson.JsonParser p=new com.google.gson.JsonParser();
		if(f.exists()) {
			java.io.FileReader r=new java.io.FileReader(f);
			com.google.gson.JsonArray a=p.parse(r).getAsJsonArray();
			for(int x=0;x<a.size();x++) {
				com.google.gson.JsonObject s=a.get(x).getAsJsonObject();
				Data d=new Data();
				d.setKrit(s.get("krit").getAsString());
				d.setNama(s.get("nama").getAsString());
				d.setValue(s.get("value").getAsDouble());
				l.add(d);
			} r.close();
		}else System.out.println("Data json not found");
		return l;
	}

	public static Matriks matriksKrit(List<Krit> lk) {
		Matriks m=new Matriks("Krit Matriks",1,lk.size());
		for(int y=0;y<m.getKolom();y++) {
			Krit k=lk.get(y);
			m.set(0, y, k.getKep());
		} return m;
	}

	public static Matriks matriksData(List<Data> ld, List<Krit> lk) {
		List<String>ln=listNamaData(ld);
		Matriks m=new Matriks("Matriks Data",ln.size(),lk.size());
		for(int x=0;x<m.getBaris();x++) {
			String n=ln.get(x);
			for(int y=0;y<m.getKolom();y++) {
				Krit k=lk.get(y);
				m.set(x, y, getValData(ld,n,k.getKrit()));
			}
		}
		return m;
	}

	private static double getValData(List<Data> ld, String n, String krit) {
		double d=0;
		for(Data da:ld) {
			if(n.equals(da.getNama())&&krit.equals(da.getKrit())) {
				d=da.getValue();
				break;
			}
		} return d;
	}

	private static List<String> listNamaData(List<Data> ld) {
		List<String>l=new java.util.LinkedList<>();
		ld.forEach((d)->{
			if(!l.contains(d.getNama()))
				l.add(d.getNama());
		});
		return l;
	}

	public static Matriks genBobot(Matriks mk) {
		Matriks m=new Matriks("Bobot Kepentingan",1,mk.getKolom());
		double pembagi=0;
		for(int y=0;y<mk.getKolom();y++)
			pembagi+=mk.get(0, y);
		for(int y=0;y<mk.getKolom();y++)
			m.set(0, y, mk.get(0, y)/pembagi);
		return m;
	}

	public static Matriks genPangkat(List<Krit> lk, Matriks bobot) {
		Matriks m=new Matriks("Pangkat",1,lk.size());
		for(int y=0;y<lk.size();y++) {
			Krit k=lk.get(y);
			if("BENEFIT".equals(k.getCb()))
				m.set(0, y, bobot.get(0, y));
			else m.set(0, y, bobot.get(0, y)*-1);
		} return m;
	}

	public static Matriks genS(Matriks md, Matriks pangkat) {
		Matriks m=new Matriks("S",md.getBaris(),1);
		for(int x=0;x<m.getBaris();x++) {
			double v=1;
			for(int y=0;y<md.getKolom();y++)
				v*=Math.pow(md.get(x, y), pangkat.get(0, y));
			m.set(x, 0, v);
		} return m;
	}

	public static List<MyResult> genResult(List<Data> ld, Matriks s) {
		List<MyResult>l=new java.util.LinkedList<>();
		List<String>ln=listNamaData(ld);
		double add=0;
		for(int x=0;x<s.getBaris();x++)
			add+=s.get(x, 0);
		for(int x=0;x<ln.size();x++) {
			String n=ln.get(x);
			l.add(new MyResult(n,s.get(x, 0)/add));
		}
		return l;
	}
}
