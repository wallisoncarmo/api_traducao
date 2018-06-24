package com.carmowallison.base_api.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.carmowallison.base_api.security.UserSS;

public class UserSService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
