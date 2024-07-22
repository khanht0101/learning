/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Types;

import java.io.Serializable;

/**
 *
 * @author CONG HUY
 */
public class anyURI implements Serializable
{
    public anyURI() 
    {
        href = "";
    }
    public anyURI(String url) 
    {
        href = url;
    }
    public String href;
    
}
