/**
 * 
 */
package com.shopbridge.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class SaveFile {

	@Autowired
	private Environment environment;

	public String saveBase64(String base64Str, String name) throws Exception {
		String finalName = null;
		try {
			int index = name.lastIndexOf('.');
			String path = environment.getRequiredProperty("directory.path");
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();
			finalName = name.substring(0, index) + System.currentTimeMillis() + name.substring(index);
			StringBuffer sb = new StringBuffer();
			sb.append(path);
			sb.append(finalName);
			int ind = base64Str.indexOf(",");
			byte[] data = Base64.decodeBase64(base64Str.substring(ind + 1));
			try (OutputStream stream = new FileOutputStream(sb.toString())) {
				stream.write(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return finalName;
	}

}
