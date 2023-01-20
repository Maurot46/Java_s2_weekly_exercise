package Database;

import java.time.LocalDate;

public class Libri extends Biblioteca{
    private String autore;
    private String genere;
    public Libri(long isbn, String title, LocalDate anno_pubblicazione, int num_pages, String autore, String genere) {
        this.isbn = isbn;
        this.title = title;
        this.anno_pubblicazione = anno_pubblicazione;
        this.num_pages = num_pages;
        this.autore = autore;
        this.genere = genere;
    }
    @Override
    public long getIsbn() {
        return this.isbn;
    }

    @Override
    String getTitle() {
        return this.title;
    }

    @Override
    LocalDate getAnno_pubblicazione() {
        return this.anno_pubblicazione;
    }

    @Override
    public int getNum_pages() {
        return this.num_pages;
    }
    public String getAutore(){
        return this.autore;
    }
    public String getGenere(){
        return this.genere;
    }
}
