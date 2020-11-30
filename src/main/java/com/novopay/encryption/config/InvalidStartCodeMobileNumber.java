package com.novopay.encryption.config;

public enum InvalidStartCodeMobileNumber {
	
	five("555"),six("666");
	
	private String startsWith;

	private InvalidStartCodeMobileNumber(String startsWith) {
		this.startsWith = startsWith;
	}

	public String getToBeBlockCustomer() {
		return this.startsWith;
	}
}
