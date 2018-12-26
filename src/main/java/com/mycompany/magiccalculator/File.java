/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.magiccalculator;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 *
 * @author Akshay
 */
public class File {
    private int reqCount = 0;
    
    public int getReqCount(){
        return this.reqCount;
    }
    
    public void setReqCount(){
        try{
            StringBuilder path = new StringBuilder();
            path = path.append(Paths.get(".").toAbsolutePath().normalize().toString());
            path = path.append("/count.txt");
            FileInputStream f = new FileInputStream(path.toString());
            DataInputStream d = new DataInputStream(f);
            this.reqCount = d.readInt();
        }catch(Exception e){
            
        }
    }
    
    public void incrReqCount(){
        try{
            StringBuilder path = new StringBuilder();
            path = path.append(Paths.get(".").toAbsolutePath().normalize().toString());
            path = path.append("/count.txt");
            FileOutputStream f = new FileOutputStream(path.toString());
            DataOutputStream d = new DataOutputStream(f);
            d.writeInt(this.reqCount+1);
        }catch(Exception e){
            
        }
    }
    
    public String getPath(){
        StringBuilder path = new StringBuilder();
        path = path.append(Paths.get(".").toAbsolutePath().normalize().toString());
        return path.toString();
    }
}
