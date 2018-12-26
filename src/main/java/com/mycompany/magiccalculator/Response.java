/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.magiccalculator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Akshay
 */
@XmlRootElement
public class Response {
    
    @XmlElement
    private int result = 0;
    
    @XmlElement
    private String inExpression = "";
    
    @XmlElement
    private int offset = 0;
    
    public void setResult(int result, String expr,int offset){
        this.result = result;
        this.inExpression = expr;
        this.offset = offset;
    }
}
