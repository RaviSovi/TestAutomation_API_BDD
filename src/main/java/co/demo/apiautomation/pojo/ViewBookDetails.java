package co.demo.apiautomation.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ViewBookDetails {
	
	@JsonProperty
	private String book_name;
	@JsonProperty
	private String isbn;
	@JsonProperty
	private String aisle;
	@JsonProperty
	private String author;
	
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getAisle() {
		return aisle;
	}
	public void setAisle(String aisle) {
		this.aisle = aisle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}	
}
