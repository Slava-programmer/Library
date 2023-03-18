package classes;

import java.util.ArrayList;
import java.util.List;

public class Visitor {
    private final String name;
    private int id = 0;
    private List<String> booksInUse;


    public Visitor(String name) {
        this.name = name;
        this.booksInUse = new ArrayList<>();
    }

    public String setName() {
        return name;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList <String > getBooksInUse() {
        return (ArrayList<String>) booksInUse;
    }

    public void setBooksInUse(String book) {
        booksInUse.add(book);

    }
}
