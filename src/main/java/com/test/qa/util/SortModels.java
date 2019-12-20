package com.test.qa.util;

public class SortModels implements Comparable<SortModels> {
	private String name;
	private String href;
	private int price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public SortModels(String href, String name, int price) {
		super();
		this.name = name;
		this.href = href;
		this.price = price;
	}

	public int compareTo(SortModels m) {
		return this.price - m.price;
	}
}
