
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.Writer;

import org.jsoup.Jsoup;

public class HTMLUtils {
  private HTMLUtils() {}

  public static String extractText(Reader reader) throws IOException {
    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(reader);
    String line;
    while ( (line=br.readLine()) != null) {
      sb.append(line);
    }
    String textOnly = Jsoup.parse(sb.toString()).text();
    return textOnly;
  }

  public static void converter(int k) throws Exception{
	 for(int i=1;i<k;i++){
		    FileReader reader = new FileReader
		          ("C:\\Users\\shivani\\Desktop\\Last_crawler\\src\\data\\test"+i+".html");
		    BufferedWriter bw=null;
			File myfile=new File("C:\\Users\\shivani\\Desktop\\Last_crawler\\src\\data\\test"+i+".txt");
			if(!myfile.exists()){
				myfile.createNewFile();
			}
			//System.out.println(HTMLUtils.extractText(reader));
			Writer wr= new FileWriter(myfile);
			bw=new BufferedWriter(wr);
			bw.write(HTMLUtils.extractText(reader));
			bw.close();
	 }
    
    
  }
}