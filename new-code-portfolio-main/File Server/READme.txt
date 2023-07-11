The two programs PAServer and PAClient both implement a File Share Server through the use of Sockets.

PAServer should be run first and then PAClient right after.

The server has a hardcoded text file name as its argument and the client attempts to fetch that particular file to see if it exists.

The original exisiting file reads "The saved file should say this" for reference once the client saves a new file with the same extension 
under the name "received_clt.txt". If the file exists the newly saved file will contain its contents and be saved under that extension.

If the file doesn't exist the client will save a blank file under that name and the terminal will return with a file not found exception and read that the the file isn't available.

Be wary the transfer only completes if the hardcoded file already exists.


the sockets will then close and sever the connection.