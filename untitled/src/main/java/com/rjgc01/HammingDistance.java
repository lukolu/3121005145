package com.rjgc01;

public class HammingDistance {
    public static int getHammingDistance(String simHash1,String simHash2) {
        int distance=0;
        for (int i=0;i<simHash1.length();i++)
        {
            if (simHash1.charAt(i)!=simHash2.charAt(i))
                distance++;
        }

        return distance;
    }

    public static double getSimilarity(String simHash1,String simHash2) {
        int hammingDistance = HammingDistance.getHammingDistance(simHash1, simHash2);
        return 0.01*(100-hammingDistance*100/128);
    }
}
