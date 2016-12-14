package lorem.lovel.models;

/**
 * Created by mrgn on 13/12/2016.
 *
 */
public class CardModel {
    public String name;
    public String place;

    public CardModel(String name, String place) {
        this.name = name;
        this.place = " – "+place;
    }
    public CardModel(String name) {
        this.name = name;
        this.place = "";
    }

    public String getText() {
        return this.name + this.place;
    }

    public String getHtml() {
        return "<b>" + this.name + "</b>" + this.place;
    }

}
