package com.hy.dynamicdatasource.util.common;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class Base64Util
{
    
    public static String encodeBytes(byte[] srcdata)
    {
        return Base64.encodeBase64String(srcdata);
    }
    
    // 加密
    public static String encodeString(String srcStr, String srcEncode)
        throws Base64UtilException
    {
        if (srcEncode == null || srcEncode.equals(""))
        {
            return Base64.encodeBase64String(srcStr.getBytes());
        }
        else
        {
            try
            {
                return Base64.encodeBase64String(srcStr.getBytes(srcEncode));
            }
            catch (UnsupportedEncodingException e)
            {
                // TODO Auto-generated catch block
                throw new Base64UtilException(e);
            }
        }
    }
    
    // 解密
    public static String decodeString(String srcStr, String desEncode)
        throws Base64UtilException
    {
        byte[] strBytes = Base64.decodeBase64(srcStr);
        if (desEncode == null || desEncode.equals(""))
        {
            return new String(strBytes);
        }
        else
        {
            try
            {
                return new String(strBytes, desEncode);
            }
            catch (UnsupportedEncodingException e)
            {
                // TODO Auto-generated catch block
                throw new Base64UtilException(e);
            }
        }
    }
    
    public static class Base64UtilException extends Exception
    {
        
        public Base64UtilException(Throwable e)
        {
            super(e);
        }
        
    }
    
}
