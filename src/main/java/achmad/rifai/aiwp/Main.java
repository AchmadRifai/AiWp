package achmad.rifai.aiwp;

import java.io.IOException;
import java.util.List;

import achmad.rifai.aiwp.beans.*;
import achmad.rifai.aiwp.util.Work;

public class Main {

	public static void main(String[] args) {
		String data_path="parame/data.json",krit_path="parame/krit.json";
		try {
			List<Krit>lk=Work.loadKrit(krit_path);
			List<Data>ld=Work.loadData(data_path);
			Matriks mk=Work.matriksKrit(lk),md=Work.matriksData(ld,lk),bobot=
					Work.genBobot(mk),pangkat=Work.genPangkat(lk,bobot),s=
					Work.genS(md,pangkat);
			System.out.println(""+mk+"\n"+md+"\n"+bobot+"\n"+pangkat+"\n"+s);
			List<MyResult>lm=Work.genResult(ld,s);
			lm.forEach((r)->System.out.println(r));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
