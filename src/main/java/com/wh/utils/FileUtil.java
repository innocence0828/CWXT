package com.wh.utils;

import com.wh.entity.Result;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 创建文件工具类
 */
@Service
public class FileUtil {
    /**
     * @param request
     * @param文件 file
     * @param路径 image
     * @return
     */
    public Result createFile(HttpServletRequest request, MultipartFile file, String path)  {
        // TODO Auto-generated method stub
        Result result = null;
        try {
            // 上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);

            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //路径加文件名
           String filePath =DateUtils.getShortYMDHMS()+filename;
            // 将上传文件保存到一个目标文件当中
            file.transferTo(new File(path +File.separator +filePath));
            if(!filename.contains(".apk"))
            Thumbnails.of(path +File.separator +filePath).scale(0.3f).outputQuality(0.3f).toFile(path +File.separator +filePath);
            result = new Result(true,filePath);
        } catch (Exception e) {
            result = new Result(false,e.getMessage());
        }
        return result;

    }

    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStrs = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStrs[1]);

            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            return new BASE64DecodedMultipartFile(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件路径
     * @param path
     * @return
     */
    public  File[] getFileList(String path){
        File file=new File(path);
        File[] filelist=null;
        if(file.exists()){
            filelist=file.listFiles();
        }
        return filelist;
    }

    public  Boolean deleteFile(String path) {
        Boolean flag = false;
        File file = new File(path);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

}
