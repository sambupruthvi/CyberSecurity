/***************************************************************
 CSCI 650         Assignment 2     Summer 2017
 
 Group2
 Prashanth Krishnakumar - Z1784959
 Pruthvi Sambu - Z1804923
 
This program implements the RC2 encryption algorithm and the respective times are calculated 
***************************************************************/
import java.lang.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class RC2
{	
	//creating key and the block data
	public static final String algoName = "RC2" ;
	public static final byte[] keyBytes = new byte[] {'c','o','m','p','u','t','e','r'};
	public static final byte[] block = new byte[] {'I','t',' ','i','s',' ','n','o'};
	public static void main(String args[]) throws Exception
	{
	byte[] cipherText;
	byte[] plainText;
	String output;					//declaration of required variables.
	long start_time;
	long end_time;
	double average;
	int count;
    SecretKey key = new SecretKeySpec(keyBytes, algoName);
    Cipher cipher = Cipher.getInstance(algoName);				//initializing the cipher
    cipher.init(Cipher.ENCRYPT_MODE, key);						//preparing the encryption cipher
    cipherText = cipher.update(block);
    average=0;
    for(count=0;count<1000000;count++)
    {
		start_time=System.nanoTime();
		cipherText = cipher.update(block);
		end_time=System.nanoTime();
		average+=(end_time-start_time);							//calculating the average encryption time
    }
    average/=1000000;
    System.out.println("Average " + average);
    cipher.init(Cipher.DECRYPT_MODE, key);						//preparing the decryption cipher
    
    plainText = cipher.update(cipherText);
    average=0;
    for(count=0;count<1000000;count++)
    {
		start_time=System.nanoTime();
		plainText = cipher.update(cipherText);
		end_time=System.nanoTime();
		average+=(end_time-start_time);
    }
    average/=1000000;
    System.out.println("Average " + average);
    
	output=new String(plainText);
    System.out.println("Decrypted Message is " + output);				//printing out the decrypted message
  }
}
