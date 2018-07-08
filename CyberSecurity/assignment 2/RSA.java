/***************************************************************
 CSCI 650         Assignment 2     Summer 2017
 
 Group2
 Prashanth Krishnakumar - Z1784959
 Pruthvi Sambu - Z1804923



 This program implements the RSA encryption algorithm and the respective times are calculated
***************************************************************/
import java.lang.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class RSA
{
	//creating key and the block data
	public static final String algoName = "RSA" ;
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
    KeyPairGenerator key=KeyPairGenerator.getInstance(algoName);
    key.initialize(1024);
    KeyPair pair=key.generateKeyPair();
    Cipher cipher = Cipher.getInstance(algoName);				//initializing the cipher
    cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());					//preparing the encryption cipher
    cipherText = cipher.update(block);
    average=0;
    for(count=0;count<10000;count++)
    {
		start_time=System.nanoTime();
		cipherText = cipher.doFinal(block);
		end_time=System.nanoTime();
		average+=(end_time-start_time);
    }
    average/=10000;
    System.out.println("Average " + average);							//calculating the average encryption time
    cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());						//preparing the decryption cipher
    
    average=0;
    for(count=0;count<10000;count++)
    {
		start_time=System.nanoTime();
		plainText = cipher.doFinal(cipherText);
		end_time=System.nanoTime();
		average+=(end_time-start_time);
    }
    average/=10000;
    System.out.println("Average " + average);
    
    plainText = cipher.doFinal(cipherText);
	output=new String(plainText);
    System.out.println("Decrypted Message is " + output);
  }
}
