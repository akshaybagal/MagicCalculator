/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.magiccalculator;

/**
 *
 * @author Akshay
 */
public class Calculator {
    private String expr = "";
    private int ans = 0;
    private int offset = 0;
    
    public int getAns(){
        return this.ans;
    }
    public int getOffset(){
        return this.offset;
    }
    public void setExpr(String expr){
        this.expr = expr;
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
                int num = Integer.parseInt(s.toString());
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
            ptr = new Node(Integer.parseInt(sl.toString()),'a');
            head = ptr;
        }
        else
            ptr.next = new Node(Integer.parseInt(sl.toString()),'a');
    return head;
    }
    
    
    public int calc(){
        File fl = new File();
        fl.setReqCount();
        
        ans = isValid(expr);
        if(ans == 1){
            Node head = prepareLinkedList(expr);
            solveMultiplyDivision(head);
            solveAdditionSubtraction(head);
            if(head != null && ans != -1)
                ans = head.num+fl.getReqCount();
        }
        offset = fl.getReqCount();
        fl.incrReqCount();
        return ans;
    }
}
