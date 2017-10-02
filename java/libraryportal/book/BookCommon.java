package libraryportal.book;

import libraryportal.user.Writer;

public class BookCommon implements Book {

    private final String name;
    private final String genre;
    private final Writer author;
    private Rating rating;

    BookCommon(String name, String genre, Writer author) {
        this.name = name;
        this.genre = genre;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookCommon)) return false;

        BookCommon that = (BookCommon) o;

        if (!name.equals(that.name)) return false;
        if (!genre.equals(that.genre)) return false;
        return author.equals(that.author);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public Writer getAuthor() {
        return author;
    }

    public Rating getRating() {
        return rating;
    }
}
