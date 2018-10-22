
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * 
 */

public class Crawler {
	
	
	public static void main(String args[]) throws InterruptedException{
		// define searching criteria
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
		map.put("java", 0);
		map.put(" c ", 0);
		map.put("javascript", 0);
		map.put("node", 0);
		map.put("c++", 0);
		map.put("python", 0);
		map.put("html", 0);
		map.put("css", 0);
		map.put("angular", 0);
		map.put("bootstrap", 0);
		map.put("react", 0);
		map.put("php", 0);
		map.put("vue", 0);
		map.put("object-oriented", 0);
		map.put("spring", 0);
		map.put("mvc", 0);
		map.put("mysql", 0);
		map.put("sql", 0);
		map.put("tcp/ip", 0);
		map.put("c#", 0);
		map.put("ruby", 0);
		map.put(".net", 0);
		map.put("rest", 0);
		
		
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
}