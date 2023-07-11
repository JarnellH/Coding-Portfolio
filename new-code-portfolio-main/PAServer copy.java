
/* 
*
*
* @Version 1.1
* @author  Jarnell Hayes 
*
*   The Following program is a File Share Server which is being implemented using 
*   a socket connection.
*
*/ 

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.*;

class PAServer {

    
	public static int BUFFER_SIZE=16*1024;
	public static int PORT= 4444;
	public static void main(String args[]) throws Exception{

        //Establish the Server using the socket and connect the host with client 
		ServerSocket serverSocket = new ServerSocket(PORT);
		Socket socket = serverSocket.accept();

		System.out.println("Accepted connection to Client "+socket);

        InputStream in = new FileInputStream("received.txt");

        //The byte array will be used to read the contents of the file
        byte [] buffer = new byte[BUFFER_SIZE];
        int bytesRead = 0;

        OutputStream out = socket.getOutputStream();

        //This loop reads the file and writes it back into the output stream 
        while ((bytesRead = in.read(buffer)) > 0){
            out.write(buffer, 0 , bytesRead);
            }


        //Closes the I/O Stream
        out.close();
        in.close();

        //CLoses the Socket and the Server Socket 
        socket.close();
        serverSocket.close();
		
		
	}

}