package lorem.lovel.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lorem.lovel.R;
import lorem.lovel.models.CardModel;

/**
 * Created by mrgn on 13/12/2016.
 *
 */

public class BigCountryCardAdapter extends RecyclerView.Adapter<BigCountryCardAdapter.CountryCardViewHolder> {

    private List<CardModel> countryList;
    private Context context;

    public BigCountryCardAdapter(List<CardModel> countryList, Context pContext) {
        this.countryList = countryList;
        context = pContext;
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
    public int getItemCount() {
        return this.countryList.size();
    }

    public static class CountryCardViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected ImageView vImage;

        public CountryCardViewHolder(View v){
            super(v);
            vName = (TextView) v.findViewById(R.id.bigcountry_name);
            vImage = (ImageView) v.findViewById(R.id.bigcountry_image);
        }
    }
}
