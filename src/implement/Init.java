package implement;

import java.util.Random;
import org.apache.logging.log4j.*;
public class Init 
{
    private static final Logger loggerDemo = LogManager.getLogger(Init.class.getName());
	//khai báo mảng 
	int []arr;
	public Init()
	{
		int a = 2000;
		Random r = new Random();
		
//		// khởi tạo mảng có 2000 phần tử 
		arr = new int[2000];
//		
//		// thêm giá trị random cho mảng
		for(int i = 0; i < arr.length; i++)
		{
			int random = r.nextInt(a);
			arr[i] = random;
		}
		System.out.println("compelete ");
	}
}
