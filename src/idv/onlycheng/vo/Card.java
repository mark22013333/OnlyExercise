package idv.onlycheng.vo;

public class Card {

	private Integer id;
	private String address;
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Description:
	 * 
	 * @author chengmark Dec 25, 2018 5:50:57 PM
	 * @return
	 */
	@Override
	public String toString() {
		return "Card [id=" + id + ", address=" + address + ", person=" + person + "]";
	}

}
