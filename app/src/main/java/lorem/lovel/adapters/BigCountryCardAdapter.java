package lorem.lovel.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lorem.lovel.R;
import lorem.lovel.models.CountryModel;

/**
 * Created by mrgn on 13/12/2016.
 *
 */

public class BigCountryCardAdapter extends RecyclerView.Adapter<BigCountryCardAdapter.CountryCardViewHolder> {

    private List<CountryModel> countryList;

    public BigCountryCardAdapter(List<CountryModel> countryList) {
        this.countryList = countryList;
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
        CountryModel cm = countryList.get(position);
        holder.vName.setText(cm.name);
    }

    @Override
    public int getItemCount() {
        return this.countryList.size();
    }

    public static class CountryCardViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;

        public CountryCardViewHolder(View v){
            super(v);
            vName = (TextView) v.findViewById(R.id.bigcountry_name);
        }
    }
}
