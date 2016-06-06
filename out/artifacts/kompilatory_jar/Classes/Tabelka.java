public class Tabelka {

	Int Id;
	Double Pierwszydouble;
	String Pierwszystring;
	String Drugistring;

	public Tabelka(){
	}

	public Tabelka(Int Id, Double Pierwszydouble, String Pierwszystring, String Drugistring) {
		this.Id = Id;
		this.Pierwszydouble = Pierwszydouble;
		this.Pierwszystring = Pierwszystring;
		this.Drugistring = Drugistring;

	}

	public Int getId() {
		return Id;
	}

	public void setId(Int Id) {
		this.Id = Id;
	}

	public Double getPierwszydouble() {
		return Pierwszydouble;
	}

	public void setPierwszydouble(Double Pierwszydouble) {
		this.Pierwszydouble = Pierwszydouble;
	}

	public String getPierwszystring() {
		return Pierwszystring;
	}

	public void setPierwszystring(String Pierwszystring) {
		this.Pierwszystring = Pierwszystring;
	}

	public String getDrugistring() {
		return Drugistring;
	}

	public void setDrugistring(String Drugistring) {
		this.Drugistring = Drugistring;
	}


}