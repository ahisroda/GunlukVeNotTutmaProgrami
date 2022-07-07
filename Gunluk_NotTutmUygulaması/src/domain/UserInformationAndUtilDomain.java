package domain;

public class UserInformationAndUtilDomain {

	private String kimlik;
	private String sifre;
	private String hesapKurtarmaNo;
	private String adi;
	private int aktiflik;
	private String imza;
	private String sayfaSayisi;
	private String konuBasligi;
	private String esasGunluk;

	public String getKimlik() {
		return kimlik;
	}

	public void setKimlik(String kimlik) {
		this.kimlik = kimlik;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String getHesapKurtarmaNo() {
		return hesapKurtarmaNo;
	}

	public void setHesapKurtarmaNo(String hesapKurtarmaNo) {
		this.hesapKurtarmaNo = hesapKurtarmaNo;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public int getAktiflik() {
		return aktiflik;
	}

	public void setAktiflik(int aktiflik) {
		this.aktiflik = aktiflik;
	}

	public String getImza() {
		return imza;
	}

	public void setImza(String imza) {
		this.imza = imza;
	}

	public String getSayfaSayisi() {
		return sayfaSayisi;
	}

	public void setSayfaSayisi(String sayfaSayisi) {
		this.sayfaSayisi = sayfaSayisi;
	}

	public String getKonuBasligi() {
		return konuBasligi;
	}

	public void setKonuBasligi(String konuBasligi) {
		this.konuBasligi = konuBasligi;
	}

	public String getEsasGunluk() {
		return esasGunluk;
	}

	public void setEsasGunluk(String esasGunluk) {
		this.esasGunluk = esasGunluk;
	}

	@Override
	public String toString() {
		return sayfaSayisi + " / " + konuBasligi;
	}

}
