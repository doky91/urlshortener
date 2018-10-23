package hr.dskugor.urlshortener.models;

public class Link {

	private String url;
	private String shortUrl;
	private int linkVisits;
	private Integer redirectType;
	private String user;

	public Link() {
	}

	public Link(String url, String shortUrl, int linkVisits, Integer redirectType, String user) {
		this.url = url;
		this.shortUrl = shortUrl;
		this.linkVisits = linkVisits;
		this.redirectType = redirectType;
		this.user = user;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getLinkVisits() {
		return linkVisits;
	}

	public void setLinkVisits(int linkVisits) {
		this.linkVisits = linkVisits;
	}

	public Integer getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(Integer redirectType) {
		this.redirectType = redirectType;
	}
	
}
