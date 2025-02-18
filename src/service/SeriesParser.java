package service;

import model.Series;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SeriesParser implements JsonParser {
    private final List<String> TITLE_LIST = new ArrayList<>();
    private final List<String> URL_LIST = new ArrayList<>();
    private final List<String> RATING_LIST = new ArrayList<>();
    private final List<String> YEAR_LIST = new ArrayList<>();

    public List<Series> parse(String url){
        List<Series> seriesList = new ArrayList<>();

        for (int i = 0; i <= 13; i++) {
            try {
                var json = ApiClient.getBody(url + "&page=" + i);

                extractContent(json, "title", TITLE_LIST);
                extractContent(json, "url", URL_LIST);
                extractContent(json, "rating", RATING_LIST);
                extractContent(json, "year", YEAR_LIST);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < TITLE_LIST.size(); i++) {
            seriesList.add(new Series(TITLE_LIST.get(i), URL_LIST.get(i), RATING_LIST.get(i), YEAR_LIST.get(i)));
        }

        return seriesList;
    }

    private static void extractContent(String json, String contentType, List<String> listType) {
        String regex = "";

        switch (contentType) {
            case "title" -> {
                regex = "\"name\":\"(.*?)\"";
            }
            case "url" -> {
                regex = "\"poster_path\":(null|\"(.*?)\")";
            }
            case "rating" -> {
                regex = "\"vote_average\":(\\d*\\.\\d+|\\d+)";
            }
            case "year" -> {
                regex = "\"first_air_date\":\"(.*?)\"";
            }
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);

        while (matcher.find() && listType.size() < 250) {
            listType.add(matcher.group(1));
        }

    }
}
