package com.cy.dp.adapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("d:/password.txt");
        InputStreamReader is = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(is);
        String line = br.readLine();
        while(null != line && !"".equals(line)){
            System.out.println(line);
            line = br.readLine();
        }

        br.close();
    }
}
