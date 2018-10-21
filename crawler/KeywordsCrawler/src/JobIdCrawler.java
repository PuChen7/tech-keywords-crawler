
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*
 * 
 */

public class Crawler implements Runnable{
	
	public static void main(String args[]){
		int count = 0;
		System.out.println("running...");
		
		Document document = null;
		String url = "https://www.indeed.com/jobs?q=software+engineer&start=0";
		try {
			// get connection to url
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// holding the selected elements
		Elements job_id_selector = null;
		
		try {
			// select [row result]
//			job_id_selector = document.select("#resultsCol > div.row.result");
			job_id_selector = document.select("#resultsCol > div.row.result > div > span.company");
		}catch(Exception e) {
			System.out.println("wrong selector");
		}
		
		
		Thread[] thread_arr = new Thread[job_id_selector.size()];
		for (int i = 0; i < job_id_selector.size(); i++) {
			//System.out.println(job_id_selector.get(i));
			
			thread_arr[i] = new Thread(new Crawler(job_id_selector.get(i).text().toString()));
		}
		
		for (int j = 0; j < job_id_selector.size(); j++) {
			thread_arr[j].start();
		}
		
		//System.out.println("done " + job_id_selector.size());
	}

	// method for trimming job id
	public static String trimId(String id) {
		
		return "";
	}
}