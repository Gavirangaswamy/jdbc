package com.grs.digitalwallet.runner;

import java.util.ArrayList;
import java.util.List;

import com.grs.digitalwallet.dao.WalletDAO;
import com.grs.digitalwallet.dto.WalletDTO;

public class WalletRunner {

	public static void main(String[] args) {
		WalletDTO dto1 =new WalletDTO();
		dto1.setWalletID(1);
		dto1.setProvider("PhonePe");
		dto1.setAvailableBalance(1520);
		dto1.setTansactions(15);
		
		WalletDTO dto2 =new WalletDTO();
		dto2.setWalletID(2);
		dto2.setProvider("GRSPay");
		dto2.setAvailableBalance(158920);
		dto2.setTansactions(150);
		
		
		WalletDAO dao =  new WalletDAO();
		
		List<WalletDTO> detailsDTOs =new  ArrayList<>();
		detailsDTOs.add(dto1);
		detailsDTOs.add(dto2);
			
		dao.insert(detailsDTOs);
		
		
	}

}
