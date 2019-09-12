package com.example.clubactivity.Class;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clubactivity.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<Item> items;
    int item_layout;

    public RecyclerAdapter(Context context, List<Item> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_list_item, parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = items.get(position);
       /* Drawable drawable = new BitmapDrawable(item.getImage());
        holder.image.setBackground(drawable);*/

       //이미지 이렇게 넘겨줘야해 bytes
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        item.getImage().compress(Bitmap.CompressFormat.PNG, 100, stream);
//        final byte[] bytes = stream.toByteArray();

        Glide.with(context)
                .load(item.getImage())
                .into(holder.image);

        //holder.image.setImageBitmap(item.getImage());
        if(item.getFlag_dongnae() == 0) {
            holder.dongnae.setText(" 개인 클래스 ");
            holder.dongnae.setBackgroundColor(Color.parseColor("#8013B9A5"));
        }
        else {
            holder.dongnae.setText(" 동네 배움터 ");
            holder.dongnae.setBackgroundColor(Color.parseColor("#80F78181"));
        }
        holder.title.setText(item.getTitle());
        holder.desc.setText(item.getDesc());
        holder.ratingBar.setRating(item.getStar());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ClassDetailActivity.class);

                intent.putExtra("param", item.getTitle()); //클래스 제목을 뿌려준다.
                intent.putExtra("image",item.getImage()); //클래스 이미지 뿌리기
                intent.putExtra("area",item.getArea()); //클래스 지역구
                intent.putExtra("star",item.getStar()); //평점 뿌리기

                intent.putExtra("desc",item.getDesc()); //클래스 설명
                intent.putExtra("people",item.getPeople()); //클래스 대상
                intent.putExtra("location",item.getLocation()); //클래스 정확한 장소
                intent.putExtra("date",item.getDate()); //클래스 시간
                intent.putExtra("number_now",item.getPeopelNumberNow()); //현재 인원수
                intent.putExtra("number",item.getPeopleNumber()); //클래스 인원수
                intent.putExtra("price",item.getPrice()); //가격
                intent.putExtra("favorite", item.getFavorite());
                intent.putExtra("class_index",item.getClass_index()); //클래스 인덱스


                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView desc;
        RatingBar ratingBar;
        CardView cardview;
        TextView dongnae;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.class_list_image);
            Log.d(""+image.getWidth(),"");
            Log.d(""+image.getHeight(),"");

            title = (TextView) itemView.findViewById(R.id.class_list_title);
            desc = (TextView) itemView.findViewById(R.id.class_list_sogae);
            ratingBar = (RatingBar) itemView.findViewById(R.id.review_star);
            cardview = (CardView) itemView.findViewById(R.id.class_list_cardview);
            dongnae = (TextView) itemView.findViewById(R.id.class_event);
        }
    }
}
