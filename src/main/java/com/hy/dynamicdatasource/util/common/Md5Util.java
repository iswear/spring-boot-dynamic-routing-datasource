package com.hy.dynamicdatasource.util.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/1/20.
 */
public class Md5Util {

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteArrayToHexString(byte b[])
    {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b)
    {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static byte[] MD5EncodeBytes(String origin, String charsetname)
            throws Md5UtilException
    {
        MessageDigest md;
        try
        {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new Md5UtilException(e);
        }
        if(null != charsetname)
        {
            try
            {
                return md.digest(origin.getBytes(charsetname));
            }
            catch (UnsupportedEncodingException e)
            {
                // TODO Auto-generated catch block
                throw new Md5UtilException(e);
            }
        }
        else
        {
            return md.digest(origin.getBytes());
        }
    }

    public static String MD5EncodeString(String origin, String charsetname)
            throws Md5UtilException
    {
        String resultString = new String(origin);
        MessageDigest md;
        try
        {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            throw new Md5UtilException(e);
        }
        if (charsetname == null || "".equals(charsetname))
        {
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        }
        else
        {
            try
            {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
            catch (UnsupportedEncodingException e)
            {
                // TODO Auto-generated catch block
                throw new Md5UtilException(e);
            }
        }
        return resultString;
    }


    public static class Md5UtilException extends Exception
    {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public Md5UtilException(Throwable e)
        {
            super(e);
        }

    }

}
