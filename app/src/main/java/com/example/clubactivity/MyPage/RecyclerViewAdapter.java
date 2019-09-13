package com.example.clubactivity.MyPage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clubactivity.Class.ClassDetailActivity;
import com.example.clubactivity.Class.Item;
import com.example.clubactivity.Constants;
import com.example.clubactivity.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Item> items = new ArrayList<Item>();
    //vars
    private ArrayList<String> mNames;
    private ArrayList<String> mImageUrls;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> imageUrls) {
        mNames = names;
        mImageUrls = imageUrls;
        mContext = context;
    }

    public RecyclerViewAdapter(Context context, ArrayList<Item> items) {
        mContext = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_class_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        final Item item = items.get(position);

        Glide.with(mContext)
                .load(items.get(position).getImage())
                .into(holder.image);

        holder.name.setText(items.get(position).getTitle());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ClassDetailActivity.class);
                //Intent intent = new Intent(context, TabTest.class);

                intent.putExtra("param", item.getTitle());
                intent.putExtra("desc", item.getDesc());
                intent.putExtra("image",item.getImage());
                intent.putExtra("people", item.getPeople());
                intent.putExtra("location", item.getLocation());
                intent.putExtra("date", item.getDate());
                intent.putExtra("number_now",item.getPeopleNumberNow());
                intent.putExtra("number", item.getPeopleNumber());
                intent.putExtra("price", item.getPrice());
                intent.putExtra("favorite", item.getFavorite());
                intent.putExtra("class_index", item.getClass_index());
                intent.putExtra("area",item.getArea());

                ((Activity) mContext).startActivityForResult(intent, Constants.REQUEST_ENTER_CLASS_DETAIL);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
        }
    }
}