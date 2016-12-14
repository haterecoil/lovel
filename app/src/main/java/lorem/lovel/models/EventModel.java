package lorem.lovel.models;

/**
 * Created by mrgn on 13/12/2016.
 *
 */

public class EventModel {
    private String name;
    private String place;
    public String text;
    private final String META_SEPARATOR = " – ";

    public EventModel(String pName, String pPlace) {
        name = pName;
        place = pPlace;
        text = getText();
    }

    public String getText(){ return name + META_SEPARATOR + place; }
}
