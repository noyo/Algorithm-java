package util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {
	
	private static final String ROOT = "C:\\Users\\Administrator\\Desktop\\算法io";

	/**
	 * 按行读文件
	 * @param filePath
	 * @return
	 */
	public static List<String> readFromFile(String filePath) {
		List<String> data = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(ROOT + "/" + filePath));) {
			String line;
			while (null != (line = reader.readLine())) {
				data.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 按行写文件
	 * @param data
	 * @param filePath
	 */
	public static void wirteToFile(String filePath, boolean add, String... data) {
		File file = new File(ROOT + "/" + filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, add));) {
			for (String line : data) {
				writer.write(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
