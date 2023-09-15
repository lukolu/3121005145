package com.rjgc01;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class SimHash {
    public static String getHash(String s){


        try {
            //使用MD5获得hash值
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            return new BigInteger(1,messageDigest.digest(s.getBytes("UTF-8"))).toString(2);
        } catch (Exception e) {
            e.printStackTrace();
            return s;
        }
    }

    //获得SimHash
    //map存放的是词，以及频数
    public static String getSimHash(Map<String,Integer> map) {
        int []temp=new int[128];
        for (String s : map.keySet()) {
            String hash = getHash(s);
            //如果hash码不够128位，补齐0
            if (hash.length()<128)
            {
                int bit=128-hash.length();
                for (int i=0;i<bit;i++)
                    hash+="0";
            }
            //加权，把频数乘以hash,同时合并
            for (int j=0;j<temp.length;j++)
            {
                if (hash.charAt(j)=='1')
                {
                    temp[j] += map.get(s);
                }
                else
                    temp[j]-=map.get(s);
            }
        }

        String str="";
        for (int i=0;i< temp.length;i++) {
            if (temp[i]>0) str+="1"; else str+="0";
        }

        return str;


    }

    public static double Construct(StringReader reader1,StringReader reader2)  {
        IKSegmenter ikSegmenter1 = new IKSegmenter(reader1, true);
        IKSegmenter ikSegmenter2 = new IKSegmenter(reader2, true);

        Map<String, Integer> frequency = null;
        Map<String, Integer> frequency1 =null;
        try {
            frequency = getFrequency(ikSegmenter1);
            frequency1 = getFrequency(ikSegmenter2);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String simHash = getSimHash(frequency);
        String simHash1 = getSimHash(frequency1);

        double similarity = HammingDistance.getSimilarity(simHash,simHash1);

        return similarity;
    }

    public static Map<String ,Integer> getFrequency(IKSegmenter ikSegmenter) throws IOException {
        Map<String, Integer> mp = new HashMap<>();

        Lexeme next = ikSegmenter.next();
        while (next != null) {
            String lexemeText = next.getLexemeText();
            if (!(mp.containsKey(lexemeText)))
                mp.put(lexemeText, 1);
            else mp.put(lexemeText, mp.get(lexemeText) + 1);

            next = ikSegmenter.next();
        }

        return mp;
    }
}
