/**
 * 
 */
package com.shopbridge.util;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomGenerator {

	
	
    public static String generateRandamNo(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	return sdf.format(new Date());
     }
    
	public static String generateNum(int len) {
        String chars = "123456789";
        final int PW_LENGTH = len;
        Random rnd = new SecureRandom();
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < PW_LENGTH; i++)
            pass.append(chars.charAt(rnd.nextInt(chars.length())));
        return pass.toString();
    }
}
