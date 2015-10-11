package bjsbuzz.com.rocketbuzz.pojo;

/**
 * Created by pramo_000 on 11-10-2015.
 */
public class DataPojoBJS {

    private String items;
    private String titles;
    private String descriptions;

    public void setItems(String items) {
        this.items = items;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setAuthors_name(String authors_name) {
        this.authors_name = authors_name;
    }

    public String getItems() {
        return items;
    }

    public String getTitles() {
        return titles;
    }

    public String getDescriptions() {
        return descriptions;
    }

    @Override
    public String toString() {
        return "DataPojoBJS{" +
                "items='" + items + '\'' +
                ", titles='" + titles + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", authors_name='" + authors_name + '\'' +
                '}';
    }

    public String getAuthors_name() {
        return authors_name;
    }

    private String authors_name;

}
