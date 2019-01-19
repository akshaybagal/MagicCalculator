/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.magiccalculator;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Akshay
 */
@Path("Calc/{expr}")
public class CalcResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CalcResource
     */
    public CalcResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.magiccalculator.CalcResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(
            @PathParam("expr") String expr) {
        
        Response r = new Response();
        Calculator c = new Calculator();
        
        c.setExpr(expr);
        c.calc();
        
        r.setResult(c.getMetaCode(),c.getMetaStatus(),c.getAns(),expr,c.getOffset(),c.getActualResult(),c.getApiHit());
        return r;
    }

    /**
     * PUT method for updating or creating an instance of CalcResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
