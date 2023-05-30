package implement;
import org.apache.logging.log4j.*;
public class MyThread implements Runnable 
{
    private static final Logger loggerDemo = LogManager.getLogger(MyThread.class.getName());
	int start;
	int end;
	int []arr;
	String name;
	String state = " input";
	
	public MyThread(int start, int end, int[] arr, String name)
	{
		this.start = start;
		this.end = end;
		this.arr = arr;
		this.name = name;
	}
	
	//hàm sort
	protected void sort()
	{
		for(int i = start; i < end; i++)
		{
			for(int j = i+1; j < end; j++)
			{
				if(arr[i] > arr[j])
				{
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}
	
	//Hàm print
	protected void print()
	{
		String str = this.name + state + ":		";
		for(int i = start; i<end; i++)
		{
			str += arr[i]+ " ";
		}
		System.out.println(str);
	}
	
	public void run()
	{
		print();
		sort();
		state = " output";
		print();
                
        }
}