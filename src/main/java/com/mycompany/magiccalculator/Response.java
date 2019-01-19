/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.magiccalculator;

import java.util.HashMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Akshay
 */
@XmlRootElement
public class Response {
    @XmlElement
    private MetaResponse meta;
    
    @XmlElement
    private DataResponse data;
    
    public void setResult(int metaCode, String metaStatus,long result, String expr,long offset,long actualResult,long apiHit){
        meta = new MetaResponse(metaCode, metaStatus);
        data = new DataResponse(result,expr,offset,actualResult,apiHit);
    }
}
