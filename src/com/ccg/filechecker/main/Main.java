package com.ccg.filechecker.main;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.ccg.filechecker.operations.CheckSum;
import com.ccg.filechecker.operations.ListDirectory;

public class Main{
	public static void main(String[] args) throws Exception {
		String path = "";
		if (Arrays.asList(args).isEmpty()){
			System.out.println("you need to set a path. i.e c:\\ or .");
		}else{
		path = Arrays.asList(args).get(0);
	    Collection<File> all = new ArrayList<File>();
	    ListDirectory l = new ListDirectory();
	    l.addTree(new File(path), all);

	    PrintWriter writer = new PrintWriter("CHECKSUM.csv", "UTF-8");
	    CheckSum cs = new CheckSum();
	    int count = 1;
	    for (Iterator i=all.iterator(); i.hasNext(); ){
	    	
	    	File f =new File(i.next().toString());
	    	if(f.isDirectory()){
//	    		System.out.println("Directory " +f.getAbsolutePath());	
	    	}else if (f.isFile()){ 
	    		writer.println(f.getAbsolutePath()+","+cs.checkSum(f.getAbsolutePath()));
	    		System.out.println("processed "+count+" of "+all.size());
	    	}
	    	count++;
		}
		writer.flush();
		writer.close();
		}
	}
}