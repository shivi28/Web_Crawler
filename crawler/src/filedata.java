import java.io.*;
import java.sql.*;
import java.util.*;
public class filedata{
	
    public static void executeres() {
    	List<String> data = new ArrayList<String>();
            try {
                    Connection con = null;
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crawler", "root", "");
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("Select * from record");
                    while (rs.next()) {
                    		String URL = rs.getString("URL");
                            data.add(URL);
                            

                    }
                    writeToFile(data, "C:\\Users\\shivani\\Desktop\\Last_crawler\\src\\Employee.txt");
                    rs.close();
                    st.close();
            } catch (Exception e) {
                    System.out.println(e);
            }
            
    }

    private static void writeToFile(java.util.List<String> list, String path) {
            BufferedWriter out = null;
            try {
            		File file = new File(path);
                    out = new BufferedWriter(new FileWriter(file, true));
                    for (Object s : list) {
                            out.write((String)s);
                            out.newLine();

                    }
                    out.close();
            } catch (IOException e) {
            }
    }
}