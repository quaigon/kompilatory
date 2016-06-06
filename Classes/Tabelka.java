public class Tabelka {

	Int Id;
	String Innanazwa;
	String Nazwa;
	Int Doub;

	public Tabelka(){
	}

	public Tabelka(Int Id, String Innanazwa, String Nazwa, Int Doub) {
		this.Id = Id;
		this.Innanazwa = Innanazwa;
		this.Nazwa = Nazwa;
		this.Doub = Doub;

	}

	public Int getId() {
		return Id;
	}

	public void setId(Int Id) {
		this.Id = Id;
	}

	public String getInnanazwa() {
		return Innanazwa;
	}

	public void setInnanazwa(String Innanazwa) {
		this.Innanazwa = Innanazwa;
	}

	public String getNazwa() {
		return Nazwa;
	}

	public void setNazwa(String Nazwa) {
		this.Nazwa = Nazwa;
	}

	public Int getDoub() {
		return Doub;
	}

	public void setDoub(Int Doub) {
		this.Doub = Doub;
	}


}