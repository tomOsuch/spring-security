package pl.tomaszosuch.springsecuritysample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Quotation {

    private String content;
    private String author;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Quotation(String content, String author) {
        this.content = content;
        this.author = author;
    }
}
