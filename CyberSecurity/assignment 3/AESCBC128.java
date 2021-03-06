/***************************************************************
 CSCI 650         Assignment 3     Summer 2017
 
 Group2
 Prashanth Krishnakumar - Z1784959
 Pruthvi Sambu - Z1804923
 
 
Purpose: This program implements the AES encryption algorithm and the respective times are calculated 
***************************************************************/



import java.lang.*;
import java.util.*;
import java.security.*;
import java.io.*;
import javax.crypto.*;
import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import javax.xml.bind.DatatypeConverter;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class AESCBC128
{
	//creating key and the block data
	public static final String algoName = "AES" ;
	public static void main(String args[]) throws Exception
	{
		byte[] inputBlock = new byte[16];
        byte[] cipherBlock = new byte[16];
		FileInputStream inFile = null;
		FileOutputStream outFile = null;
        byte[] keyBytes=DatatypeConverter.parseHexBinary(args[1]);									//Parsing the key and IV
        byte[] ivBytes=DatatypeConverter.parseHexBinary(args[2]);
		if (args.length < 3) 
		{
			System.out.println("Invalid number of arguments. Exiting without encryption");				//error message on invalid arguments
			System.exit(1);
		}
		SecretKey key = new SecretKeySpec(keyBytes, algoName);
		Cipher cipher = Cipher.getInstance("AES_128/CBC/NoPadding");						//initializing the cipher
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivBytes));				//preparing the encryption cipher
    
		try 
		{
			inFile = new FileInputStream(args[0]);
		}
		catch(IOException ex)															//exception handling in case of file not found
		{
			System.out.println("could not open file " + args[0]);
			System.exit(1);
		}
    
		try 
		{
			outFile = new FileOutputStream(args[0] + ".aes");							//creating the output file
		}
		catch(IOException ex)
		{
			System.out.println("could not open file " + args[0] + ".aes");
			System.exit(1);
		}
		while(inFile.read(inputBlock) != -1) 
		{   
			cipherBlock = cipher.update(inputBlock);										//encrypting the data
			outFile.write(cipherBlock);
		}
		cipherBlock = cipher.doFinal();														
		outFile.write(cipherBlock);
		inFile.close();
		outFile.close();
	}
}

