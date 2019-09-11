package com.example.clubactivity.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.clubactivity.Class.ClassFragment;
import com.example.clubactivity.Class.ClassList;
import com.example.clubactivity.Constants;
import com.example.clubactivity.Network.NetworkTask;
import com.example.clubactivity.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH>{



    private Context context;
    private int mCount;
    ClassList classList;
    public SliderAdapter(Context context) {
        this.context = context;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        classList = new ClassList();
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
                if(position==0)
                {
                    Toast.makeText(context, "네트워크 연결 후 축소해 봐주세요!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, WebViewActivity.class);
                    context.startActivity(intent);
                }
                if (position==1){
                    ClassFragment.classmenuTitle = "원데이 클래스";
                    //원데이 클래스 영역
                    String data = "";
                    String url = "";
                    NetworkTask networkTask = new NetworkTask(context, url, data, Constants.SERVER_CLASS_LIST_GET);
                    networkTask.execute();

                    FragmentActivity activity = (FragmentActivity) context;
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_frame, classList).commit();
                }
                if (position==2){
                    ClassFragment.classmenuTitle = "개발 클래스";
                    //개발 클래스 영역
                    String data = "";
                    String url = "";
                    NetworkTask networkTask = new NetworkTask(context, url, data, Constants.SERVER_CLASS_LIST_GET);
                    networkTask.execute();

                    FragmentActivity activity = (FragmentActivity) context;
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_frame, classList).commit();
                }
            }
        });


        switch (position) {
            case 0:
                viewHolder.textViewDescription.setText("동네배움터란?"); //글쓰는곳
                viewHolder.textViewDescription.setTextSize(16);
                viewHolder.textViewDescription.setTextColor(Color.BLACK);
                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.dongnaebaeumteoimage) //이미지 넣는곳
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                viewHolder.textViewDescription.setText("개발을 위한 클래스들!");
                viewHolder.textViewDescription.setTextSize(16);
                viewHolder.textViewDescription.setTextColor(Color.WHITE);
                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.gaebalja)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            /*case 4:
                viewHolder.textViewDescription.setText("This is slider item " + position);
                viewHolder.textViewDescription.setTextSize(16);
                viewHolder.textViewDescription.setTextColor(Color.WHITE);
                viewHolder.imageGifContainer.setVisibility(View.GONE);
                Glide.with(viewHolder.itemView)
                        //.load("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260") //주소주기
                        .load(R.drawable.class3)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;*/
            default:
                viewHolder.textViewDescription.setTextSize(16);
                viewHolder.textViewDescription.setTextColor(Color.WHITE);
                viewHolder.textViewDescription.setText("하루면 충분한 원데이!");
                //viewHolder.imageGifContainer.setVisibility(View.VISIBLE);
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.onedaychimimoeum)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                /*Glide.with(viewHolder.itemView)
                        .asGif()
                        .load(R.drawable.oh_look_at_this)
                        .into(viewHolder.imageGifContainer);*/
                break;
        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mCount;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }

}
