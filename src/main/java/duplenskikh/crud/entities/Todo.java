package duplenskikh.crud.entities;

public class Todo implements Persistable {
    private final String title;
    private final String text;

    public Todo(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String toString() {
        return String.format("<div>Title: %s. Text: %s</div>", title, text);
    }

    public boolean isEmpty() {
        return (title.isEmpty() && text.isEmpty());
    }
}
