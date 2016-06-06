public class Drugabelka {

	Int Id;
	String Trzecitring;
	String Czwartytring;

	public Drugabelka(){
	}

	public Drugabelka(Int Id, String Trzecitring, String Czwartytring) {
		this.Id = Id;
		this.Trzecitring = Trzecitring;
		this.Czwartytring = Czwartytring;

	}

	public Int getId() {
		return Id;
	}

	public void setId(Int Id) {
		this.Id = Id;
	}

	public String getTrzecitring() {
		return Trzecitring;
	}

	public void setTrzecitring(String Trzecitring) {
		this.Trzecitring = Trzecitring;
	}

	public String getCzwartytring() {
		return Czwartytring;
	}

	public void setCzwartytring(String Czwartytring) {
		this.Czwartytring = Czwartytring;
	}


}