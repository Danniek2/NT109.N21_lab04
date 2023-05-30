package extendthread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.apache.logging.log4j.*;

public class EchoCLient 
{
    private static final Logger loggerDemo = LogManager.getLogger(EchoCLient.class.getName());
	public static final int SERVICE_PORT = 6543;
	// Max size of packet
	public static final int BUFSIZE = 256;
	

	public static void main(String[] args) throws IOException {
            
		String sentence_to_server;
        String sentence_from_server;
      //Tạo socket cho client kết nối đến server qua ID address và port number
        Socket clientSocket = new Socket("127.0.0.1", SERVICE_PORT);
        loggerDemo.info("Tạo socket cho client ket noi den server thong qua ID va port");
        //Tạo Inputstream(từ bàn phím)
        do {
            
        System.out.print("Input from client: ");
        loggerDemo.info("nhap tin nhan tu client");
        BufferedReader inFromUser =
            new BufferedReader(new InputStreamReader(System.in));
        //Lấy chuỗi ký tự nhập từ bàn phím
        sentence_to_server = inFromUser.readLine();
//    
        
    
        //Tạo OutputStream nối với Socket
        PrintWriter outToServer =
            new PrintWriter(clientSocket.getOutputStream(),true);
    
        //Tạo inputStream nối với Socket
        BufferedReader inFromServer =
            new BufferedReader(new
            InputStreamReader(clientSocket.getInputStream()));
//     
        //Gửi chuỗi ký tự tới Server thông qua outputStream đã nối với Socket (ở trên)
        outToServer.println(sentence_to_server + '\n');
    
        //Đọc tin từ Server thông qua InputSteam đã nối với socket
        sentence_from_server = inFromServer.readLine();
    
        //print kết qua ra màn hình
        System.out.println("FROM SERVER: " + sentence_from_server);
        loggerDemo.info("in ket qua ket qua ra man hinh from server");
        } while (!sentence_to_server.equals("bye"));
        //Đóng liên kết socket
        clientSocket.close();    
        loggerDemo.info("ket thuc va dong lien ket socket");
	
	}
}
