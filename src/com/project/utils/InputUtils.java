package com.project.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 获取键盘输入内容
 */
public class InputUtils {

    private InputUtils(){}

    /**
     * 获取键盘输入整型数字
     * @return
     */
    public static int getInputInt(String prompt){
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        while (scanner.hasNext()){
            String result = scanner.next();
            if (result.matches("\\d+")){
                //scanner.close();
                return Integer.parseInt(result);
            } else {
                System.out.print(prompt);
            }
        }

        return 0;
    }

    /**
     * 获取键盘输入字符串
     * @return
     */
    public static String getInputString(String prompt){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(prompt);
            String result = br.readLine().trim();
            while (result.equals("")){
                System.out.print("输入的内容不能为空，请重写输入：");
                result = br.readLine().trim();
            }
            return result;
        } catch (IOException e){
            e.printStackTrace();
        } finally {
//            try {
//                if (br != null){
//                    br.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return "";
    }

}
