import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PageNav implements Runnable{
	private int pageNum;
	private String url;
	
	// constructor
	public PageNav(String url, int pageNum) {
		this.pageNum = pageNum;
		this.url = url;
	}

	@Override
	public void run() {
		System.out.println("This is " + this.pageNum + "\n"+this.url+"\n");
		
		Document document = null;
		try {
			// get connection to url
			document = Jsoup.connect(this.url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(document);
		
		// holding the selected elements
		Elements job_id_selector = null;
		
		try {
			// select [row result]
//			job_id_selector = document.select("#resultsCol > div.row.result");
			job_id_selector = document.select("#resultsCol > div.row.result > div > span.company");
		}catch(Exception e) {
			System.out.println("wrong selector");
		}
		
		for (int i = 0; i < job_id_selector.size(); i++) {
			//System.out.println(job_id_selector.get(i));
			System.out.println(job_id_selector.get(i).text());
		}
		
	}
}
