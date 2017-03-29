package com.po;

/**
 * @author FFFF Put undefined The's Not me want. insert in angel nice 2016年9月21日
 */
public class Registration {

	private Integer id;

	private String city;

	private String section;

	private String title;

	private String reservationDate;

	private String name;

	private String gender;

	private String age;

	private String phone;

	private String content;

	private String money;

	private String orderCode;

	private String orderStatus;

	private Integer userId;

	private String createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", city=" + city + ", section="
				+ section + ", title=" + title + ", reservationDate="
				+ reservationDate + ", name=" + name + ", gender=" + gender
				+ ", age=" + age + ", phone=" + phone + ", content=" + content
				+ ", money=" + money + ", orderCode=" + orderCode
				+ ", orderStatus=" + orderStatus + ", userId=" + userId
				+ ", createTime=" + createTime + "]";
	}

}
