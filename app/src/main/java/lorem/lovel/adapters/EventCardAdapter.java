package lorem.lovel.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lorem.lovel.R;
import lorem.lovel.models.EventModel;

/**
 * Created by mrgn on 13/12/2016.
 *
 */

public class EventCardAdapter extends RecyclerView.Adapter<EventCardAdapter.EventCardViewHolder> {

    private List<EventModel> eventsList;

    public EventCardAdapter(List<EventModel> eventsList) {
        this.eventsList = eventsList;
    }

    @Override
    public EventCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.event_cardview, parent, false);

        return new EventCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventCardViewHolder holder, int position) {
        EventModel em = eventsList.get(position);
        holder.vText.setText(em.text);
    }

    @Override
    public int getItemCount() {
        return this.eventsList.size();
    }

    public static class EventCardViewHolder extends RecyclerView.ViewHolder {
        protected TextView vText;

        public EventCardViewHolder(View v){
            super(v);
            vText = (TextView) v.findViewById(R.id.event_text);
        }

    }
}
