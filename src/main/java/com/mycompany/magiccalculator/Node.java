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
public class Node {
    public int num = 0;
    public char opr = 'a';
    public Node next = null;
    
    public Node(int num, char opr){
        this.num = num;
        this.opr = opr;
    }
}
