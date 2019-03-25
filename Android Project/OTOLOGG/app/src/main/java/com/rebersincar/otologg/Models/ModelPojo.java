package com.rebersincar.otologg.Models;

public class ModelPojo{

	private String agirlik;
	private String yakitid;
	private String modelid;
	private String markaid;
	private String puan;
	private String fiyat;
	private String modelyil;
	private String markaad;
	private String motorgucu;
	private String modelad;
	private String koltuksayisi;
	private String silindirsayisi;
	private String yakitdepohacmi;
	private String vitesid;
	private String viteskademe;
	private String hiz;
	private String kapisayisi;

	public String getAgirlik(){
		return agirlik;
	}

	public String getYakitid(){
		return yakitid;
	}

	public String getModelid(){
		return modelid;
	}

	public String getPuan(){
		return puan;
	}

	public String getFiyat(){
		return fiyat;
	}

	public String getModelyil(){
		return modelyil;
	}

	public String getMarkaid(){
		return markaid;
	}

	public String getMarkaad(){
		return markaid;
	}

	public String getMotorgucu(){
		return motorgucu;
	}

	public String getModelad(){
		return modelad;
	}

	public String getKoltuksayisi(){
		return koltuksayisi;
	}

	public String getSilindirsayisi(){
		return silindirsayisi;
	}

	public String getYakitdepohacmi(){
		return yakitdepohacmi;
	}

	public String getVitesid(){
		return vitesid;
	}

	public String getViteskademe(){
		return viteskademe;
	}

	public String getHiz(){
		return hiz;
	}

	public String getKapisayisi(){
		return kapisayisi;
	}

	@Override
	public String toString(){
		return
				"ModelPojo{" +
						"agirlik = '" + agirlik + '\'' +
						",yakitid = '" + yakitid + '\'' +
						",modelid = '" + modelid + '\'' +
						",puan = '" + puan + '\'' +
						",fiyat = '" + fiyat + '\'' +
						",modelyil = '" + modelyil + '\'' +
						",markaid = '" + markaid + '\'' +
						",markaad = '" + markaad + '\'' +
						",motorgucu = '" + motorgucu + '\'' +
						",modelad = '" + modelad + '\'' +
						",koltuksayisi = '" + koltuksayisi + '\'' +
						",silindirsayisi = '" + silindirsayisi + '\'' +
						",yakitdepohacmi = '" + yakitdepohacmi + '\'' +
						",vitesid = '" + vitesid + '\'' +
						",viteskademe = '" + viteskademe + '\'' +
						",hiz = '" + hiz + '\'' +
						",kapisayisi = '" + kapisayisi + '\'' +
						"}";
	}
}
