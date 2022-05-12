import com.option.backtesting.csv.NiftyCsvReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class OptionBackTesting {

    public static void main(String[] args) {
        try {
            NiftyCsvReader csvReader = new NiftyCsvReader();
            csvReader.readCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
