package hr.dskugor.urlshortener.models;

public class AccountRequest {

	private String accountId;

	public AccountRequest() {
	}

	public AccountRequest(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
