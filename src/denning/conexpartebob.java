package denning;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import java.net.ServerSocket;

import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class conexpartebob

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

 public static void main(String[] args)

 {
   

 try

 {

 int port = 20000;

 ServerSocket serverSocket = new ServerSocket(port);

 System.out.println("Server Started and listening to the port "+port);

 //Server is running always. This is done using this while(true) loop

 while(true)

 {

 //Reading the message from the client

 socket = serverSocket.accept();

 InputStream is = socket.getInputStream();

 InputStreamReader isr = new InputStreamReader(is);

 BufferedReader br = new BufferedReader(isr);

 String number = br.readLine();

 System.out.println("Message received from client is "+number);
   final String mensaje = number;
     //String clavedesesion="sesionkey";
     String clavebob="clavebob";
    // String paquetebob=clavedesesion+"1"+"5";
    // String paquetebobencriptado = Conex.encrypt(paquetebob, clavebob) ;
    //String originalString = clavedesesion+"-"+"1"+"-"+"2"+"-"+"5"+"-"+paquetebobencriptado;
    //String encryptedString = Conex.encrypt(mensaje, clavebob) ;
    String decryptedString = Conex.decrypt(mensaje, clavebob) ;
     
   // System.out.println(originalString);
    System.out.println(decryptedString);
    // String string = "Este es un mensaje de prueba de integridad-f77f4a34aabe4e974d441383f2ba78a4e2654cdeba45528d68546d86a9f86a87";
   String[] parts = decryptedString.split("-");
    String part1 = parts[0]; 
    String part2 = parts[1]; 
      String part3 = parts[2];   
        System.out.println("mensaje1"+part1);
        System.out.println("mensaje2"+part2);
        System.out.println("mensaje3"+part3);
   // System.out.println(decryptedString);

 //Multiplying the number by 2 and forming the return message

 String returnMessage;
Random aleatorio = new Random(System.currentTimeMillis());
// Producir nuevo int aleatorio entre 0 y 99
int intAletorio = aleatorio.nextInt(100);
String convertido = Integer.toString(intAletorio);
String encryptedString = Conex.encrypt(convertido, part1) ;
 try

 {
 returnMessage = encryptedString+ "\n";
 }

 catch( Exception e)

 {

 //Input was not a number. Sending proper message back to client.

 returnMessage = "Please send a proper number\n";

 }

 //Sending the response back to the client.

 OutputStream os = socket.getOutputStream();

 OutputStreamWriter osw = new OutputStreamWriter(os);

 BufferedWriter bw = new BufferedWriter(osw);

 bw.write(returnMessage);

 System.out.println("Message sent to the client is "+returnMessage);

 bw.flush();

 }

 }

 catch (Exception e)

 {

 e.printStackTrace();

 }

 finally

 {

 try

 {

 socket.close();

 }

 catch(Exception e){}

 }

 }

}
