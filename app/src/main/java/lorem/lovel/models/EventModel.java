package lorem.lovel.models;

/**
 * Created by mrgn on 13/12/2016.
 *
 */

public class EventModel {
    private String name;
    private String place;
    private String date;
    private int attendants;

    public String text;
    private final String META_SEPARATOR = " – ";

    public EventModel(String pName, String pPlace, String pDate) {
        name = pName;
        place = pPlace;
        date = pDate;

        text = getText();
    }

    public EventModel(String pName, String pPlace) {
        name = pName;
        place = pPlace;

        text = getText();
    }

    public String getText(){ return name + META_SEPARATOR + place; }
    public String getDate(){ return date; }
}
