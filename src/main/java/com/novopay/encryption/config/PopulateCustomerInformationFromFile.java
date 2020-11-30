package com.novopay.encryption.config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.novopay.encryption.constant.SecretCodeConstant;
import com.novopay.encryption.service.TriggerSecretCodeService;

@Component
public class PopulateCustomerInformationFromFile {
	@Autowired
	TriggerSecretCodeService triggerSecretCodeService;

	@Autowired
	@Qualifier("getInputFile")
	private Map<Integer, String> inputData;

	public Long generateSecretCodeForAllCustomer() throws IOException {
		FileWriter myWriter = new FileWriter(SecretCodeConstant.response_file_name);
		BufferedWriter bufferedWriter = new BufferedWriter(myWriter);
		List<String> list = new ArrayList<String>();
		long startTime = System.currentTimeMillis();
		long endTime = 0;
		for (Map.Entry<Integer, String> object : inputData.entrySet()) {
			if (BusinessRulesConfiguration.isCustomerEligibleForSecretCode(object.getKey(), (object.getValue()))) {
				list.add(triggerSecretCodeService.invoke(object.getKey(), (object.getValue())));
				bufferedWriter.append(triggerSecretCodeService.invoke(object.getKey(), (object.getValue()))); //append the lines to the string
				bufferedWriter.append('\n');
			}
		}
		bufferedWriter.close();
		endTime = System.currentTimeMillis();
		return ((endTime - startTime) );
		
	}
}
