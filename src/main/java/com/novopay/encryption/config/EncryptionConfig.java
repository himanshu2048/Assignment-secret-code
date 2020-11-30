package com.novopay.encryption.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.ResourceUtils;

@Configuration
@PropertySource("classpath:config/config.properties")
public class EncryptionConfig {
	
    private static final Logger logger = LogManager.getLogger(PopulateCustomerInformationFromFile.class); 
	
	@Value("${input.file}")	
	private String inputFile;
	
	
	
	public File getInputFile() throws FileNotFoundException {
		return ResourceUtils.getFile(this.inputFile);
	}
	
	
	@Bean("getInputFile")
	public Map<Integer, String>  populateCustomerDetails() throws IOException{
		logger.info("populate file information...");
		Map<Integer, String> inputData=new HashMap<Integer, String>();
		try(BufferedReader in = new BufferedReader(new FileReader(getInputFile()))) {
		    String str;
		    while ((str = in.readLine()) != null) {
		    	String[] tokens = str.split(",");
		    	int uniqueCustomerId = null!=tokens[0]?Integer.parseInt(tokens[0]):null;
		    	String nameAndMobileNumber = (tokens[1])+","+(tokens[2]);
		    	inputData.put(uniqueCustomerId, nameAndMobileNumber);
		    }
		}
		catch (IOException e) {
		   throw e;
		}
		return inputData;	
	}
	

}
