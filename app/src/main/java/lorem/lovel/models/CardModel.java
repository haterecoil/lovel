package lorem.lovel.models;

import android.icu.text.DateFormat;
import android.net.Uri;
import android.util.Log;

import lorem.lovel.CountryActivity;
import lorem.lovel.EventActivity;

/**
 * Created by mrgn on 13/12/2016.
 *
 */
public class CardModel {
    protected String name = "";
    protected String place = "";
    protected String date = "";
    protected String imageUrl = "";
    protected int attendants = 0;
    protected int cardType;
    protected Class cardTarget;

    public final static int TYPE_EVENT = 0;
    public final static int TYPE_COUNTRY = 1;
    public final static Class TYPE_EVENT_CLASS = EventActivity.class;
    public final static Class TYPE_COUNTRY_CLASS = CountryActivity.class;

    protected Class TARGET_ACTIVITY;

    public CardModel(){}

    public CardModel setName(String pName){
        name = pName;
        return this;
    }
    public CardModel setPlace(String pPlace){
        place = " – " + pPlace;
        return this;
    }
    public CardModel setDate(String pDate){
        date = pDate;
        return this;
    }
    public CardModel setAttendants(int pAttendants){
        attendants = pAttendants;
        return this;
    }

    public CardModel setCardType(int pCardType){
        switch(pCardType){
            case TYPE_EVENT: cardType = TYPE_EVENT; cardTarget = TYPE_EVENT_CLASS; break;
            case TYPE_COUNTRY: cardType = TYPE_COUNTRY; cardTarget = TYPE_COUNTRY_CLASS; break;
            default: cardType = TYPE_COUNTRY; cardTarget = TYPE_COUNTRY_CLASS; break;
        }
        return this;
    }

    public String getText() {
        return this.name + this.place;
    }
    public String getHtml() {return "<b>" + this.name + "</b>" + this.place;}
    public Class getTargetClass() {return cardTarget;}

    public String getPlace() {
        return place;
    }

    public Uri getIllustrationPath(Boolean thumbnail) {
        if (cardType != TYPE_COUNTRY) {
            return Uri.parse("file:///illus/progress_image.png");
        }

        if (thumbnail) {return Uri.parse("file:///android_asset/illus/159_196/"+name.toLowerCase()+".png");}
        return Uri.parse("file:///android_asset/illus/196_327/"+name.toLowerCase()+".png");
    }
}
