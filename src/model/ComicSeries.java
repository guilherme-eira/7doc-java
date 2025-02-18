package model;

public class ComicSeries implements Content {
    private String title;
    private String urlImage;
    private String rating;
    private String year;

    public ComicSeries(String title, String urlImage, String rating, String year) {
        this.title = title;
        this.urlImage = urlImage + ".jpg";
        if (rating.isEmpty()) {
            this.rating = "Sem avaliação";
        } else {
            this.rating = rating;
        }
        this.year = year;
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public String urlImage() {
        return this.urlImage;
    }

    @Override
    public String rating() {
        return this.rating;
    }

    @Override
    public String year() {
        return this.year;
    }

    @Override
    public String toString() {
        return "\n- Título: " + title +
                "\n- URL do Poster: " + urlImage +
                "\n- Avaliação: " + rating +
                "\n- Ano de Lançamento: " + year;
    }

}
