package com.ccg.filechecker.operations;

import java.io.File;
import java.util.Collection;

public class ListDirectory{

	public void addTree(File file, Collection<File> all) {
		File[] children = file.listFiles();
	    if (children != null) {
	        for (File child : children) {	
	        	System.out.println(child);
	        	all.add(child);
	            addTree(child, all);
	        }
	    }if (file.isFile()) {	        
	    	System.out.println(file);
	    	all.add(file);	        
	    }
	}
}