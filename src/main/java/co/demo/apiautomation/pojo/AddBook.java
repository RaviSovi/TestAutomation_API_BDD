package co.demo.apiautomation.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBook {
	
	@JsonProperty
	private String Msg;
	
	@Override
	public String toString() {
		return "AddBook [Msg=" + Msg + ", ID=" + ID + "]";
	}
	@JsonProperty
	private String ID;
	
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
}
