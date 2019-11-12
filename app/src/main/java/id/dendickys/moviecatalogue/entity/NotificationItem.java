package id.dendickys.moviecatalogue.entity;

public class NotificationItem {
    private int id;
    private String title;
    private String overview;

    public NotificationItem(int id, String title, String overview) {
        this.id = id;
        this.title = title;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
