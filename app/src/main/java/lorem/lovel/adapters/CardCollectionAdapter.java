package lorem.lovel.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import lorem.lovel.R;
import lorem.lovel.models.CardModel;

/**
 * Created by mrgn on 13/12/2016.
 *
 */

public class CardCollectionAdapter
        extends RecyclerView.Adapter<CardCollectionAdapter.CardCollectionViewHolder>{

    private List<CardModel> cardList;

    public CardCollectionAdapter(List<CardModel> cardList) {
        this.cardList = cardList;
    }

    @Override
    public CardCollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.cardcollection_cardview, parent, false);
        return new CardCollectionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardCollectionViewHolder holder, int position) {
        final CardModel cm = cardList.get(position);
        holder.vText.setText(Html.fromHtml(cm.getHtml()));

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), cm.getText(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.cardList.size();
    }

    public static class CardCollectionViewHolder extends RecyclerView.ViewHolder {
        protected TextView vText;

        public CardCollectionViewHolder(View v){
            super(v);
            vText = (TextView) v.findViewById(R.id.cardcollection_cardview_cardtext);
        }
    }
}
