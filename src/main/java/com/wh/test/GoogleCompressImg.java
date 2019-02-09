package com.wh.test;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class GoogleCompressImg {

    private static final String basepath="D:\\photo";

    public static void main(String[] args) {
        File[] files=getFileList(basepath);
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
            googleCompress(file.getAbsolutePath(), "D:\\photo\\"+file.getName()+"_test"+file.getName().substring(file.getName().indexOf(".")));
        }
    }
    public static void googleCompress(String olderImg,String newImg){
        try {
            /**
             * scale图片长宽  outputQuality图片质量
             */
            //Thumbnails.of(olderImg).scale(0.5f).outputQuality(0.5f).toFile(newImg);
            /**
             * 指定大小
             */
            Thumbnails.of(olderImg).size(300, 300).toFile(newImg);
            /**
             * keepAspectRatio(false)按比例
             */
            //Thumbnails.of(olderImg).size(120, 120).keepAspectRatio(false).toFile(newImg);
            /**
             * rotate(角度),正数：顺时针 负数：逆时针
             */
            // Thumbnails.of(olderImg).size(1280, 1024).rotate(90).toFile(newImg);
             /**
              *  watermark(位置，水印图，透明度)
              */
//           Thumbnails.of(olderImg).size(1280, 1024).watermark(Positions.CENTER, ImageIO.read(new File(olderImg)), 0.5f).outputQuality(0.8f).toFile(newImg);
          /**
           * 裁剪
           */
        //  Thumbnails.of(olderImg).sourceRegion(Positions.CENTER, 400, 400).size(200, 200).keepAspectRatio(false).toFile(newImg);
        /**
         * 转换图像格式
         */
            // Thumbnails.of(olderImg).size(1280, 1024).outputFormat("png").toFile(newImg);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static File[] getFileList(String path){
        File file=new File(path); 
        File[] filelist=null;
        if(file.exists()){ 
           filelist=file.listFiles(); 
        }
        return filelist;
    }

}
