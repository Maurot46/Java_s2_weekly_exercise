import Database.AutoreNonTrovato;
import Database.Libri;
import com.sun.jdi.StringReference;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Aggiunta libri
        Libri l1 = new Libri
                (232134,"Figli della libertà", LocalDate.of(2023,1,8),62,"Paullina Simons","Narrativa erotica e rosa");
        Libri l2 = new Libri
                (232135,"La vita intima", LocalDate.of(2023,1,1),48,"Einaudi","Narrativa Italiana");
        Libri l3 = new Libri
                (232136,"Mezzamela. La bellezza di amarsi alla pari", LocalDate.of(2023,1,6),124,"Salani","Bambini e ragazzi");
        Libri l4 = new Libri
                (232137,"I Promessi sposi", LocalDate.of(2023,1,12),541,"Alessandro Manzoni","Narrativa italiana");
        Libri l5 = new Libri
                (232138,"La mente del bambino", LocalDate.of(2023,1,15),246,"Maria Montessori","Psicologia");
        Libri l6 = new Libri
                (232139,"Un contadino nella metropoli", LocalDate.of(2023,1,20),1480,"Prospero Gallinari","Biografia");
        //-------------------------------------------------------------------------------------------
        //Generazione file biblioteca
        try {
            File myObj = new File("biblioteca.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //Scrittura su file

        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleziona:-----------------------------------------");
        System.out.println(0+ "- Aggiungi file alla biblioteca");
        System.out.println(1+ "- Rimuovi File dalla bibliboteca");
        System.out.println(2+ "- Ricerca file per ISBN");
        System.out.println(3+ "- Ricerca file per anno di pubblicazione");
        System.out.println(4+ "- Ricerca file per autore");
        System.out.println(5+ "- Visualizza tutti i file della biblioteca");
        Integer s1 = Integer.valueOf(scanner.nextLine());
        switch(s1){
            case 0:
                try {
                    FileOutputStream fos = new FileOutputStream("biblioteca.txt", true);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    Scanner sc = new Scanner(System.in);
                    int input = -1;
                    Integer isbn = null;
                    LocalDate date = null;
                    Integer num_pages = null;
                    while (input != 0) {
                        System.out.println("Inserisci un libro (0 per uscire): ");
                        //Gestione errore lettere invece che numeri
                        for(int i = 0; i< 1;){
                            try{
                                System.out.println("Codice ISBN: ");
                                isbn = Integer.valueOf(sc.nextLine());

                                i++;
                            }catch (NumberFormatException e){
                                System.out.println("Devi inserire un numero");
                            }
                        }
                        if (isbn.equals(0))break;
                        System.out.println("Titolo: ");
                        String title = sc.nextLine();
                        //Gestione errore data in formato sbagliato
                        for(int i = 0; i<1;) {
                            try {
                                System.out.println("Data di pubblicazione: ");
                                date = LocalDate.parse(sc.nextLine());
                                i++;
                            }catch (DateTimeException e) {
                                System.out.println("Inserire data in questo formato anno-mese-giorno");
                            }
                        }
                        //Gestione errore numero pagine in formato sbagliato
                        for (int i = 0; i < 1;){
                            try{
                                System.out.println("Numero pagine: ");
                                num_pages = sc.nextInt();
                                i++;
                            }catch (NumberFormatException e){
                                System.out.println("Devi inserire un numero");
                            }
                        }
                        sc.nextLine();
                        //Aggiunta autore
                        System.out.println("Autore: ");
                        String autore = sc.nextLine();
                        //Aggiunta genere
                        System.out.println("Genere: ");
                        String genere = sc.nextLine();

                        String book = "\n" + "ISBN-" + isbn + "-" + title + "-"+"Anno-" + date + "-"+"numero di pagine:" + num_pages + "-"+"autore:" + autore + "-" + genere;
                        osw.write(book);
                    }
                    osw.close();
                    fos.close();
                    System.out.println("Scrittura completata!");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case 1:
                try {
                    //Ricevo il file in input
                    FileInputStream fis = new FileInputStream("biblioteca.txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    ArrayList<String> lines = new ArrayList<>();
                    String line;
                    while((line = br.readLine()) != null) {
                        lines.add(line);
                    }
                    br.close();
                    fis.close();
                    //Apro lo scanner per ricever il codice isbn
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Inserisci il codice ISBN del libro da rimuovere: ");
                    String isbn = sc.nextLine();

                    boolean found = false;
                    for(int i = 0; i < lines.size(); i++) {
                        if(lines.get(i).startsWith(isbn)) {
                            lines.remove(i);
                            found = true;
                            break;
                        }
                    }
                    if(!found) {
                        System.out.println("Libro non trovato.");
                        return;
                    }
                    FileOutputStream fos = new FileOutputStream("biblioteca.txt");
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    for(String l : lines) {
                        osw.write(l + "\n");
                    }
                    osw.close();
                    fos.close();
                    System.out.println("Rimozione completata!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.print("Inserisci il codice ISBN: ");
                    String isbn = scanner.nextLine();

                    try {
                        Files.lines(Paths.get("biblioteca.txt"))
                                .filter(line -> line.contains(isbn))
                                .forEach(System.out::println);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }catch (NumberFormatException e){
                    System.out.println("Codice isbn non trovato");
                }
                break;
            case 3:
                try {
                    Scanner scanner3 = new Scanner(System.in);
                    System.out.print("Inserisci l'anno di pubblicazione: ");
                    String anno = scanner.nextLine();

                    try {
                        Files.lines(Paths.get("biblioteca.txt"))
                                .filter(line -> line.contains(anno))
                                .forEach(System.out::println);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }catch (NumberFormatException e){
                    System.out.println("Anno non inserito correttamente formato consentito annno-mese-giorno(2023-10-01)");
                }
                break;
            case 4:
                try {
                    Scanner scanner4 = new Scanner(System.in);
                    System.out.print("Inserisci l'Autore del libro: ");
                    String autore = scanner.nextLine();

                    try {
                        Files.lines(Paths.get("biblioteca.txt"))
                                .filter(line -> line.contains(autore))
                                .forEach(System.out::println);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }catch (NumberFormatException e){
                    System.out.println("Devi inserire una stringa");
                }
                break;
            case 5:
                try {
                    Files.lines(Paths.get("biblioteca.txt"))
                            .forEach(System.out::println);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Errore");

        }
    }
}