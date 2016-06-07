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

import java.util.ArrayList;

/**
 * Created by Eugene on 5/29/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<ItemData> mDataset;
    private int mLayout_id;
    Context context;
    OverrideFont o = new OverrideFont();

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtMon;
        public TextView txtDay;
        public TextView txtTemp;
        public ImageView imgWeather;

        public ViewHolder(View v) {
            super(v);
            txtMon = (TextView) v.findViewById(R.id.tvMon);
            txtDay = (TextView) v.findViewById(R.id.tvDay);
            txtTemp = (TextView) v.findViewById(R.id.tvTemp);
            imgWeather = (ImageView) v.findViewById(R.id.img_weather);
        }
    }

    public void add(int position, ItemData item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

    public void remove(ItemData item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<ItemData> myDataset, int layout_id) {
        mDataset = myDataset;
        mLayout_id = layout_id;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v;
        context = parent.getContext();
        if (mLayout_id == 1) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_forecast_layout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            v.getLayoutParams().width = parent.getWidth() / 4;
            LinearLayout daily_forecast_layout = (LinearLayout)v.findViewById(R.id.daily_forecast_layout);

            o.overrideFont(context, daily_forecast_layout);
        } else  {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_forecast_layout, parent, false);
            v.getLayoutParams().height = parent.getHeight() / 5;
            LinearLayout hourly_forecast_layout = (LinearLayout)v.findViewById(R.id.hourly_forecast_layout);
            o.overrideFont(context,hourly_forecast_layout);
            overrideColor(context, hourly_forecast_layout);
        }


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.txtMon.setText(mDataset.get(position).getWeek_day());

        holder.txtDay.setText(mDataset.get(position).getMonth_day());
        holder.txtTemp.setText(mDataset.get(position).getTemp());
//        Resources resources = get
        int id = R.drawable.ic_drizzle;
        int id2 = mDataset.get(position).getImageId();
        holder.imgWeather.setImageResource(id2);
        //holder.imgWeather.setImageResource(mDataset.get(position).getImageId());
        if (R.drawable.ic_drizzle == mDataset.get(position).getImageId()) {
            Log.d("K", "Compare OK");
        }
    }
    @Override
    public int getItemCount() {
        Log.i("Error", "Size: " + mDataset.size());
        return mDataset.size();

    }

    private void overrideColor(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideColor(context, child);
                }
            } else if (v instanceof ImageView) {
                ((ImageView) v).setColorFilter(Color.parseColor("#FFFFFF"));
            }
        } catch (Exception e) {
        }
    }

}
