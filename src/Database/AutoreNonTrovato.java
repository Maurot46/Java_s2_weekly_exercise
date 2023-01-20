package Database;

public class AutoreNonTrovato extends Exception {
    String str1;
    AutoreNonTrovato(String str2){
        str1 = str2;
    }
    public String toString(){
        return ("MyException Occurred: "+str1) ;
    }
}
