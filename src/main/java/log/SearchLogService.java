package log;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class SearchLogService {

    private static final String LOG_FILE = "logs/search.log";

    public void logSearch(
            String brand,
            String query,
            Integer minPrice,
            Integer maxPrice,
            Integer minYear,
            Integer maxYear,
            String color,
            String sort
    ) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            String line = LocalDateTime.now()
                    + ";brand=" + (brand != null ? brand : "")
                    + ";query=" + (query != null ? query : "")
                    + ";minPrice=" + (minPrice != null ? minPrice : "")
                    + ";maxPrice=" + (maxPrice != null ? maxPrice : "")
                    + ";minYear=" + (minYear != null ? minYear : "")
                    + ";maxYear=" + (maxYear != null ? maxYear : "")
                    + ";color=" + (color != null ? color : "")
                    + ";sort=" + (sort != null ? sort : "");

            bw.write(line);
            bw.newLine();

        } catch (IOException e) {
            throw new RuntimeException("Log Error", e);
        }
    }
}
