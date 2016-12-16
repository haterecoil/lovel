package lorem.lovel.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import lorem.lovel.R;
import lorem.lovel.models.CardModel;

/**
 * Created by mrgn on 13/12/2016.
 *
 */

public class CardCollectionAdapter
        extends RecyclerView.Adapter<CardCollectionAdapter.CardCollectionViewHolder>{

    private final Context context;
    private List<CardModel> cardList;

    public CardCollectionAdapter(List<CardModel> cardList, Context context) {
        this.cardList = cardList;
        this.context = context;
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
        holder.vName.setText(cm.getText());
        holder.vPlace.setText(cm.getPlace());

        Glide.with(context)
                .load(cm.getIllustrationPath(true))
                .placeholder(R.drawable.progress_animation)
                .centerCrop()
                .into(holder.vImage);


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), cm.getTargetClass());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.cardList.size();
    }

    public static class CardCollectionViewHolder extends RecyclerView.ViewHolder {
        protected ImageView vImage;
        protected TextView vName;
        protected TextView vPlace;

        public CardCollectionViewHolder(View v){
            super(v);
            vName = (TextView) v.findViewById(R.id.cardcollection_cardview_cardtext);
            vPlace = (TextView) v.findViewById(R.id.cardcollection_cardview_cardplace);
            vImage = (ImageView) v.findViewById(R.id.cardcollection_cardview_cardimage);
        }
    }
}
