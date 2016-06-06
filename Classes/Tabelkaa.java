public class Tabelkaa {

	Int Id;
	Int Nazwy;
	Double Jakisdoubel;

	public Tabelkaa(){
	}

	public Tabelkaa(Int Id, Int Nazwy, Double Jakisdoubel) {
		this.Id = Id;
		this.Nazwy = Nazwy;
		this.Jakisdoubel = Jakisdoubel;

	}

	public Int getId() {
		return Id;
	}

	public void setId(Int Id) {
		this.Id = Id;
	}

	public Int getNazwy() {
		return Nazwy;
	}

	public void setNazwy(Int Nazwy) {
		this.Nazwy = Nazwy;
	}

	public Double getJakisdoubel() {
		return Jakisdoubel;
	}

	public void setJakisdoubel(Double Jakisdoubel) {
		this.Jakisdoubel = Jakisdoubel;
	}


}