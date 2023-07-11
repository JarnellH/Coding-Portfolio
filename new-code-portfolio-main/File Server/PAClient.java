
/*
 * 
 * @Version 1.1 
 * @Author Jarnell Hayes 
 * 
 *The following code is the client end of a File Share Server  
 * the client is implemented using sockets which connect to the host 
 * through the IP address and its corresponding port number 
 */

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
 
public class PAClient {
	public static int PORT= 4444;
	public static int BUFFER_SIZE= 16*1024;
    
    public static void main(String[] args) throws Exception {

        String fileName = null;
		String ip = "localhost";
        Socket socket = new Socket(ip, PORT);


        //Attempt to fetch a file from the server being mindful of file not existing 
        try {

			fileName = "received.txt";
            serveFile(socket, fileName);

        } catch (Exception e) {
            System.out.println("The file " + fileName + " is not available");
        }

		
        /*
            Prepare to save the contents of the fetched file into a new file with the client's extension 
            Read the contents from the server and then write them into the new file 
            and close the socket connection of the client 
        */
        String saveFile = "received_clt.txt";
        InputStream in = socket.getInputStream();
        OutputStream out= new FileOutputStream(saveFile);
 
        byte [] buffer = new byte[BUFFER_SIZE];
        int bytesRead = 0;

        while ((bytesRead = in.read(buffer)) > 0) {
            out.write(buffer,0,bytesRead);
        }


        //Closes the I/O streams and the Socket connection
        out.close();
        in.close();
		socket.close();
		System.out.println("Done, client has saved " + saveFile +" in it's current directory " );
    }

    /*
     * 
     * The method below attempts to fetch a given file from the Server
     * if the file exists the file is read and written to the socket 
     * in the output stream 
     */
    private static void serveFile(Socket socket, String filename) throws IOException {
        try{
        InputStream in = new FileInputStream(filename);
		OutputStream out = socket.getOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = 0 ;
         
		while((bytesRead = in.read(buffer)) > 0 ){
			out.write(buffer,0,bytesRead);
		}
           
    }catch(IOException e){
        System.out.println(e);
    }
    }
}