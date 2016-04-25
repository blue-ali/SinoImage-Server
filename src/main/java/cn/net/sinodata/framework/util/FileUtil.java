package cn.net.sinodata.framework.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
	
	public static InputStream byte2input(byte[] buf) {  
        return new ByteArrayInputStream(buf);  
    }  
  
    public static byte[] input2byte(InputStream inStream)  
            throws IOException {  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        byte[] b = new byte[100];  
        int rc = 0;  
        while ((rc = inStream.read(b, 0, 100)) > 0) {  
        	bos.write(b, 0, rc);  
        }  
        byte[] buffer = bos.toByteArray();  
        return buffer;  
    }  

	public static byte[] file2byte(String filePath) throws IOException {
		byte[] buffer = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			File file = new File(filePath);
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			buffer = bos.toByteArray();
		} finally {
			if(fis != null)
				fis.close();
			if(bos != null)
				bos.close();
		}
		return buffer;
	}
	
	public static byte[] file2byte(File file) throws IOException {
		byte[] buffer = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			buffer = bos.toByteArray();
		} finally {
			if(fis != null)
				fis.close();
			if(bos != null)
				bos.close();
		}
		return buffer;
	}

	public static void byte2file(byte[] buf, String filePath, String fileName) throws IOException {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {
				dir.mkdirs();
			}
			file = new File(filePath + File.separator + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(buf);
		} finally {
			if (bos != null) {
				bos.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
	
/*	public static String getFileMimeType(File file){
        MagicMatch match = null;
        try {
            match = Magic.getMagicMatch(file,true);
        } catch (MagicParseException e) {
            e.printStackTrace();
        } catch (MagicMatchNotFoundException e) {
            e.printStackTrace();
        } catch (MagicException e) {
            e.printStackTrace();
        } 
        return match.getMimeType();
    }*/
}
