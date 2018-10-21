
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*
 * 
 */

public class Crawler implements Runnable{
	
	
	public static void main(String args[]) throws InterruptedException{
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
		map.put("java", 0);
		map.put("c", 0);
		
		ExecutorService fixedExecutorService = Executors.newFixedThreadPool(10);;
		int pageCount = 0;
		String url = "https://www.indeed.com/jobs?q=software+engineer&start=" + pageCount;
		
//		Thread[] pageThread = new Thread[2];
		for (int i = 0; i < 100; i++) {
			fixedExecutorService.execute(new PageNav(url, pageCount, map));
			
//			pageThread[i] = new Thread(new PageNav(url, pageCount));
//			pageThread[i].start();

			// update count and url
			pageCount += 10;
			url = "https://www.indeed.com/jobs?q=software+engineer&start=" + pageCount;
		}
		fixedExecutorService.shutdown();
		while(!fixedExecutorService.awaitTermination(1, TimeUnit.MINUTES));
		
		System.out.println(map);
	}

	@Override
	public void run() {
		
		
	}
}