package idv.onlycheng.vo;

public class Person {
	private Integer id;
	private String name;
	private String sex;
	private Card card;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	/**
	 * Description:
	 * 
	 * @author chengmark Dec 25, 2018 5:50:41 PM
	 * @return
	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", sex=" + sex + ", card=" + card + "]";
	}

}
