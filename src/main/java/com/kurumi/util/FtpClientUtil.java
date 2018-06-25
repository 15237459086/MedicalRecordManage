package com.kurumi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClientUtil {
	/*private static final Logger log = LoggerFactory.getLogger(FtpClientUtil.class);
	*/
	/** 
	 * Description: 向FTP服务器上传文件 
	 * @Version1.0 Jul 27, 2008 4:31:09 PM by 崔红保（cuihongbao@d-heaven.com）创建 
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param path FTP服务器保存目录 
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false 
	 */  
	public static boolean uploadFile(String url,int port,String username, String password, String path, String filename, InputStream input) {  
	    boolean success = false;  
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.enterLocalPassiveMode();
	        ftp.connect(url, port);//连接FTP服务器  
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(username, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return success;  
	        }  
	        ftp.changeWorkingDirectory(path);  
	        ftp.storeFile(filename, input);           
	          
	        input.close();  
	        ftp.logout();  
	        success = true;  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return success;  
	}
	
	/** 
	 * Description: 从FTP服务器下载文件 
	 * @Version1.0 Jul 27, 2008 5:32:36 PM by 崔红保（cuihongbao@d-heaven.com）创建 
	 * @param host FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param userName FTP登录账号 
	 * @param password FTP登录密码 
	 * @param remotePath FTP服务器上的相对路径 
	 * @param fileName 要下载的文件名 
	 * @param localPath 下载后保存到本地的路径 
	 * @return -1/0/1 => 异常/无资源下载/资源下载成功
	 */  
	public static int downFile(String host, int port,String userName, String password, String remotePath,String fileName,String localPath) {  
	    int result = 0;  
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.enterLocalPassiveMode();
	        ftp.connect(host, port);  
	        //如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器  
	        ftp.login(userName, password);//登录  
	        reply = ftp.getReplyCode();  
	        
	        if (!FTPReply.isPositiveCompletion(reply)) {
	            result = 0;  
	            return result;  
	        }  
	        
	        boolean isChange = ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录  
	        
	        if (isChange) {
	        	boolean success = false;
	            FTPFile[] fs = ftp.listFiles();  
		        for(FTPFile ff:fs){  
		        	
		            if(ff.isFile() && ff.getName().equals(fileName)){  
		                File localFile = new File(localPath+"/"+ff.getName());  
		                OutputStream is = new FileOutputStream(localFile);   
		                ftp.retrieveFile(ff.getName(), is);  
		                is.close();
		                success = true;
		            }  
		        }
		        if(success){
		        	result = 1; 
		        }
		        
	        }else{
	        	result = 0; 
	        }
	    } catch (IOException e) {  
	        e.printStackTrace();  
	        result = -1; 
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return result;  
	}
	
	/** 
	 * Description: 从FTP服务器下载文件 
	 * @Version1.0 Jul 27, 2008 5:32:36 PM by 崔红保（cuihongbao@d-heaven.com）创建 
	 * @param host FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param userName FTP登录账号 
	 * @param password FTP登录密码 
	 * @param remotePath FTP服务器上的相对路径 
	 * @param fileName 要下载的文件名 
	 * @param localPath 下载后保存到本地的路径 
	 * @return 
	 */  
	public static List<String> downSonFiles(String host, int port,String userName, String password, String remotePath,String localPath) {  
	    List<String> sonFileList = new ArrayList<String>();
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply; 
	        ftp.enterLocalPassiveMode();
	        ftp.connect(host, port);  
	        //如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器  
	        ftp.login(userName, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	        	sonFileList = null;
	            return sonFileList;  
	            
	        }
	        boolean isChange = ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录 
	        System.out.println(remotePath+"----------"+isChange);
	        
	        if (isChange) {
	            FTPFile[] fs = ftp.listFiles(); 
		        for(FTPFile ff:fs){  
		        	if(ff.isFile()){
		        		File localFile = new File(localPath+"/"+ff.getName());  
		                  
		                OutputStream is = new FileOutputStream(localFile);   
		                ftp.retrieveFile(ff.getName(), is);  
		                sonFileList.add(ff.getName());
		                is.close();
		        	}else{
		        		
		        	}
		        }  
	        }
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            
	            }  
	        }  
	    }  
	    return sonFileList;  
	}
	
	
	/** 
	 * Description: 从FTP服务器下载文件 
	 * @Version1.0 Jul 27, 2008 5:32:36 PM by 崔红保（cuihongbao@d-heaven.com）创建 
	 * @param host FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param userName FTP登录账号 
	 * @param password FTP登录密码 
	 * @param remotePath FTP服务器上的相对路径 
	 * @param fileName 要下载的文件名 
	 * @param localPath 下载后保存到本地的路径 
	 * @return 
	 */  
	public static List<String> downChildFiles(String host, int port,String userName, String password, String remotePath,String localPath) {  
	    List<String> sonFileList = new ArrayList<String>();
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply; 
	        ftp.enterLocalPassiveMode();
	        ftp.connect(host, port);  
	        //如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器  
	        ftp.login(userName, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	        	sonFileList = null;
	            return sonFileList;  
	            
	        }
	        boolean isChange = ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录 
	        System.out.println(remotePath+"----------"+isChange);
	        
	        if (isChange) {
	            FTPFile[] fs = ftp.listFiles(); 
		        for(FTPFile ff:fs){  
		        	if(ff.isFile()){
		        		File localFile = new File(localPath+"/"+ff.getName());  
		                  
		                OutputStream is = new FileOutputStream(localFile);   
		                ftp.retrieveFile(ff.getName(), is);  
		                sonFileList.add(ff.getName());
		                is.close();
		        	}else{
		        		
		        	}
		        }  
	        }
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            
	            }  
	        }  
	    }  
	    return sonFileList;  
	}
	public static void main(String[] args) {
		//downFile("192.168.37.1", 21, "FTP_ADMIN", "123456", "phr_manage/exception/", "F:\\305data");
	}

}
