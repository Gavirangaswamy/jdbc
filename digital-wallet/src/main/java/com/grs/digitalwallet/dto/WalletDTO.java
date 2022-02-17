package com.grs.digitalwallet.dto;

public class WalletDTO {
	private int walletID;
	private String provider;
	private double availableBalance;
	private int tansactions;
	
	public int getWalletID() {
		return walletID;
	}
	public void setWalletID(int walletID) {
		this.walletID = walletID;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public double getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}
	public int getTansactions() {
		return tansactions;
	}
	public void setTansactions(int tansactions) {
		this.tansactions = tansactions;
	}
	


	

}
