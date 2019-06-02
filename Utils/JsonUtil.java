package com.jiyun.IOutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

public class JsonUtil {
	public static String getFile(File file){
		StringBuffer str = new StringBuffer();
		FileInputStream f = null;
		try {
			f = new FileInputStream(file);
			byte[] by = new byte[1024];
			int leng = 0;
			while((leng = f.read(by)) != -1){
				str.append(new String(by, 0, leng));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(f != null){
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return str.toString();
	}
}
