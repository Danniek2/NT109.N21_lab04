/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sort;

import java.lang.System;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.apache.logging.log4j.*;
/**
 *
 * @author Admin
 */
public class Sort2000 extends Thread {
     private static final Logger loggerDemo = LogManager.getLogger(Sort2000.class.getName());
    
    private static final int MAX_THREADS = 4;
    
		Sort2000(Integer[] array, int begin, int end){
			super(()->{
                            mergeSort(array, begin, end);
			});
			this.start();
		}
	
 
    public static void mergeSort(Integer[] array, int begin, int end){
		if (begin<end){
			int mid = (begin+end)/2;
			mergeSort(array, begin, mid);
			mergeSort(array, mid+1, end);
			merge(array, begin, mid, end);
		}
	}
    
    
    // Thực hiện sắp xếp theo luồng
	public static void threadedSort(Integer[] array){
		//lấy thời gian hiện tại trước khi bắt đầu
		long time = System.currentTimeMillis();
		final int length = array.length;
		// Khối lượng công việc mỗi luồng (chunk_of_data) = total_elements/core_count
                // nếu chính xác không có phần tử nào đi vào không có chủ đề nào,
                // sau đó chia đều công việc,
                // nếu không, nếu có một số phần còn lại, thì giả sử chúng ta có sẵn (actual_threads-1) worker
                // và chỉ định các phần tử còn lại được xử lý bởi 1 luồng thực tế còn lại.
		boolean exact = length%MAX_THREADS == 0;
		int maxlim = exact? length/MAX_THREADS: length/(MAX_THREADS-1);
		// nếu khối lượng công việc ít hơn và không cần nhiều hơn 1 luồng cho công việc thì gán tất cả cho 1 luồng
		maxlim = maxlim < MAX_THREADS? MAX_THREADS : maxlim;
		// Để theo dõi các chủ đề
		final ArrayList<Sort2000> threads = new ArrayList<>();
		// Vì mỗi luồng hoạt động độc lập trên đoạn được chỉ định của nó,
                // sinh ra các luồng và gán phạm vi chỉ mục làm việc của chúng
                
		for(int i=0; i < length; i+=maxlim){
			int beg = i;
			int remain = (length)-i;
			int end = remain < maxlim? i+(remain-1): i+(maxlim-1);
			final Sort2000 t = new Sort2000(array, beg, end);
			// Thêm các tham chiếu luồng để nối chúng sau này
			threads.add(t);
		}
		
		for(int i=0; i < length; i+=maxlim){
			int mid = i == 0? 0 : i-1;
			int remain = (length)-i;
			int end = remain < maxlim? i+(remain-1): i+(maxlim-1);
			merge(array, 0, mid, end);
		}
		time = System.currentTimeMillis() - time;
                loggerDemo.info("tinh thoi gian chạy 4 luong");
		System.out.println(" thoi gian chay 4 luong: "+ time+ "ms");
	}
    
    public static void merge(Integer[] array, int begin, int mid, int end){
		Integer[] temp = new Integer[(end-begin)+1];
		
		int i = begin, j = mid+1;
		int k = 0;

		// Thêm các phần tử từ nửa đầu hoặc nửa sau dựa trên giá trị nào thấp hơn,
                // làm cho đến khi hết một danh sách và không thể so sánh trực tiếp một đối một nữa
		while(i<=mid && j<=end){
			if (array[i] <= array[j]){
				temp[k] = array[i];
				i+=1;
			}else{
				temp[k] = array[j];
				j+=1;
			}
			k+=1;
		}

		// Thêm các phần tử còn lại vào mảng tạm thời từ nửa đầu còn lại
		while(i<=mid){
			temp[k] = array[i];
			i+=1; k+=1;
		}
		
		// Thêm các phần tử còn lại vào mảng tạm thời từ nửa sau còn lại
		while(j<=end){
			temp[k] = array[j];
			j+=1; k+=1;
		}

		for(i=begin, k=0; i<=end; i++,k++){
			array[i] = temp[k];
		}
	}
}
