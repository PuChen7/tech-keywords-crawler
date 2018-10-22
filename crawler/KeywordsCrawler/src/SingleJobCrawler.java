
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import org.jsoup.Jsoup;
import com.google.gson.*;

/*
 * 
 */

public class SingleJobCrawler implements Runnable{
	private String url;
	public ConcurrentHashMap<String, Integer> map;
	private String id;
	
	public SingleJobCrawler(String url, ConcurrentHashMap<String, Integer> map, String id) {
		this.url = url;
		this.map = map;
		this.id = id;
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
				
		// convert json string to json
		JsonObject jobj = new Gson().fromJson(json, JsonObject.class);
		
		// all text in this job post
		String text = Jsoup.parse(jobj.get(this.id).toString()).text();
		
		searchAndUpdate(text, this.map);
	}
	
	private void searchAndUpdate(String text, ConcurrentHashMap<String, Integer> map) {
		for (String key : map.keySet()) {
			if (text.toLowerCase().contains(key)) {
				map.put(key, map.get(key)+1);
			}
		}
	}
}