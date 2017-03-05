package lorem.lovel.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lorem.lovel.App;
import lorem.lovel.R;
import lorem.lovel.models.CardModel;
import lorem.lovel.models.CountryModel;

/**
 * Created by mrgn on 13/12/2016.
 *
 */

public class BigCountryCardAdapter extends RecyclerView.Adapter<BigCountryCardAdapter.CountryCardViewHolder> {

    private List<CardModel> countryList;
    private Context context;

    public static class CountryCardViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected ImageView vImage;

        public CountryCardViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.bigcountry_name);
            vImage = (ImageView) v.findViewById(R.id.bigcountry_image);

        }
    }

    public BigCountryCardAdapter(List<CardModel> countryList, Context pContext) {
        this.countryList = countryList;
        context = pContext;
    }

    public void refresh(CardModel[] cards) {
        if(cards==null) {
            refresh(new ArrayList<CardModel>());
        } else {
            refresh(Arrays.asList(cards));
        }
    }

    public void refresh(List<CardModel> cardsList) {
        this.countryList.clear();
        this.countryList.addAll(cardsList);

        //Refresh UI
        notifyDataSetChanged();
    }


    @Override
    public CountryCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.bigcountry_cardview, parent, false);

        return new CountryCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CountryCardViewHolder holder, int position) {
        final CardModel cm = countryList.get(position);
        holder.vName.setText(cm.getText());

        Glide.with(context)
                .load(cm.getIllustrationPath(true))
                .placeholder(R.drawable.progress_animation)
                .centerCrop()
                .into(holder.vImage);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), cm.getTargetClass());
                intent.putExtra("countryName", cm.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {return countryList.size();}

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
