package com.rjgc01;

import org.junit.Test;

public class mainTest {

    String s1="e:\\TestText\\orig.txt";
    String s2="e:\\TestText\\orig_0.8_add.txt";
    String s3="e:\\TestText\\output.txt";
    String s4="e:\\TestText\\orig_0.8_del.txt";
    String s5="e:\\TestText\\orig_0.8_dis_1.txt";
    String s6="e:\\TestText\\orig_0.8_dis_10.txt";
    String s7="e:\\TestText\\orig_0.8_dis_15.txt";

    //测试代码的正确运行
    @Test
    public void main() {
        main.ReadFile(s1,s2,s3);
    }

    //测试代码为空
    @Test
    public void testIsNull()
    {
        main.ReadFile(null,s2,s3);
    }

    //写入的路径错误
    @Test
    public void testRouteMistakeOut() {
        main.ReadFile(s1,s2,s3+"abc");
    }

    @Test
    //读入的路径错误
    public void testRouteMistakeIn() {
        main.ReadFile(s1+"abc",s2,s3);
    }

    //两份写入文件相同
    @Test
    public void testTheSameReadRoute() {
        main.ReadFile(s2,s2,s3);
    }

    //orig_0.8_add.txt
    @Test
    public void testAdd() {
        main.ReadFile(s1,s2,s3);
    }

    //orig_0.8_del.txt
    @Test
    public void testDel() {
        main.ReadFile(s1,s4,s3);
    }

    //orig_0.8_dis_1.txt
    @Test
    public void testDis1() {
        main.ReadFile(s1,s5,s3);
    }

    //orig_0.8_dis_10.txt
    @Test
    public void testDis10() {
        main.ReadFile(s1,s6,s3);
    }

    //orig_0.8_dis_15.txt
    @Test
    public void testDis15() {
        main.ReadFile(s1,s7,s3);
    }
}