import model.Content;
import service.*;
import service.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        var sc = new Scanner(System.in);

        System.out.println("Seja bem-vindo! Qual conteúdo deseja visualizar?" +
                "\n1 - 250 melhores filmes segundo o TMDB" +
                "\n2 - 250 melhores séries segundo o TMDB" +
                "\n3 - 100 quadrinhos recentemente modificados da Marvel");
        var chosenMedia = sc.nextInt();

        int chosenSortMethod = 0;

        if (chosenMedia == 1 || chosenMedia == 2) {
            System.out.println("Deseja adicionar algum critério de ordenação?" +
                    "\n1 - Mais recente" +
                    "\n2 - Ordem Alfabética" +
                    "\n3 - Maior Nota (Padrão)");
            chosenSortMethod = sc.nextInt();
        }
        if (chosenMedia == 3) {
            System.out.println("Deseja adicionar algum critério de ordenação?" +
                    "\n1 - Mais Recente (Padrão)" +
                    "\n2 - Ordem Alfabética");
            chosenSortMethod = sc.nextInt();
        }

        String url = "";
        JsonParser parser = null;
        Comparator<Content> comparator = null;

        switch (chosenMedia) {
            case 1 -> {
                url = generateTmdbUrl("movie");
                parser = new MovieParser();
            }
            case 2 -> {
                url = generateTmdbUrl("tv");
                parser = new SeriesParser();
            }
            case 3 -> {
                url = MarvelUrlGenerator.generate();
                parser = new ComicSeriesParser();
            }
            default -> {
                System.out.println("Opção inválida");
                return;
            }
        }

        switch (chosenSortMethod) {
            case 0 -> {
            }
            case 1 -> {
                comparator = Comparator.comparing(Content::year).reversed();
            }
            case 2 -> {
                comparator = Comparator.comparing(Content::title);
            }
            case 3 -> {
                if (chosenMedia == 3) {
                    System.out.println("Opção inválida");
                    return;
                }
                comparator = Comparator.comparing(Content::rating).reversed();
            }
            default -> {
                System.out.println("Opção inválida");
                return;
            }
        }

        List<? extends Content> contentList = parser.parse(url);
        contentList.sort(comparator);

        var writer = new FileWriter("content.html");
        new HtmlGenerator(writer).generate(contentList);

        sc.close();
        writer.close();

        System.out.println("\nArquivo HTMl gerado em 'content.html', na raiz do projeto");
    }

    private static String generateTmdbUrl(String typeOfMedia) {
        return "https://api.themoviedb.org/3/" + typeOfMedia + "/top_rated?api_key=" + System.getenv("TMDB_KEY") +
                "&language=pt-BR";
    }
}

