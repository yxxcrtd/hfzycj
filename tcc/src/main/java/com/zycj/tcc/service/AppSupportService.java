package com.zycj.tcc.service;

import java.util.Map;

public interface AppSupportService {
	public Integer appErrorRecord(Map<String, String> errors);
	public Integer appInstall(Map<String, String> installs);
}
