package com.wh.service.web.imp;

import com.wh.service.web.BaVersionService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.concurrent.Callable;

/**
 * 下载文件多线程
 * @author Thinkpad-e470
 *
 */
public class DownLoadFile implements Callable<ResponseEntity<byte[]>>  {
	public String path;
    public  String typeStr;
    public HttpServletRequest request;
    public BaVersionService countService;
   
    public DownLoadFile(String path, String typeStr,
                        HttpServletRequest request, BaVersionService countService) {
		super();
		this.path = path;
		this.typeStr = typeStr;
		this.request = request;
		this.countService = countService;
	}
    int i=0;
	@Override
    public ResponseEntity<byte[]> call()throws Exception  {
    	i ++;
    	System.out.println("1ddddddd"+i);
    	//设置http协议头部
    	//设置文件名
        String fileName = "";
        HttpHeaders headers = new HttpHeaders();
    	if("App".equals(typeStr)){
    		  //下载对应url路径
    		  path = countService.downQuery(path);
    		  //截取字符串
    		  path =path.substring(path.indexOf("path=")+5, path.length());
    	}
    	 String name = path.substring(14,path.length());
	     fileName = new String(name.getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
        //头部设置文件类型
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //获取文件路径
        String targetDirectory = request.getServletContext().getRealPath("/images/")+File.separator + path;
        File file=new File(targetDirectory);  
        //返回文件字节数组
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    	
    }
    
  
}