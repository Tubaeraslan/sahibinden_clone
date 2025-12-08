package job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class SearchStatsJob {

    private static final String LOG_FILE = "logs/search.log";

    @Scheduled(fixedRate = 10000)

    public void generateStats() {
        System.out.println("SearchStatsJob çalıştı: " + LocalDateTime.now());

        List<String> lines = readLogLines();
        if (lines.isEmpty()) {
            System.out.println("Log dosyası boş, job sonlandı.");
            return;
        }

        String mostSearchedBrand = getMostFrequent(lines, "brand=");
        String mostSearchedModel = getMostFrequent(lines, "query=");
        long searchesLast24h = getSearchCountLast24h(lines);

        System.out.println("En çok aranan marka: " + mostSearchedBrand);
        System.out.println("En çok aranan model (query): " + mostSearchedModel);
        System.out.println("Son 24 saatte yapılan arama: " + searchesLast24h);
    }

    private List<String> readLogLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = br.readLine()) != null) lines.add(line);
        } catch (Exception e) {
            throw new RuntimeException("Log okuma hatası", e);
        }
        return lines;
    }

    private String getMostFrequent(List<String> lines, String key) {
        Map<String, Integer> counter = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split(";");
            for (String p : parts) {
                if (p.startsWith(key)) {
                    String value = p.replace(key, "");
                    if (!value.isBlank()) {
                        counter.put(value, counter.getOrDefault(value, 0) + 1);
                    }
                }
            }
        }

        return counter.isEmpty()
                ? "Yok"
                : Collections.max(counter.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private long getSearchCountLast24h(List<String> lines) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime limit = now.minusHours(24);

        return lines.stream()
                .filter(line -> {
                    String datePart = line.split(";")[0];
                    LocalDateTime date = LocalDateTime.parse(datePart);
                    return date.isAfter(limit);
                })
                .count();
    }

}
