/***************************************************************
CSCI 650         Assignment 2     Summer 2017

Group2
 Prashanth Krishnakumar - Z1784959
 Pruthvi Sambu - Z1804923

This program implements the AES encryption algorithm and the respective times are calculated 
***************************************************************/
import java.lang.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class AESECB128
{
	//creating key and the block
	public static final String algoName = "AES" ;
	public static final byte[] keyBytes = new byte[] {'c','o','m','p','u','t','e','r','S','e','c','u','r','i','t','y'};
	public static final byte[] block = new byte[] {'I','t',' ','i','s',' ','n','o','t',' ','a',' ','j','o','k','e'};
	public static void main(String args[]) throws Exception
	{
	byte[] cipherText;
	byte[] plainText;
	String output;					//declaration of required variables
	long start_time;
	long end_time;
	double average;
	int count;
    SecretKey key = new SecretKeySpec(keyBytes, algoName);
    Cipher cipher = Cipher.getInstance("AES_128/ECB/NoPadding");				//initializing the cipher
    cipher.init(Cipher.ENCRYPT_MODE, key);					//preparing the encryption cipher
    cipherText = cipher.doFinal(block);
    average=0;
    for(count=0;count<1000000;count++)
    {
		start_time=System.nanoTime();
		cipherText = cipher.update(block);
		end_time=System.nanoTime();
		average+=(end_time-start_time);
    }
    average/=1000000;							//calculating the average encryption time
    System.out.println("Average " + average);
    cipher.init(Cipher.DECRYPT_MODE, key);						//preparing the decryption cipher
    
    plainText = cipher.doFinal(cipherText);
    average=0;
    for(count=0;count<1000000;count++)
    {
		start_time=System.nanoTime();
		plainText = cipher.doFinal(cipherText);
		end_time=System.nanoTime();
		average+=(end_time-start_time);
    }
    average/=1000000;
    System.out.println("Average " + average);
    
    plainText = cipher.doFinal(cipherText);
	output=new String(plainText);
    System.out.println("Decrypted Message is " + output);
  }
}

