/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.magiccalculator;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Akshay
 */
public class DataResponse {
    @XmlElement
    private long result = 0;
    
    @XmlElement
    private String inExpression = "";
    
    @XmlElement
    private long offset = 0;
    
    @XmlElement
    private long actualResult = 0;
    
    public DataResponse(){}
    
    public DataResponse(long result, String expr,long offset,long actualResult){
        this.result = result;
        this.inExpression = expr;
        this.offset = offset;
        this.actualResult = actualResult;
    }
}
