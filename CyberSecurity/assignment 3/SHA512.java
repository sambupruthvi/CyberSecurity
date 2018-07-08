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

public class SHA512

{
	//creating key and the block data
	public static final String algoName = "SHA-512" ;
	public static void main(String args[]) throws Exception
	{
		byte[] inputBlock = new byte[16];
		FileOutputStream outFile = null;
		FileInputStream inFile = null;
        
		if (args.length < 1) 
		{
			System.out.println("Invalid number of arguments. Exiting without hashing");				//error message on invalid arguments
			System.exit(1);
		}
    
		try
		{
			inFile = new FileInputStream(args[0]);
		} 
		catch(IOException ex) 													//exception handling in case of file not found
		{
			System.out.println("could not open file: " + args[0]);
			System.exit(1);
		}
    
		try 
		{
			outFile = new FileOutputStream(args[0] + ".sha");					//creating output file
		} 
		catch(IOException ex)
		{
			System.out.println("could not open file: " + args[0] + ".sha");
			System.exit(1);
		}
		MessageDigest md = MessageDigest.getInstance("SHA-512");				//encrypting
		while (inFile.read(inputBlock) != -1)
		{
			md.update(inputBlock);
		}

		byte[] digest = md.digest();											
		outFile.write(digest);
		inFile.close();
		outFile.close();
	}
}

