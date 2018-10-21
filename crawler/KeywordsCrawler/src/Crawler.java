
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*
 * 
 */

public class Crawler implements Runnable{
	
	public static void main(String args[]){
		int pageCount = 0;
		String url = "https://www.indeed.com/jobs?q=software+engineer&start=" + pageCount;
		
		Thread[] pageThread = new Thread[2];
		for (int i = 0; i < 2; i++) {
			pageThread[i] = new Thread(new PageNav(url, pageCount));
			pageThread[i].start();

			// update count and url
			pageCount += 10;
			url = "https://www.indeed.com/jobs?q=software+engineer&start=" + pageCount;
		}
	}

	@Override
	public void run() {
		
		
	}
}