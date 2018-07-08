/***************************************************************
 CSCI 650         Assignment 4     Summer 2017
 
 Group2
 Prashanth Krishnakumar - Z1784959
 Pruthvi Sambu - Z1804923
***************************************************************/
import java.io.*;
import java.net.*;
import java.lang.*;
import java.text.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import javax.xml.bind.DatatypeConverter;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class Assignment4 
{
    public static void main(String[] args) throws Exception 
	{
		String send = "group 2";														//Message to be sent to the server
		InetAddress address = InetAddress.getByName("blitz.cs.niu.edu");				//address
		byte[] keyM=new byte[16];
		System.out.println("Trying the connect to the UDP ports:");
		for(int i=9000; i <=9100; i++)
		{     
			try
			{
				byte[] buffer = send.getBytes();
				DatagramSocket ds = new DatagramSocket();								//creating the datagram socket
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);			//creating the datagram packet	
				ds.setSoTimeout(100);													//determining a failed connection
				ds.connect(address, i);													//connecting to the socket
				ds.send(dp);
				byte[] buffer1=new byte[16];  
				dp = new DatagramPacket(buffer1, buffer1.length);
				ds.receive(dp);
				ds.close();
				System.out.println();
				System.out.println("Connected to the UDP port: "+i);
				Date date = new Date();
				System.out.println("Connected to UDP port at: "+date.toString());			//printing the date format
				String received = new String(dp.getData());
				keyM=dp.getData();
				break;
			}
			catch(IOException e)
			{
				System.out.print(i+",");
			}  	     
		}
		InputStream is;
		SecretKey key = new SecretKeySpec(keyM, "ARCFOUR");									//generating the key
		Cipher cipher = Cipher.getInstance("ARCFOUR");				
		cipher.init(Cipher.DECRYPT_MODE, key);
		System.out.println("trying to connect to TCP ports: ");
		for(int i=9000; i <=9100; i++)
		{
			try
			{
				Socket conn = new Socket("blitz.cs.niu.edu", i);							//trying to connect to the TCP port
				System.out.println();
				System.out.println("Connected to the TCP port: "+i);
				Date date1 = new Date();
				System.out.println("Connected to UDP port at: "+date1.toString());
				is = conn.getInputStream();
				OutputStream os = conn.getOutputStream();    
				os.write(send.getBytes());
				byte[] message = new byte[1];
				byte[] output= new byte[1];
				while(is.read(message)!=-1)
				{
					output= cipher.update(message);	
					String received = new String(output);									//reading message byte by byte
					System.out.print(received);
				}
				conn.close();
				break;
			}
			catch(IOException ie)
			{
				System.out.print(i+",");
			}
		}
    }
}
