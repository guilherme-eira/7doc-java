package service;

import model.Content;
import model.Movie;
import model.Series;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlGenerator {
    private FileWriter writer;

    public HtmlGenerator(FileWriter writer) {
        this.writer = writer;
    }

    public void generate(List<? extends Content> contentList) throws IOException {

        String htmlTitle;

        if (contentList.getFirst() instanceof Movie) htmlTitle = "TOP 250 MELHORES FILMES";
        else if (contentList.getFirst() instanceof Series) htmlTitle = "TOP 250 MELHORES SÉRIES";
        else htmlTitle = "QUADRINHOS POPULARES";

        String head =
                """
                  <head>
                        <meta charset="utf-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
                        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
                   </head>
                """;

        String startOfBody =
                "<body style=\"background: rgb(2,0,36); background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(6,6,120,1) 35%, rgba(0,212,255,1) 100%); color: white\">" +
                        "<h1 class=\"mb-5 px-5 pt-5 text-center\">" + htmlTitle + "</h1>" +
                        "<ul class=\"d-flex flex-column align-items-center pt-5\">";

        StringBuilder contentItem = new StringBuilder();
        contentList.forEach(c -> {
            contentItem.append(
                    "           <li class=\"d-flex flex-row mb-5\" style=\"width: 50%\"> \n" +
                    "               <img style=\"border: 5px solid white;\" src=\"" + c.urlImage() + "\" width=\"250px\"> \n" +
                    "               <div class=\"d-flex flex-column justify-content-center p-5\"> \n" +
                    "                   <h2 class=\"mb-4\">"+ c.title() +"</h2>\n" +
                    "                   <h5>Nota Média: " + c.rating() + "</h5>\n" +
                    "                   <h5>Ano de Lançamento: " + c.year() + "</h5>\n" +
                    "               </div>\n" +
                    "           </li>\n"
            );
        });

        String endOfBody =
                """
                        </ul>
                    </body>
                """;

        String completeHTML =
                "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + head + startOfBody + contentItem + endOfBody + "</html>";

        writer.write(completeHTML);
    }
}
