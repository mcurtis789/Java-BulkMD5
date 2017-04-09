package com.ccg.filechecker.operations;

import java.io.FileInputStream;
import java.security.MessageDigest;


public class CheckSum{	  
	public static String checkSum(String path) throws Exception{
		MessageDigest md = MessageDigest.getInstance("MD5");
		FileInputStream fis = new FileInputStream(path);
		byte[] dataBytes = new byte[1024];
		int nread = 0; 
		while ((nread = fis.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		};
		byte[] mdbytes = md.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i=0;i<mdbytes.length;i++) {
			String hex=Integer.toHexString(0xff & mdbytes[i]);
			if(hex.length()==1) hexString.append('0');
			hexString.append(hex);
		}    
		return hexString.toString();
	}
}