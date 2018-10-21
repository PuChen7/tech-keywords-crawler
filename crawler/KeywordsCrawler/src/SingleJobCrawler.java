
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*
 * 
 */

public class SingleJobCrawler implements Runnable{
	private String url;
	public ConcurrentHashMap<String, Integer> map;
	
	public SingleJobCrawler(String url, ConcurrentHashMap<String, Integer> map) {
		this.url = url;
		this.map = map;
	}

	@Override
	public void run() {
		
		String json = null;
		try {
			// get response
			json = Jsoup.connect(this.url).ignoreContentType(true).execute().body();;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (json.contains("java")) {
			map.replace("java", map.get("java")+1);
		}
		
	}
}