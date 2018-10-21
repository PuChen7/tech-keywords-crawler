
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*
 * 
 */

public class SingleJobCrawler implements Runnable{
	private String url;
	
	public SingleJobCrawler(String url) {
		this.url = url;
	}

	@Override
	public void run() {
		
		String json = null;
		try {
			System.out.println("Going into: " + this.url);
			// get connection to url
			json = Jsoup.connect(this.url).ignoreContentType(true).execute().body();;
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(json + "\n");

		Elements children = null;
		
		
		
	}
}