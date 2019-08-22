package com.example.clubactivity.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.clubactivity.Class.ClassDetailActivity;
import com.example.clubactivity.Class.ClassDetailIntroduction;
import com.example.clubactivity.R;

import java.util.List;

public class Adapter extends PagerAdapter {

    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        desc.setText(models.get(position).getDesc());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("param", models.get(position).getTitle()); //제목 뿌려주기
                context.startActivity(intent);
                // finish();*/

                Intent intent = new Intent(context, ClassDetailActivity.class);



                intent.putExtra("param", models.get(position).getTitle()); //클래스 제목을 뿌려준다.
                intent.putExtra("image",models.get(position).getImage()); //클래스 이미지 뿌리기
                intent.putExtra("area",models.get(position).getArea()); //클래스 지역구

                intent.putExtra("desc",models.get(position).getDesc()); //클래스 설명
                intent.putExtra("people",models.get(position).getPeople()); //클래스 대상
                intent.putExtra("location",models.get(position).getLocation()); //클래스 정확한 장소
                intent.putExtra("date",models.get(position).getDate()); //클래스 시간
                intent.putExtra("number",models.get(position).getPeopleNumber()); //클래스 인원수

                ClassDetailIntroduction classDetailIntroduction = new ClassDetailIntroduction();
                Bundle bundle = new Bundle();

                bundle.putString("desc",models.get(position).getDesc()); //클래스 설명
                bundle.putString("people",models.get(position).getPeople()); //클래스 대상
                bundle.putString("location",models.get(position).getLocation()); //클래스 정확한 장소
                bundle.putString("date",models.get(position).getDate()); //클래스 시간
                bundle.putString("number",models.get(position).getPeopleNumber()); //클래스 인원수

                classDetailIntroduction.setArguments(bundle);


                context.startActivity(intent);
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
