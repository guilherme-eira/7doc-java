package service;

import model.ComicSeries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ComicSeriesParser implements JsonParser {
    private final List<String> TITLE_LIST = new ArrayList<>();
    private final List<String> URL_LIST = new ArrayList<>();
    private final List<String> RATING_LIST = new ArrayList<>();
    private final List<String> YEAR_LIST = new ArrayList<>();

    public List<ComicSeries> parse(String url) {
        List<ComicSeries> movieList = new ArrayList<>();

        try {
            var json = ApiClient.getBody(url);

            extractContent(json, "title", TITLE_LIST);
            extractContent(json, "url", URL_LIST);
            extractContent(json, "rating", RATING_LIST);
            extractContent(json, "year", YEAR_LIST);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < TITLE_LIST.size(); i++) {
            movieList.add(new ComicSeries(TITLE_LIST.get(i), URL_LIST.get(i), RATING_LIST.get(i), YEAR_LIST.get(i)));
        }

        return movieList;
    }

    private static void extractContent(String json, String contentType, List<String> listType) {
        String regex = "";

        switch (contentType) {
            case "title" -> {
                regex = "\"title\":\"(.*?)\"";
            }
            case "url" -> {
                regex = "\"path\":\"(.*?)\"";
            }
            case "rating" -> {
                regex = "\"rating\":\"(.*?)\"";
            }
            case "year" -> {
                regex = "startYear\":(\\d{4})";
                ;
            }
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);

        while (matcher.find()) {
            listType.add(matcher.group(1));
        }

    }
}
