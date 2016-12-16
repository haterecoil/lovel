package lorem.lovel.models;

import java.util.List;

/**
 * Created by mrgn on 15/12/2016.
 */

public class CalendarModel {
    public List<EventModel> mEvents;
    public String mDate;

    public CalendarModel(List<EventModel> mEvents, String mDate) {
        this.mEvents = mEvents;
        this.mDate = mDate;
    }
}
