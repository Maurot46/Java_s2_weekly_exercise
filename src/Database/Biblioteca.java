package Database;

import java.time.LocalDate;

abstract class Biblioteca {
    //Proprietà
    public long isbn;
    public String title;
    public LocalDate anno_pubblicazione;
    public Integer num_pages;
    //Metodi
    abstract public long getIsbn();
    abstract String getTitle();
    abstract LocalDate getAnno_pubblicazione();
    abstract public int getNum_pages();
}
