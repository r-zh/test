package file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import com.csvreader.CsvReader;

public class CSV {
	// FIile name: 028260EFD967B0CA16344D22A2DD07E20ED768E0C4BD602FF06B73D79A334817
	public static void main(String[] args) {
		String file = "/Users/tonyzhou/Desktop/University/logging/028260EFD967B0CA16344D22A2DD07E20ED768E0C4BD602FF06B73D79A334817.csv";
		CSVreader(file);
	}

	// read the record
	public static void CSVreader(String file) {
		try {
			CsvReader csvReader = new CsvReader(file, ',', Charset.forName("UTF-8"));
			
			while (csvReader.readRecord()) {
	            for (int i = 0; i < csvReader.getColumnCount(); i++)
	            {
	                System.out.println(csvReader.get(i));
	            }
	        }
	        csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
