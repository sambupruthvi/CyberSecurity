/***************************************************************
 CSCI 650         Assignment 2     Summer 2017
 
 Group2
 Prashanth Krishnakumar - Z1784959
 Pruthvi Sambu - Z1804923
 Due Date:7/18/2017
 
This program implements the Blowfish encryption algorithm and the respective times are calculated 
***************************************************************/

import java.lang.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class Blowfish
{
	//creating key and the block data
	public static final String algoName = "Blowfish";
	public static final byte[] keyBytes = new byte[] {'c','o','m','p','u','t','e','r','S','e','c','u','r','i','t','y'};
	public static final byte[] block = new byte[] {'I','t',' ','i','s',' ','n','o'};
	public static void main(String args[]) throws Exception
	{
	byte[] cipherText;
	byte[] plainText;
	String output;					//declaration of required variables
	long start_time;
	long end_time;
	double average;
	int count;
    SecretKey key = new SecretKeySpec(keyBytes, algoName);				//initializing the cipher
    Cipher cipher = Cipher.getInstance(algoName);					//preparing the encryption cipher
    cipher.init(Cipher.ENCRYPT_MODE, key);
    cipherText = cipher.update(block);
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
    System.out.println("Decrypted Message is " + output);
  }
}
