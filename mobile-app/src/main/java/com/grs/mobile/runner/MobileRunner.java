package com.grs.mobile.runner;

import java.util.ArrayList;
import java.util.List;

import com.grs.mobile.dao.MobileDAO;
import com.grs.mobile.dto.MobileDetailsDTO;

public class MobileRunner {

	public static void main(String[] args) {
		MobileDetailsDTO dto1 =new MobileDetailsDTO();
		dto1.setBrandName("Apple");
		dto1.setPrice(110000);
		dto1.setModelName("13pro");
		dto1.setProcessor("ios 14");
		dto1.setRam((byte) 8);
		
		MobileDetailsDTO dto2 =new MobileDetailsDTO();
		dto2.setBrandName("VIVO");
		dto2.setPrice(19000);
		dto2.setModelName("Z!X");
		dto2.setProcessor("snapdragon 825");
		dto2.setRam((byte) 6);
		
		
		MobileDetailsDTO dto3 =new MobileDetailsDTO();
		dto3.setBrandName("GRS");
		dto3.setPrice(29000);
		dto3.setModelName("1.0");
		dto3.setProcessor("snapdragon 865");
		dto3.setRam((byte) 8);
		
		MobileDetailsDTO dto4 =new MobileDetailsDTO();
		dto4.setBrandName("GRS");
		dto4.setPrice(35000);
		dto4.setModelName("2.0");
		dto4.setProcessor("snapdragon 885");
		dto4.setRam((byte) 12);
		
		MobileDetailsDTO dto5 =new MobileDetailsDTO();
		dto5.setBrandName("GRS");
		dto5.setPrice(45000);
		dto5.setModelName("3.0");
		dto5.setProcessor("snapdragon 895");
		dto5.setRam((byte) 16);

		MobileDAO dao = new MobileDAO();
		//dao.save(dto1);
		//dao.save(dto2);
		//dao.updatePriceByBrandName("Nokia 1100", 6000);
		//System.out.println(dao.deleteByModelName("Z!X"));
		//dao.getModelNameAndPriceByBrandName("Nokia");
		//dao.getModelNameAndPriceByBrandName("Nokia","apple");
		
		List<MobileDetailsDTO> detailsDTOs =new  ArrayList<>();
		detailsDTOs.add(dto5);
		detailsDTOs.add(dto4);
		detailsDTOs.add(dto3);
		
		System.out.println(dao.insert(detailsDTOs));
		
		
	}

}
