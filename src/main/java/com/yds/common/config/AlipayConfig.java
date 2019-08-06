package com.yds.common.config;

import java.io.FileWriter;

public class AlipayConfig {
    public static String app_id= "2016100100639257";
    public static String merchant_private_key="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDL4KCIy6eRt1cvU7ai7KGJkelNUvG1lFp9wPrNNapXr+iioPThqLhUzqAgyeIlWvUgotA1iDCMXX0m4D9IioPtDvuCvzDgaggz7cGWEOSYxZBi5vSMicRgHZyBU8oO/d4/m1v6ByU3KHTcF1DV8uAvD50KcukCK0dRFd/sxvfT0c6mCTI/KwotJr0+SAKNcrznP+0iN7ivip9ixdd1lQRIEZ/YJs4jLFE3/xjmRcDr42U7DY+w3fHfqKUhrc22XjzRjqHbWKJE5Y2xzjyNQxaeXelriANDDLNDFRg6KcddmRYi0h98vk3NmeBpqVuttsK7wAHlITBhnVKfD84O7Tw5AgMBAAECggEAHcFqDK0UhtroWZrxM/QvW2G1WDi72FGPp3zr3dlwRB4DaQDu3KSASrhhfIeafvM4hYvdsYJv+3yFkI/4DIFS4d1m7d0zp/0xt0qDD4DlrDphaV3l4UEWLSUFO4bcHbYFoujs0qAKWXOJIrowbRlmiNfRhr3grhVc8kOsC+W6M+rPdOuTZ1StYeOeCWkVU8oAPWSKUHEMPqvZ8ITqiGvQhGDwtrMUv4JmKAJPWzthlT0H1KLy2R+vsfjULRGNFcvhqsfIzv9QXYO6Dfoz8LZRRwwEkdgA8grQ27H5n7/OUaIfnmhghrlI0FcTNJOhGNcpFMsVL1LX3g5kzP4wXjdmJQKBgQDq3N2ku1jU40sUF/11423r7hHTJndKCLoMK1dnO9VsipLQzIoh/cMPTGJmkeDw3zy4GHIoCct/BzknearMrHHoNb6k8yJSrbLvPIy/gOWEhp62ktlMNePcY+m/bRpVcgqn3srcUu9w7jebv3MY/iaflyzL/bMScOHCsewAyfOvxwKBgQDeOd+dLKyxPs3uwzvIWhCy6BVn697s02bCeOaODwX7/nCfCjnLGLiRo98WwukPTbLTT1YE3SX276chNyCgwitLW3WJKFYETrDgoI2wQCW9oOEDGksGHJvf7mA5d6u/EdgbHyySz54cVzXaC6NjFbBBp8Tb27TnPKfk8iv7Zm+z/wKBgQCSKsEaa6p+W7L3H3MT8mYPxkt7oN3uyn2XY0XzLq7F7XwvzIxPWVITbFN1SGa5IgX1AOnS9z2ZohI0+iiNVulRPlmW6OY8lHPfJkzmRj8ReoK+V6g7IPLdz6TDCRCxitcM4MBZ3Je+LdOX78+fB1VcT0aG8i6HVI98jyS03Pr3MwKBgQCLN9R4IgG6NK9MkjDErm/htPWcGi+ZTEMHODiv7IeCO/+QExbrvnv6eaAMIWViefm62ev4thbPoYmhjGi7bYkz94MlvwhMQIQc5Vh+f2FlcAEnMXSoyol4e1z7A5ar5+Dr9Na2zqyt8pDBHiNosrCuIQ158yVc7Cn+ixy0rZLzPwKBgCw6mqQE7N5Ds+gaEmSMl6xAlbgjbbN+NO0rYCjpM+N2KHIWgirAZln6iNV8ylAWiVMRFiAlFX3kncxNTzYi8k7z5vxXwVY4dS03KC/tPJo6gjL6kxdQc4Y+vJ7TZI10PE5SIFm8PyGEkAtwDrH7R2iTsUvp5FxC8HoSsP81A7/6";
    public static String alipay_public_key ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
    public static String notify_url = "";
    public static String return_url="";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // 日志地址
    public static String log_path = "D:/logs/";

    public static void logResult(String sWord){
        FileWriter writer = null;
        try{
            writer = new FileWriter(log_path + "alipay_log_"+System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (writer!=null){
               try{
                   writer.close();
               }catch (Exception e){
                    e.printStackTrace();
               }
            }
        }
    }
}
