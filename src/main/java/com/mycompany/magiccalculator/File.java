/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.magiccalculator;
import com.sun.media.jfxmedia.logging.Logger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
/**
 * 
 *
 * @author Akshay
 */
public class File {
    private int reqCount = 0;
    
    public int getReqCount(){
        return this.reqCount;
    }
    
    public synchronized void setReqCount() throws IOException{
        FileInputStream f = null;
        DataInputStream d = null;
        try{
            StringBuilder path = new StringBuilder();
            path = path.append(Paths.get(".").toAbsolutePath().normalize().toString());
            path = path.append("/count.txt");
            f = new FileInputStream(path.toString());
            d = new DataInputStream(f);
            this.reqCount = d.readInt();
            d.close();
            f.close();
        }catch(FileNotFoundException fne){
                Logger.logMsg(1, fne.getMessage());
        }catch(Exception e){
            Logger.logMsg(1, e.getMessage());
        }
        finally{
            if(f != null){
                f.close();
            }
            if(d!=null){
                d.close();
            }
        }
    }
    
    public synchronized void incrReqCount() throws IOException {
            FileOutputStream f = null;
            DataOutputStream d = null;
            try{
            StringBuilder path = new StringBuilder();
            path = path.append(Paths.get(".").toAbsolutePath().normalize().toString());
            path = path.append("/count.txt");
            f = new FileOutputStream(path.toString());
            d = new DataOutputStream(f);
            d.writeInt(this.reqCount+1);
            }catch(FileNotFoundException fne){
                    Logger.logMsg(1,fne.getMessage());
            }catch(Exception e){
                Logger.logMsg(1, e.getMessage());
            }
            finally{
                
                if(f != null)
                    f.close();
                if(d != null)
                    d.close();
            
            }
    }
    
    public String getPath(){
        StringBuilder path = new StringBuilder();
        path = path.append(Paths.get(".").toAbsolutePath().normalize().toString());
        return path.toString();
    }
}
