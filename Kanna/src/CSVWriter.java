import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    public static void writeData(List<String> data, String fileName) throws IOException {
        FileWriter csvWriter = new FileWriter("mydata.csv");
        for (String value : data) {
            csvWriter.append(value);
            csvWriter.append(",");
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }
}
