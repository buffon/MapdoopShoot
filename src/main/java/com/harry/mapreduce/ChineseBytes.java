package com.harry.mapreduce;

/**
 * Created with IntelliJ IDEA.
 * User: chenyehui
 * Date: 14-3-9
 * Time: 下午11:49
 * To change this template use File | Settings | File Templates.
 */
public class ChineseBytes {

    public static void main(String[] args) throws Exception{
        System.out.println(bytesToHexString("陈叶辉".getBytes("utf-8")));
        System.out.println(new String("陈叶辉".getBytes("gbk"),"gbk"));

    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
