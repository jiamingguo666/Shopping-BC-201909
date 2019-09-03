package pers.guojiaming.shopping.util;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.*;
/**
 * @Description: 将文件转化为二进制流
 * @Author: jiaming.guo
 * @Date: 2019/8/13 9:34
 */
public class Upload {
    /**
     *
     * @param filestream  文件流
     * @param savaPath 文件保存的路径
     * @param fileName  文件名
     */
    public static void uploadFile(InputStream filestream, File savaPath,String fileName){
        //创建磁盘文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建核心上传对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        //创建一个文件输出流，只保留文件名部分
        fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
        String realSavePath = savaPath+"\\"+ fileName;
        //创建一个输出流
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(realSavePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //创建一个缓冲区
        byte[] buffer = new byte[1024];
        //判断输入流的数据是否已读完的标记
        int len = 0;
        //循环将输入流读入到缓冲区
        try {
            while((len = filestream.read(buffer))>0){
                out.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("这才是真正的目录："+realSavePath);
        //关闭输入流
        try {
            filestream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
