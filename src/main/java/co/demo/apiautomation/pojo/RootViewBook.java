package co.demo.apiautomation.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RootViewBook {
	
	@JsonIgnoreProperties
	private List <ViewBookDetails> viewBookDetails;

	public List<ViewBookDetails> getViewBookDetails() {
		return viewBookDetails;
	}

	public void setViewBookDetails(List<ViewBookDetails> viewBookDetails) {
		this.viewBookDetails = viewBookDetails;
	}
	

}
