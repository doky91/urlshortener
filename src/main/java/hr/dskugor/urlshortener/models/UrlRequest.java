package hr.dskugor.urlshortener.models;

public class UrlRequest {
	
	private String url;
	private Integer redirectType;
	
	public UrlRequest(String url, Integer redirectType) {
		this.url = url;
		this.redirectType = redirectType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(Integer redirectType) {
		this.redirectType = redirectType;
	}
	
	
	

}
