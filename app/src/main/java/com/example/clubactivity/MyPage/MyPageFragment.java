package com.example.clubactivity.MyPage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clubactivity.Club.ChatViewAdapter;
import com.example.clubactivity.Constants;
import com.example.clubactivity.Network.ImageConverter;
import com.example.clubactivity.Network.NetworkTask;
import com.example.clubactivity.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

//import kr.go.seoul.culturalevents.CulturalEventTypeMini;


public class MyPageFragment extends Fragment {
    private View view;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    CircleImageView user_image;
    TextView user_nickname;
    TextView user_residence;

    NetworkTask networkTask;

    ImageButton EditInfoButton;
//    private static final String TAG = "MainActivity";
//    private ArrayList<String> mNames = new ArrayList<>();
//    private ArrayList<String> mImageUrls = new ArrayList<>();
//    private ArrayList<String> mNames_favorite = new ArrayList<>();
//    private ArrayList<String> mImageUrls_favorite = new ArrayList<>();

    private String key = "454d786d61636d6539384a4c625954";
   // private CulturalEventTypeMini typeMini;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_page, container, false);

        EditInfoButton = (ImageButton) view.findViewById(R.id.edit_profile);
        EditInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EnterEditInfoActivity.class);
                intent.putExtra("isInstructor", false );
                startActivityForResult(intent, Constants.REQUEST_EDIT_INFO); // 요청한 곳을 구분하기 위한 숫자, 의미없음
            }
        });


        //마이페이지 내 정보 세팅
        preferences = getContext().getSharedPreferences("preferences", getContext().MODE_PRIVATE);
        editor = preferences.edit();

        user_image = (CircleImageView)view.findViewById(R.id.user_image);
        user_nickname = view.findViewById(R.id.user_nickname);
        user_residence = view.findViewById(R.id.user_residence);

        if(ImageConverter.getImageToBitmap(preferences.getString("profileImage", "")) != null)
            user_image.setImageBitmap(getImageToBitmap(preferences.getString("profileImage", "")));
        else{
            user_image.setImageResource(R.drawable.ic_person_24dp);
        }
        user_nickname.setText(preferences.getString("nickname", ""));
        user_residence.setText(preferences.getString("residence",""));

        //API 안먹음
        //typeMini = (CulturalEventTypeMini) view.findViewById(R.id.type_mini);
        //typeMini.setOpenAPIKey(key);

        TabHost tabHost1 = (TabHost) view.findViewById(R.id.tapHost_myclasslist) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1_myclass) ;
        ts1.setIndicator("예약한 클래스") ;
        tabHost1.addTab(ts1)  ;


        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2_favoriteclass) ;
        ts2.setIndicator("찜한 클래스") ;
        tabHost1.addTab(ts2) ;

        //getImages();
        initRecyclerView();
        return view;
    }

//    private void getImages(){
//        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
//
//        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
//        mNames.add("명화 그리기");
//
//        mImageUrls_favorite.add("http://image.chosun.com/sitedata/image/201905/21/2019052101147_0.jpg");
//        mNames_favorite.add("축구 소모임");
//
//        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
//        mNames.add("프랑스 자수");
//
//        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
//        mNames.add("원데이 쿠킹");
//
//        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
//        mNames.add("Rocky Mountain National Park");
//
//
//        initRecyclerView();
//    }

    public Bitmap getImageToBitmap(String encodedImage){

        byte[] decodedByte = Base64.decode(encodedImage, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }


    private void initRecyclerView(){
//        Log.d(TAG, "initRecyclerView: init recyclerview");

//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        RecyclerView recyclerView = view.findViewById(R.id.myclass_recyclerView);
//        recyclerView.setLayoutManager(layoutManager);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity());
//        //RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), mNames, mImageUrls);
//        recyclerView.setAdapter(adapter);
//
//        LinearLayoutManager layoutManager_favorite = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        RecyclerView recyclerView_favorite = view.findViewById(R.id.favorite_recyclerView);
//        recyclerView_favorite.setLayoutManager(layoutManager_favorite);
//        //RecyclerViewAdapter adapter_favorite = new RecyclerViewAdapter(getActivity(), mNames_favorite, mImageUrls_favorite);
//        RecyclerViewAdapter adapter_favorite = new RecyclerViewAdapter(getActivity());
//        recyclerView_favorite.setAdapter(adapter_favorite);

        String url = "http://106.10.35.170/ImportFavoriteClass.php";
        String data = "email=" + preferences.getString("email", "");
        networkTask = new NetworkTask(this.getContext(), url, data, Constants.SERVER_GET_FAVORITE_CLASS);
        networkTask.execute();

        url = "http://106.10.35.170/ImportMyClass.php";
        networkTask = new NetworkTask(this.getContext(), url, data, Constants.SERVER_GET_MY_CLASS);
        networkTask.execute();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_EDIT_INFO){
            if(ImageConverter.getImageToBitmap(preferences.getString("profileImage", "")) != null)
                user_image.setImageBitmap(getImageToBitmap(preferences.getString("profileImage", "")));
            else{
                user_image.setImageResource(R.drawable.ic_person_24dp);
            }
            user_nickname.setText(preferences.getString("nickname", ""));
            user_residence.setText(preferences.getString("residence",""));
        }

        if(requestCode == Constants.REQUEST_ENTER_CLASS_DETAIL) {
            String url = "http://106.10.35.170/ImportFavoriteClass.php";
            String dataStr = "email=" + preferences.getString("email", "");
            networkTask = new NetworkTask(this.getContext(), url, dataStr, Constants.SERVER_GET_FAVORITE_CLASS);
            networkTask.execute();

            url = "http://106.10.35.170/ImportMyClass.php";
            networkTask = new NetworkTask(this.getContext(), url, dataStr, Constants.SERVER_GET_MY_CLASS);
            networkTask.execute();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        String url = "http://106.10.35.170/ImportFavoriteClass.php";
        String dataStr = "email=" + preferences.getString("email", "");
        networkTask = new NetworkTask(this.getContext(), url, dataStr, Constants.SERVER_GET_FAVORITE_CLASS);
        networkTask.execute();

        url = "http://106.10.35.170/ImportMyClass.php";
        networkTask = new NetworkTask(this.getContext(), url, dataStr, Constants.SERVER_GET_MY_CLASS);
        networkTask.execute();
    }
}
