package com.st.advancedtopics;

public class Company {
    private String name, phone,url;

	public Company(String name, String phone, String url) {
		super();
		this.name = name;
		this.phone = phone;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
