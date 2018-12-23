package com.doctortech.fhq.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.doctortech.framework.bean.CommonRespon;
import com.doctortech.framework.consts.Const;


/**
 * 上传文件
 * @version
 */
public class FileUpload {

	public static final String UPLOAD_PATH="/upload/common/";
	public static final String UPLOAD_FILE_SUFFIX=".rar,.zip,.doc,.docx,.jpg,.png,.jpeg,.gif,.pdf,.xls,.xlsx,.txt,.ppt";
	public static final int UPLOAD_FILE_MAXSIZE=10;
	
	/**
	 * @param file 			//文件对象
	 * @param filePath		//上传路径
	 * @param fileName		//文件名
	 * @return  文件名
	 */
	public static String fileUp(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {
			if (file.getOriginalFilename().lastIndexOf(".") >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}
			copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName+extName;
	}
	
	/**
	 * 写文件到当前目录的upload目录中
	 * 
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	private static String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}
	
	/**
	 * 文件上传
	 * @param upload  //上传目录
	 * @param exts    //支持的文件格式
	 * @param request
	 * @param response
	 * @return
	 */
	public static  CommonRespon  uploadFile(String upload, String exts, HttpServletRequest request, HttpServletResponse response){
		CommonRespon respon=new CommonRespon(Const.CODE_ERROR,Const.CODE_ERROR_STR);
		String originName="";
		try {
		    upload=StringUtils.defaultString(upload, "/upload/common");
          	ServletContext application = request.getSession().getServletContext();
            String savePath = application.getRealPath("/") +upload;
            // 文件保存目录URL
            String saveUrl = request.getContextPath() +upload;
            // 定义允许上传的文件扩展名
            HashMap<String, String> extMap = new HashMap<String, String>();
            extMap.put("exts", exts);
            if (!ServletFileUpload.isMultipartContent(request)) {
            	respon.setMsg("请选择文件。");
               return respon;
            }
            // 检查目录
            File uploadDir = new File(savePath);
            if (!uploadDir.exists()) {
            	uploadDir.mkdirs();
            }
            if(request.getParameter("fileName")!=null){
            	originName=request.getParameter("fileName");
            }
        	MultipartHttpServletRequest mRequest;
	        try {
	            mRequest = (MultipartHttpServletRequest) request;
	            Iterator<String> itr = mRequest.getFileNames();
	            while (itr.hasNext()) {
	                MultipartFile mFile = mRequest.getFile(itr.next());
	                String fileName = mFile.getOriginalFilename();
	                if(StringUtils.isBlank(originName)){
	                	originName=fileName;
	                }
	                 if(mFile!=null && mFile.getSize()>1024*1024*50){
	                	 respon.setMsg("不允许上传50MB的文件");
	                     return respon;
					}
	                // 检查扩展名
	                 String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	                if(StringUtils.isBlank(fileExt)||fileExt.equals("blob")){
	                	fileExt="jpg";
	                }
	                if(StringUtils.isNotBlank(exts)){
	                    if (!Arrays.<String>asList(extMap.get("exts").split(",")).contains(fileExt)) {
	                    	respon.setMsg("文件格式不允许上传");
	                        return respon;
	                    }
	                }
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newFileName =
                            df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                    try {
                        File uploadedFile = new File(savePath, newFileName);
                        //mFile.transferTo(uploadedFile);
                        FileUtils.copyInputStreamToFile(mFile.getInputStream(), uploadedFile);
                    } catch (Exception e) {
                    	respon.setMsg("文件上传失败");
                        return respon;
                    }
                    String webPath=saveUrl+"/"+newFileName;
                    respon.setCode(Const.CODE_SUCCESS);
                    respon.setMsg(Const.CODE_SUCCESS_STR);
                    respon.setData(webPath);
                    respon.setReserveData(originName);
                    //respon.setReserveData(fileName);
                    break;
                    
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		} catch (Exception e) {
			e.printStackTrace();
			respon.setCode(Const.CODE_ERROR);
			respon.setMsg(Const.CODE_ERROR_STR);
			return respon;
		}
		return respon;
	}
	
	
	public static  CommonRespon  uploadFile(HttpServletRequest request, HttpServletResponse response){
		return FileUpload.uploadFileCustom("",null, request,response);
	}
	
	/**
	 * 文件上传
	 * @param exts    //支持的文件格式
	 * @param maxFileSize //最大能上传的文件大小 单位:MB
	 * @param request
	 * @param response
	 * @return
	 */
	public static  CommonRespon  uploadFileCustom(String exts,Integer maxFileSize, HttpServletRequest request, HttpServletResponse response){
		CommonRespon respon=new CommonRespon();
		try {
			if(StringUtils.isBlank(exts)){
				exts=UPLOAD_FILE_SUFFIX;
			}
			if(maxFileSize==null){
				maxFileSize=UPLOAD_FILE_MAXSIZE;
			}
			 Date todayDate=new Date();
			String today=DateUtil.formatDate(todayDate, "yyyyMMdd");
          	ServletContext application = request.getSession().getServletContext();
            String savePath = application.getRealPath("/") +UPLOAD_PATH+today;
            // 文件保存目录URL
            String saveUrl = request.getContextPath() +UPLOAD_PATH+today;
            File dir=new File(savePath);
            //目录不存在，创建文件目录，一天一个文件夹
    	   if(!dir.exists()&& !dir.isDirectory()) {
    		  dir.mkdirs();
    	   } 
      
            // 定义允许上传的文件扩展名
            HashMap<String, String> extMap = new HashMap<String, String>();
            extMap.put("exts", exts);//exts："ppt,doc,docx"
            if (!ServletFileUpload.isMultipartContent(request)) {
            	respon.setMsg("请选择文件。");
               return respon;
            }
            // 检查目录
            File uploadDir = new File(savePath);
            if (!uploadDir.exists()) {
            	uploadDir.mkdirs();
            }
        	MultipartHttpServletRequest mRequest;
	        try {
	            mRequest = (MultipartHttpServletRequest) request;
	            Iterator<String> itr = mRequest.getFileNames();
	            while (itr.hasNext()) {
	                MultipartFile mFile = mRequest.getFile(itr.next());
	                String fileName = mFile.getOriginalFilename();
	                fileName= new String (fileName.getBytes("ISO-8859-1"),"UTF-8");
	                 if(mFile!=null && mFile.getSize()>1024*1024*maxFileSize){
	                	 respon.setMsg("文件大小超过"+maxFileSize+"MB，不允许上传");
	                     return respon;
					}
	                // 检查扩展名
	                if(fileName.lastIndexOf(".")==-1){
	                	 respon.setMsg("不允许删除此类格式文件");
	                     return respon;
	                } 
	                String fileExt = fileName.substring(fileName.lastIndexOf(".") ).toLowerCase();
	                if(StringUtils.isBlank(fileExt)||fileExt.equals(".blob")){
	                	fileExt=".jpg";
	                }
	                if(StringUtils.isNotBlank(exts)){
	                    if (!Arrays.<String>asList(extMap.get("exts").split(",")).contains(fileExt)) {
	                    	respon.setMsg("文件格式不允许上传");
	                        return respon;
	                    }
	                }
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newFileName =df.format(new Date()) + "_" + new Random().nextInt(1000) + fileExt;
                    try {
                        File uploadedFile = new File(savePath, newFileName);
                        FileUtils.copyInputStreamToFile(mFile.getInputStream(), uploadedFile);
                    } catch (Exception e) {
                    	respon.setMsg("文件上传失败");
                        return respon;
                    }
                    String webPath=saveUrl+"/"+newFileName;
                    respon.setCode(Const.CODE_SUCCESS);
                    respon.setMsg(Const.CODE_SUCCESS_STR);
                    respon.setData(webPath);
                    respon.setReserveData(fileName);
                    break;
                    
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		} catch (Exception e) {
			e.printStackTrace();
			respon.setCode(Const.CODE_ERROR);
			respon.setMsg(Const.CODE_ERROR_STR);
			return respon;
		}
		return respon;
	}
	
	/**
	 * 删除文件
	 * @param request
	 * @param response
	 * @param path 文件路径
	 */
	public static  void  deleteUploadFile(HttpServletRequest request,String path){
      	ServletContext application = request.getSession().getServletContext();
        String rPath = application.getRealPath("/") +path;
        File f=new File(rPath);
        if(f.exists()&&f.isFile()){
        	f.delete();
        }
	}
	
}
