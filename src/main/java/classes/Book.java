package classes;

public class Book {
    private String name;
    private String author;
    private String readsBook;
    private boolean issue = false;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReadsBook() {
        return readsBook;
    }

    public void setReadsBook(String readsBook) {
        this.readsBook = readsBook;
    }

    public boolean isIssue() {
        return issue;
    }

    public void setIssue(boolean issue) {
        this.issue = issue;
    }
}
