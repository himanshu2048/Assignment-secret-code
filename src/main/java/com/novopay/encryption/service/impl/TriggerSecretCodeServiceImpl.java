package com.novopay.encryption.service.impl;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.novopay.encryption.constant.SecretCodeConstant;
import com.novopay.encryption.service.TriggerSecretCodeService;

@Service
public class TriggerSecretCodeServiceImpl extends GenerateUniqueCode implements TriggerSecretCodeService {
	@Override
	public String invoke(Integer id, String nameAndMobileNumber) {
		String code = generateAndSendUniqueCode(nameAndMobileNumber.split(",")[1], nameAndMobileNumber.split(",")[0]);
		return "Code " + code + " sent for customer: " + nameAndMobileNumber.split(",")[0] + " with mobile number: "
				+ nameAndMobileNumber.split(",")[1];
	}

	@Override
	public String generateAndSendUniqueCode(String mobile, String name) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		long random = (long) (Math.random() * 99999) * ts.getTime();
		String generatedCode = SecretCodeConstant.code_prefix + Long.toString(random).substring(0, 4);
		return generatedCode;
	}

}
