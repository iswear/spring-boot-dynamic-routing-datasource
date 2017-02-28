package com.hy.dynamicdatasource.util.common;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密工具类
 * @author <a href="mailto:zengdan@zjport.gov.cn">zengdan</a>
 * @version $Id$
 * @since 1.0
 */
public class AESUtil
{
    /**
     * 密钥算法
     */
    public static final String KEY_ALGORITHM = "AES";
    
    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     * Java 6支持PKCS5Padding填充方式
     * Bouncy Castle支持PKCS7Padding填充方式
     */
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    
    /**
     * 转换密钥
     *
     * @param key 二进制密钥
     * @return Key 密钥
     * @throws Exception
     */
    private static Key toKey(byte[] key)
    {
        
        // 实例化AES密钥材料
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        
        return secretKey;
    }
    
    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key 密钥
     * @return byte[] 解密数据
     * @throws AESUtilException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrypt(byte[] data, byte[] key)
        throws AESUtilException
    {
        
        // 还原密钥
        Key k = toKey(key);
        
        /*
         * 实例化
         * 使用PKCS7Padding填充方式，按如下方式实现
         * Cipher.getInstance(CIPHER_ALGORITHM, "BC");
         */
        Cipher cipher = null;
        try
        {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        }
        catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            throw new AESUtilException(e);
        }
        catch (NoSuchPaddingException e)
        {
            // TODO Auto-generated catch block
            throw new AESUtilException(e);
        }
        
        // 初始化，设置为解密模式
        try
        {
            cipher.init(Cipher.DECRYPT_MODE, k);
        }
        catch (InvalidKeyException e)
        {
            // TODO Auto-generated catch block
            throw new AESUtilException(e);
        }
        
        // 执行操作
        try
        {
            return cipher.doFinal(data);
        }
        catch (IllegalBlockSizeException e)
        {
            // TODO Auto-generated catch block
            throw new AESUtilException(e);
        }
        catch (BadPaddingException e)
        {
            // TODO Auto-generated catch block
            throw new AESUtilException(e);
        }
    }
    
    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key 密钥
     * @return byte[] 加密数据
     * @throws AESUtilException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encrypt(byte[] data, byte[] key)
        throws AESUtilException
    {
        
        // 还原密钥
        Key k = toKey(key);
        
        /*
         * 实例化
         * 使用PKCS7Padding填充方式，按如下方式实现
         * Cipher.getInstance(CIPHER_ALGORITHM, "BC");
         */
        Cipher cipher;
        try
        {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        }
        catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            throw new AESUtilException(e);
        }
        catch (NoSuchPaddingException e)
        {
            // TODO Auto-generated catch block
            throw new AESUtilException(e);
        }
        
        // 初始化，设置为加密模式
        try
        {
            cipher.init(Cipher.ENCRYPT_MODE, k);
        }
        catch (InvalidKeyException e)
        {
            // TODO Auto-generated catch block
            throw new AESUtilException(e);
        }
        
        // 执行操作
        try
        {
            return cipher.doFinal(data);
        }
        catch (IllegalBlockSizeException e)
        {
            // TODO Auto-generated catch block
            throw new AESUtilException(e);
        }
        catch (BadPaddingException e)
        {
            // TODO Auto-generated catch block
            throw new AESUtilException(e);
        }
    }
    
    /**
     * 生成密钥 <br>
     *
     * @return byte[] 二进制密钥
     * @throws AESUtilException
     * @throws Exception
     */
    public static byte[] initKey()
        throws AESUtilException
    {
        
        // 实例化
        KeyGenerator kg;
        try
        {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        }
        catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            throw new AESUtilException(e);
        }
        
        /*
         * AES 要求密钥长度为 128位、192位或 256位
         */
        kg.init(128);
        
        // 生成秘密密钥
        SecretKey secretKey = kg.generateKey();
        
        // 获得密钥的二进制编码形式
        return secretKey.getEncoded();
    }
    
    public static class AESUtilException extends Exception
    {
        
        public AESUtilException(Throwable e)
        {
            super(e);
        }
        
    }
    
}
