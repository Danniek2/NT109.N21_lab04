package implement;

import java.util.Scanner;
import org.apache.logging.log4j.*;
public class App 
{
    private static final Logger loggerDemo = LogManager.getLogger(App.class.getName());
	public static void main(String[] args)
	{
		Init init = new Init();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seclect 1. Single Thread; 2. Multi Thread");
                loggerDemo.info("Chon gia tri 1 hoac 2 de thuc thi");
		int choose = scanner.nextInt();
		switch (choose)
		{
			//Single Thread
			case 1: 
			{
                            
                                loggerDemo.info("Mang duoc tao nhu sao");
				System.out.println("Input Array:"); 
                                
				Thread mainThread = new Thread(new MyThread(0, init.arr.length, init.arr, "Main Thread"));
				mainThread.start();
                                break;   
                                
			}
//			//Multi Thread
			case 2: 
			{
                                loggerDemo.info("thu duoc 4 thread nhu sau");
				Thread th1 = new Thread(new MyThread(0, 499, init.arr, "Thread 1"));
				Thread th2 = new Thread(new MyThread(500, 999, init.arr, "Thread 2"));
				Thread th3 = new Thread(new MyThread(1000, 1499, init.arr, "Thread 3"));
				Thread th4 = new Thread(new MyThread(1500, 1999, init.arr, "Thread 4"));
				
				th1.start();
				th2.start();
				th3.start();
				th4.start();                            
				break;				
			}
			default: 
				throw new IllegalArgumentException("Unexpected value: " + choose);
                                
		}
	}
}
