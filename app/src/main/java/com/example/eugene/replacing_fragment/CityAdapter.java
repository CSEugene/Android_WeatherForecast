package com.example.eugene.replacing_fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugene on 6/5/2016.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private ArrayList<ItemDataCity> mDataset;

    Context context;
    OverrideFont o = new OverrideFont();

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtCity;
        //        public TextView txtDay;
        public TextView txtTemp;
        public ImageView imgWeather;

        public ViewHolder(View v) {
            super(v);
            txtCity = (TextView) v.findViewById(R.id.tvCityName);
//            txtDay = (TextView) v.findViewById(R.id.tvDay);
            txtTemp = (TextView) v.findViewById(R.id.tv_city_temp);
            imgWeather = (ImageView) v.findViewById(R.id.img_city_weather);
        }
    }

    public void add(int position, ItemDataCity item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

    public void remove(ItemDataCity item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CityAdapter(ArrayList<ItemDataCity> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v;
        context = parent.getContext();
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_weather_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        v.getLayoutParams().width = parent.getWidth() / 4;
        RecyclerView.LayoutParams l = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(l);

        LinearLayout city_weather_layout = (LinearLayout) v.findViewById(R.id.city_weather_layout);

        o.overrideFont(context, city_weather_layout);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.txtCity.setText(mDataset.get(position).getCityName());

//        holder.txtDay.setText(mDataset.get(position).getMonth_day());
        holder.txtTemp.setText(mDataset.get(position).getTemp());
////        Resources resources = get
//        int id = R.drawable.ic_drizzle;
        int id2 = mDataset.get(position).getImageId();
        holder.imgWeather.setImageResource(id2);
//        //holder.imgWeather.setImageResource(mDataset.get(position).getImageId());
//        if (R.drawable.ic_drizzle == mDataset.get(position).getImageId()) {
//            Log.d("K", "Compare OK");
//        }
    }

    @Override
    public int getItemCount() {
        Log.i("Error", "Size: " + mDataset.size());
        return mDataset.size();

    }

    private List<ItemDataCity> mCityModel;

    public void setFilter(List<ItemDataCity> cityModel) {
        mCityModel = new ArrayList<>();
        mCityModel.addAll(cityModel);
        notifyDataSetChanged();
    }

}