import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;

public class JavaGetUrl10{
	public static void generator() {

		URL url;
		try {
			File files = new File("C:\\Users\\shivani\\Desktop\\Last_crawler\\src\\Employee.txt");
			FileReader fileReader = new FileReader(files);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//StringBuffer stringBuffer = new StringBuffer();
			String line;
			int i=1;
			while ((line = bufferedReader.readLine()) != null) {
						try {
							// get URL content
							url = new URL(line);
							URLConnection conn = url.openConnection();

							// open the stream and put it into BufferedReader
							BufferedReader br = new BufferedReader(
											   new InputStreamReader(conn.getInputStream()));

							String inputLine;

							//save to this filename
							String fileName = "C:\\Users\\shivani\\Desktop\\Last_crawler\\src\\data\\test"+i+".html";
							File file = new File(fileName);

							if (!file.exists()) {
								file.createNewFile();
							}

							//use FileWriter to write file
							FileWriter fw = new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);

							while ((inputLine = br.readLine()) != null) {
								bw.write(inputLine);
							}

							bw.close();
							br.close();

							System.out.println("Done");

						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						i++;
			}
			fileReader.close();
			try{
				HTMLUtils.converter(i);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
}
