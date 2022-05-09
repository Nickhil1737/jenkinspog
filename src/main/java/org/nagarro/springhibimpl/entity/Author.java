package org.nagarro.springhibimpl.entity;

import javax.persistence.Id;

public class Author {
	private int aid;
	
	private String name;

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author(int aid, String name) {
		super();
		this.aid = aid;
		this.name = name;
	}

	public Author() {
		super();
	}

	@Override
	public String toString() {
		return "Author [aid=" + aid + ", name=" + name + "]";
	}
	

}
