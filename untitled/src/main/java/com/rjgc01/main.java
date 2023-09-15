package com.rjgc01;

import java.io.*;
import java.sql.Time;

public class main {
    public static void main(String[] args) {

        long l = System.currentTimeMillis();

        if (args[0].equals(args[2]) || args[1].equals(args[2]))
        {
            System.out.println("写入路径不能和读入路径相同");
            System.exit(1);
        }

        String s1=args[0];
        String s2=args[1];
        String s3=args[2];

        if (s1==null || s2==null || s3==null )
        {
            System.out.println("文件不能为空");
            System.exit(1);
        }
        else {
            ReadFile(s1,s2,s3);
        }

        long l1 = System.currentTimeMillis();

        System.out.println("Analysis Time is"+(l1-l)+"ms");
    }
    //读取命令行的前两个文件，分别是原文路径和抄袭版论文路径
    public static void ReadFile(String s1,String s2,String s3) {

            File file = new File(s1);
            File file1 = new File(s2);
            if (!(file.exists() && file1.exists()))
            {
                System.out.println("读入的文件路径有误！");
                System.exit(1);
            }
            File file2 = new File(s3);
            if (!file2.exists())
            {
                System.out.println("写入的文件路径有误");
                System.exit(1);
            }
        StringReader stringReader = new StringReader(s1);
            StringReader stringReader1 = new StringReader(s2);
            if (file.length()!=0 && file1.length()!=0)
            {
                    double construct = SimHash.Construct(stringReader, stringReader1);
                    writeFile(s3,s2,String.valueOf(construct));
            }

    }

    public static void writeFile(String s3,String s2,String similar) {
        FileWriter fileWriter=null;
        try {
            fileWriter = new FileWriter(s3,true);
            fileWriter.write(s2+"\t与原文相似度为："+similar+"\n");
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("文件写入错误");
            System.exit(1);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
