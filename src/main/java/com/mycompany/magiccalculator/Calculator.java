/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.magiccalculator;

import com.sun.media.jfxmedia.logging.Logger;
import java.io.IOException;

/**
 *
 * @author Akshay
 */
public class Calculator {
    
    private int metaCode = 0;
    private String metaStatus = "";
    
    private String expr = "";
    private long ans = 0;
    private long offset = 0;
    private long actualResult = 0;
    
    public int getMetaCode(){
        return this.metaCode;
    }
    
    public String getMetaStatus(){
            return this.metaStatus;
    }
    
    public String getExpr(){
        return this.expr;
    }
    
    public long getAns(){
        return this.ans;
    }
    public long getOffset(){
        return this.offset;
    }
    public long getActualResult(){
        return this.actualResult;
    }
    public void setExpr(String expr){
        this.expr = expr;
    }
    
    public void setAns(int ans){
        this.ans = ans;
    }
    
    public void setOffset(int offset){
        this.offset = offset;
    }
    
    public int isValid(String expr){
        int prev = 0;
        for(int i = 0 ; i < expr.length();i++){
            if(expr.charAt(i) == '+' || expr.charAt(i) == '-' || expr.charAt(i) == '*' || expr.charAt(i) == '/'){
                if(prev == 0){
                    prev = 1;
                }
                else{
                    return -1;
                }
            }
            else if(expr.charAt(i) < '0' || expr.charAt(i) > '9')
                   return -1;
            else{
                prev = 0;
            }
        }
        if(prev == 1)
               return -1;
        return 1;
    }
    
    private void solveMultiplyDivision(Node head){
        if(head != null){
        Node ptr1 = head;
        Node ptr2 = head.next;
        while(ptr2 != null && ptr1 != null){
            if(ptr2.opr == '*'){
                ptr1.num = ptr1.num * ptr2.next.num;
                ptr1.next = ptr2.next.next;
                ptr2 = ptr1.next;
            }
            else if(ptr2.opr == '/'){
                if(ptr2.next.num == 0)
                    ans = -1;
                else
                    ptr1.num = ptr1.num / ptr2.next.num;
                
                ptr1.next = ptr2.next.next;
                ptr2 = ptr1.next;
            }
            else{
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            }
        }   
        }     
    }
    
    private void solveAdditionSubtraction(Node head){
        if(head != null){
        Node ptr1 = head;
        Node ptr2 = head.next;
        while(ptr2 != null && ptr1 != null){
            if(ptr2.opr == '+'){
                ptr1.num = ptr1.num + ptr2.next.num;
                ptr1.next = ptr2.next.next;
                ptr2 = ptr1.next;
            }
            else if(ptr2.opr == '-'){
                ptr1.num = ptr1.num - ptr2.next.num;
                ptr1.next = ptr2.next.next;
                ptr2 = ptr1.next;
            }
            else{
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            }
        }
        }
    }
    
    private Node prepareLinkedList(String expr){
        int start = 0;
        Node ptr = null;
        Node head = null;
        for(int i = 0; i < expr.length(); i++){
            if(expr.charAt(i) == '+' || expr.charAt(i) == '-' || expr.charAt(i) == '*' || expr.charAt(i) == '/'){
                StringBuilder s = new StringBuilder();
                s.append("0");
                s.append(expr.substring(start, i));
                start = i+1;
                long num = Long.parseLong(s.toString());
                if(head == null){
                    ptr = new Node(num,'a');
                    head = ptr;
                }
                else{
                    ptr.next = new Node(num,'a');
                    ptr = ptr.next;
                }
                ptr.next = new Node(0,expr.charAt(i));
                ptr = ptr.next;
            }
        }
        StringBuilder sl = new StringBuilder();
        sl.append("0");
        sl.append(expr.substring(start, expr.length()));
        if(head == null){
            ptr = new Node(Long.parseLong(sl.toString()),'a');
            head = ptr;
        }
        else
            ptr.next = new Node(Long.parseLong(sl.toString()),'a');
    return head;
    }
    
    private int calcOffset(int count){
        int rank = 0;
        if((count & (count-1)) == 0)
                rank += 8;
        
        if((count & 1) > 0)
                rank -= 8;
        
        if((count&1) == 0)
                rank += 4;
        
        if((count%3) == 0)
                rank += 3;
        
        if((count%5) == 0)
                rank -= 5;
        
        return rank;
    }
    
    public void calc(){
        try{
            File fl = File.getInstance();
            fl.setReqCount();
            int count = 1;
            this.ans = isValid(this.expr);
            if(this.ans == 1){
                Node head = prepareLinkedList(expr);
                solveMultiplyDivision(head);
                solveAdditionSubtraction(head);
                if(head != null && ans != -1){
                    this.ans = head.num;
                    this.actualResult = this.ans;
                    count = fl.getReqCount();
                    this.offset = calcOffset(count);
                    fl.incrReqCount();
                    this.ans += this.offset;
                    this.metaCode = 200;
                    this.metaStatus = "OK";
                }
                else
                {
                    this.metaCode = 500;
                    this.metaStatus = "Error";
                }
            }
            else
            {
                this.metaCode = 400;
                this.metaStatus = "Invalid Expression";
            }
            }catch(NullPointerException npe){
                    this.metaCode = 500;
                    this.metaStatus = "Error";
                    Logger.logMsg(1, npe.getMessage());
            }catch(IOException ioe){
                    this.metaCode = 500;
                    this.metaStatus = "Error";
                    Logger.logMsg(1, ioe.getMessage());
            }catch(NumberFormatException nfe){
                    this.metaCode = 500;
                    this.metaStatus = "Error";
                    Logger.logMsg(1,nfe.getMessage());
            }catch(Exception e){
                    this.metaCode = 500;
                    this.metaStatus = "Error";
                    Logger.logMsg(1, e.getMessage());
        }
    }
}
