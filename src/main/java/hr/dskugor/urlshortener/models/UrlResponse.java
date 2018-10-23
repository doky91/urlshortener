package hr.dskugor.urlshortener.models;

public class UrlResponse {
	
	public boolean success;
	private String description;
	public String shortUrl;

	public UrlResponse() {
	}

	public UrlResponse(boolean success, String description, String shortUrl) {
		super();
		this.success = success;
		this.description = description;
		this.shortUrl = shortUrl;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	
	
	
	
}