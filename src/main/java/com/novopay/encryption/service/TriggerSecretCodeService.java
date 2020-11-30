package com.novopay.encryption.service;

public interface TriggerSecretCodeService {

	String invoke(Integer key, String string);

}
