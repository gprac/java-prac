
package com.lym.logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	public static Logger logger = null;
	private String logpath;
	File logfile;
	public synchronized static Logger getInstance(){
		if(logger == null){
			logger = new Logger();
		}
		return logger;
	}
	public synchronized void setLogpath(String path){
		logpath = path;
		File logf = new File(path);
		if(logf.exists()){
			logfile = logf;
			return ;
		}else{
			try {
				logf.createNewFile();
				logfile = logf;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void debug(String info){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
		    FileOutputStream out=new FileOutputStream(logfile,true); //如果追加方式用true        
		    StringBuffer sb=new StringBuffer();
		    sb.append("-----------"+sdf.format(new Date())+"------------:");
		    sb.append(info+"\n");
			out.write(sb.toString().getBytes("utf-8"));
			out.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//注意需要转换对应的字符集
	}
	
}
