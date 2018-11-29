package com.ampleexchange.api.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ampleexchange.api.common.MyConstants;

public class FileHelper {

  public static void renameFile(String oldName,String newName){ 
      if(!oldName.equals(newName)){ 
          File oldfile=new File(oldName); 
          File newfile=new File(newName); 
          if(!oldfile.exists()){
              return;
          }
          if(newfile.exists()) 
              System.out.println(newName +" already exists！"); 
          else{ 
              oldfile.renameTo(newfile); 
          } 
      }else{
          System.out.println("The new file name is same as the old one...");
      }
  }
  
  public static void renameFile(String path,String oldname,String newname){
	  renameFile(path+"/"+oldname, path+"/"+newname);
  }

  public static String uploadFiles(HttpServletRequest request, String userId) {
		File directory = new File(MyConstants.FILEPATH + userId);
		if (!directory.exists() && !directory.isDirectory()) {
			directory.mkdir();
		}
		StringBuffer retVal = new StringBuffer();
		try {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> files = mRequest.getFiles("files");
			for (MultipartFile file : files) {
				String filename = file.getOriginalFilename();						
				if (filename == null || filename.trim().equals("")) {
					continue;
				}
				String extension = filename.substring(filename
						.lastIndexOf("."));
				filename = UUID.randomUUID() + extension;
				File dest = new File(MyConstants.FILEPATH + userId
						+ "\\" + filename);
				file.transferTo(dest);
				retVal.append(filename).append("|");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retVal.toString();
	}
  
  public static String downloadFile(HttpServletRequest request, String savePath, String fileName, HttpServletResponse response){
  	String retVal = "Succeeded";
    if (fileName != null) {
    	String fileUrl = savePath+fileName; // "D:\\temp\\downloads\\"
       /* String realPath = request.getServletContext().getRealPath(
                "//WEB-INF//");*/
        /*File file = new File(realPath, fileName);*/
        File file = new File(fileUrl);
        if (file.exists()) {
            response.setContentType("application/force-download"); 
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
        	retVal = fileName + " doesn't exist!";        	
        }
    }
    return retVal;  	
  }

  public static String getCurrentPageNameFromMultipart(HttpServletRequest request, String fieldName) {
	String retVal = "";
	try{
	  DiskFileItemFactory factory = new DiskFileItemFactory();
	  ServletFileUpload upload = new ServletFileUpload(factory);
	  upload.setHeaderEncoding("UTF-8"); 
	  if(ServletFileUpload.isMultipartContent(request)){
		List<FileItem> list = upload.parseRequest(request);
		for(FileItem item : list){
		  if(item.isFormField()){
			if (fieldName.equals(item.getFieldName())){
			  retVal = item.getString("UTF-8");
			  return retVal.substring(0, retVal.lastIndexOf("/"));
			}
		  }
		}
	  }
	}catch (Exception e) {
		e.printStackTrace();	 
	}
	return retVal;
  }

  public static boolean deleteFile(String sPath) {  
    boolean retVal = false;  
    File file = new File(sPath);  
    if (file.isFile() && file.exists()) {  
        file.delete();  
        retVal = true;  
    }  
    return retVal;  
  } 
}
