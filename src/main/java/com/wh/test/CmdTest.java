package com.wh.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdTest  {
	
	public static void main(String[] args) {
		CmdTest cmd = new CmdTest();
		try {
			cmd.testCommand();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testCommand() throws IOException {
		//java 调 linux
//		String[] cmdL1 = {
//                "/bin/sh",
//                "-c",
//                "curl http://lyw-develop.win:8080/CWXT/userController/login?f_UserId=admin\\&f_PassWord=000000"  
//        };
//		String result = callCmd(cmdL1);
		String[] cmdL2 = {
                "cmd",
                "/c",
                "start http://lyw-develop.win:8080/CWXT/userController/login?f_UserId=admin^&f_PassWord=000000"  
        };
		
		String result = callCmd(cmdL2);
		System.out.println(result);
	}
	
	//封装方法执行操作系统命令
	public static String callCmd(String[] cmd) {
        String result = "";
        String line = "";
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(is);
            while ((line = br.readLine()) != null) {
                result += line;
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	
}
