package com.novopay.encryption.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class BusinessRulesConfiguration {
	private static final Logger logger = LogManager.getLogger(BusinessRulesConfiguration.class);

	public static long generateUniqueCode(int id, long mobileNumber) {

		return 0;
	}

	public static boolean isCustomerEligibleForSecretCode(int id, String nameAndMobile) {
		String[] tokens = nameAndMobile.split(","); 
		String name=tokens[0];
		String mobile=tokens[1];
		return (null != mobile && mobile.length() == 10) ? checkSuccessStartsWithRule(id, name, mobile) : false;

	}

	private static boolean checkSuccessStartsWithRule(int id, String name, String mobile) {
		boolean flag = true;
			for (InvalidStartCodeMobileNumber codeMobileNumber : InvalidStartCodeMobileNumber.values()) {
				if (mobile.startsWith(codeMobileNumber.getToBeBlockCustomer())) {
					flag = false;
					logger.info("Message cannot be sent to: " + mobile + " of customer " + name + "  with customer id: " + id);
					break;
				}
			}
		return flag;
	}

}
