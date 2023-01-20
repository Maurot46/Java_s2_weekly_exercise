package Database;

import java.time.LocalDate;

public class Riviste extends Biblioteca{
    String[] periodicità;
    public Riviste(long isbn, String title, LocalDate anno_pubblicazione, int num_pages, String[] periodicità){
        this.isbn = isbn;
        this.title = title;
        this.anno_pubblicazione = anno_pubblicazione;
        this.num_pages = num_pages;
        this.periodicità = periodicità;
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
    public String[] getPeriodicità() {
        return this.periodicità;
    }

}
