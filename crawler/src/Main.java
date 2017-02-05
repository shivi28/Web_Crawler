import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
 
public class Main {
	public static DB db = new DB(); 
	public static void main(String[] args) throws SQLException, IOException {
		
		db.runSql2("TRUNCATE record;");
		System.out.println("Processing the Input URL");
		//processPage("http://www.geeksforgeeks.org/java/");
		System.out.println("Putting the URL's into a Employee.txt file ");
		//filedata.executeres();
		System.out.println("Saving the web content into test files");
		JavaGetUrl10.generator();
	}
 
	public static void processPage(String URL) throws SQLException, IOException{
		//check if the given URL is already in database
		String sql = "select * from record where URL = '"+URL+"'";
		ResultSet rs = db.runSql(sql);
		//System.out.println(rs.getString(sql));
		if(rs.next()){
 
		}else{
			//store the URL to database to avoid parsing again
			sql = "INSERT INTO  `crawler`.`record` " + "(`URL`) VALUES " + "(?);";

			PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, URL);
			stmt.execute();
			
 
			//get useful information
			try{
			Document doc = Jsoup.connect("http://www.geeksforgeeks.org/java/").timeout(0).get();
 
 
			//get all links and recursively call the processPage method
			Elements questions = doc.select("a[href]");
			Elements media = doc.select("[src]");
		   
			for(Element link: questions){
				if(link.attr("href").contains(":"))
					
					processPage(link.attr("abs:href"));
				
			}
			for(Element link1: media){
				processPage(link1.attr("abs:src"));
				
			}
			}catch (HttpStatusException e) {
			    System.out.println("Invalid website");
			}
		}
	}
}