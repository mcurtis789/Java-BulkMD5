package com.ccg.filechecker.main;
/**
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
*  INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
*  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL JCRAFT,
*  INC. OR ANY CONTRIBUTORS TO THIS SOFTWARE BE LIABLE FOR ANY DIRECT, INDIRECT,
*  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
*  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
*  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
*  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
*  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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