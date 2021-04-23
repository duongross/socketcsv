package sample;

import java.io.Serializable;

public class Advertising implements Serializable{
	private String tv;
	private String radio;
	private String news;
	private String sales;
	
	public Advertising(String tv, String radio, String news, String sales) {
		this.tv=tv;
		this.radio=radio;
		this.news=news;
		this.sales=sales;
	}
	
	public String getTV() {
		return tv;
	}
	public String getRadio() {
		return radio;
	}
	public String getNews() {
		return news;
	}
	public String getSales() {
		return sales;
	}
	
	 @Override
	    public String toString() {
	        return tv + "_" + radio + "_" + news + "_" + sales;
	    }

}

