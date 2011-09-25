package com.novoda.lovepie;

public class Charity {
	
	private String nonprofit_name;
	private String statement;
	private String logo_path;
	private String receiver_email;
	private String web_url;
	private String token_for_invoice_id;
	
	public String getNonprofit_name() {
		return nonprofit_name;
	}
	
	public void setNonprofit_name(String nonprofit_name) {
		this.nonprofit_name = nonprofit_name;
	}
	
	public String getStatement() {
		return statement;
	}
	
	public void setStatement(String statement) {
		this.statement = statement;
	}
	
	public String getLogo_path() {
		return logo_path;
	}
	
	public void setLogo_path(String logo_path) {
		this.logo_path = logo_path;
	}
	
	public String getReceiver_email() {
		return receiver_email;
	}
	
	public void setReceiver_email(String receiver_email) {
		this.receiver_email = receiver_email;
	}
	
	public String getWeb_url() {
		return web_url;
	}
	
	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}
	
	public String getToken_for_invoice_id() {
		return token_for_invoice_id;
	}
	
	public void setToken_for_invoice_id(String token_for_invoice_id) {
		this.token_for_invoice_id = token_for_invoice_id;
	}
	
}
