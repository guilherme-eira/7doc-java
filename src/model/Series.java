package model;

public class Series implements Content {
    private String title;
    private String urlImage;
    private String rating;
    private String year;

    public Series(String title, String urlImage, String rating, String year) {
        this.title = title;
        if (urlImage.equals("null")) {
            this.urlImage = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg";
        } else {
            this.urlImage = "https://image.tmdb.org/t/p/w500" + urlImage.replace("\"", "");
        }
        this.rating = rating;
        this.year = year.split("-")[0];
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
