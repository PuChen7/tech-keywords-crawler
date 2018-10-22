import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PageNav implements Runnable{
	private int pageNum;
	private String url;
	public ConcurrentHashMap<String, Integer> map;
		
	// constructor
	public PageNav(String url, int pageNum, ConcurrentHashMap<String, Integer> map) {
		this.pageNum = pageNum;
		this.url = url;
		this.map = map;
	}

	@Override
	public void run() {
		ExecutorService fixedExecutorService = Executors.newFixedThreadPool(10);
		Document document = null;
		try {
			// get connection to url
			document = Jsoup.connect(this.url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(document + "\n\n\n");
		
		// holding the selected elements
		Elements job_id_selector = null;
		
		try {
			/* Company name: #resultsCol > div.row.result > div > span.company (.text())
			 * Id: #resultsCol > div.row.result (attr(id))
			 * */
			//job_id_selector = document.select("#resultsCol > div.row.result > div > span.company");
			job_id_selector = document.select("#resultsCol > div.row.result");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < job_id_selector.size(); i++) {
			String id = jobIdTrimmer(job_id_selector.get(i).id());
			String job_url = "https://www.indeed.com/rpc/jobdescs?jks=" + id;
			
			fixedExecutorService.execute(new SingleJobCrawler(job_url, this.map, id));
		}
		
	}
	
	private String jobIdTrimmer(String id) {
		int score_index = id.indexOf('_');
		id = id.substring(score_index+1);
		
		return id;
	}
}
