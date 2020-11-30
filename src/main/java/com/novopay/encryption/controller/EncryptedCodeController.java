package com.novopay.encryption.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novopay.encryption.config.PopulateCustomerInformationFromFile;

@RestController
public class EncryptedCodeController {

	@Autowired
	PopulateCustomerInformationFromFile populateCustomerInformationFromFile;

	@GetMapping(path = "/api/generateCode")
	public ResponseEntity<Map<String, Long>> generateSecretCode() throws IOException {
        Map<String, Long> map=new HashMap<String, Long>();
		long timeStamp=populateCustomerInformationFromFile.generateSecretCodeForAllCustomer();
		map.put("toaol timestamp in millisecond", timeStamp);
		return new ResponseEntity<Map<String, Long>>(map,HttpStatus.OK);
	}

}
