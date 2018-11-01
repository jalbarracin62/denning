package denning;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import java.net.InetAddress;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class cliente

{
private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

 private static Socket socket;

 public static void main(String args[])

 {
     String global="hola";

 try

 {

 String host = "localhost";

 int port = 25000;

 InetAddress address = InetAddress.getByName(host);

 socket = new Socket(address, port);

 //Send the message to the server

 OutputStream os = socket.getOutputStream();

 OutputStreamWriter osw = new OutputStreamWriter(os);

 BufferedWriter bw = new BufferedWriter(osw);

 String number = "alice";
 String sendMessage = number + "\n";

 bw.write(sendMessage);

 bw.flush();

 System.out.println("Message sent to the server : "+sendMessage);

 //Get the return message from the server

 InputStream is = socket.getInputStream();

 InputStreamReader isr = new InputStreamReader(is);

 BufferedReader br = new BufferedReader(isr);

 String message = br.readLine();

 System.out.println("Message received from the server : " +message);
 
  final String secretKey = number;
     
    String originalString = message;
   // String encryptedString = cliente.encrypt(originalString, secretKey) ;
    String decryptedString = cliente.decrypt(originalString, secretKey) ;
     
   // System.out.println(originalString);
   // System.out.println(encryptedString);
    System.out.println("el mensaje desencriptado es:"+decryptedString);
 String[] parts = decryptedString.split("-");
    String part1 = parts[0]; 
    String part2 = parts[1]; 
     String part3 = parts[2]; 
     String part4 = parts[3];
     String part5 = parts[4];
        System.out.println("mensaje1:"+part1);
        System.out.println("mensaje2:"+part2);
        System.out.println("mensaje3:"+part3);
        System.out.println("mensaje4:"+part4);
        System.out.println("mensaje:"+part5);
        System.out.println("");
        
        
        
        
        
        
        
        int port1 = 20000;

 InetAddress address1 = InetAddress.getByName(host);

 socket = new Socket(address1, port1);

 //Send the message to the server

 OutputStream os1 = socket.getOutputStream();

 OutputStreamWriter osw1 = new OutputStreamWriter(os1);

 BufferedWriter bw1 = new BufferedWriter(osw1);

 //String number = "alice";
 String sendMessage1 = part5 + "\n";

 bw1.write(sendMessage1);

 bw1.flush();

 System.out.println("Message sent to the server : "+sendMessage1);

 //Get the return message from the server

 InputStream is1 = socket.getInputStream();

 InputStreamReader isr1 = new InputStreamReader(is1);

 BufferedReader br1 = new BufferedReader(isr1);

 String message1 = br1.readLine();

 System.out.println("Message received from the server : " +message1);
 
 // final String secretKey = number;
     
   // String originalString = message;
   // String encryptedString = cliente.encrypt(originalString, secretKey) ;
    String decryptedString1 = cliente.decrypt(message1, part1) ;
     System.out.println("ultdes:"+decryptedString1);
     
     
    

 InetAddress address3 = InetAddress.getByName(host);

 socket = new Socket(address3, port1);

 //Send the message to the server

 OutputStream os3 = socket.getOutputStream();

 OutputStreamWriter osw3 = new OutputStreamWriter(os3);

 BufferedWriter bw3 = new BufferedWriter(osw3);
     int parcei=Integer.parseInt(decryptedString1);
     int decript3=parcei+1;
     System.out.println("numero:"+decript3);
     String s=Integer.toString(decript3);
     String sendMessage2 = s + "\n";

 bw3.write(sendMessage2);

 bw3.flush();

 System.out.println("Message sent to the server : "+sendMessage2);
   // System.out.println(originalString);
   // System.out.println(encryptedString);
    //System.out.println("el mensaje desencriptado es:"+decryptedString);
 //String[] parts = decryptedString.split("-");
   // String part1 = parts[0]; 
 //   String part2 = parts[1]; 
     //String part3 = parts[2]; 
     //String part4 = parts[3];
    // String part5 = parts[4];
      //  System.out.println("mensaje1:"+part1);
       // System.out.println("mensaje2:"+part2);
        //System.out.println("mensaje3:"+part3);
       // System.out.println("mensaje4:"+part4);
       // System.out.println("mensaje:"+part5);
       // System.out.println("");
        

 }

 catch (Exception exception)

 {

 exception.printStackTrace();

 }

 finally

 {

 //Closing the socket

 try

 {

 socket.close();

 }

 catch(Exception e)

 {

 e.printStackTrace();

 }

 }
  

 }}