package com.example.clubactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.clubactivity.Class.ClassFragment;
import com.example.clubactivity.Club.ClubFragment;
import com.example.clubactivity.Home.HomeFragment;
import com.example.clubactivity.Login.LoginActivity;
import com.example.clubactivity.MyPage.MyPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; //하단 네비 바
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    private ClassFragment classFragment;
    private ClubFragment clubFragment;
    private MyPageFragment myPageFragment;
    private NonLoginFragment nonLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //로그인 액티비티 죽이기
        Activity loginActivity = LoginActivity.loginActivity;
        loginActivity.finish();

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView); // 애니메이션 삭제

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_home:
                        setFragment(0);
                        break;
                    case R.id.action_class:
                        setFragment(1);
                        break;
                    case R.id.action_people:
                        if(Constants.isLogined)
                            setFragment(2);
                        else
                            setFragment(4);
                        break;
                    case R.id.action_my:
                        if(Constants.isLogined)
                            setFragment(3);
                        else
                            setFragment(4);
                        break;
                }
                return true;
            }
        });

        homeFragment = new HomeFragment();
        classFragment = new ClassFragment();
        clubFragment = new ClubFragment();
        myPageFragment = new MyPageFragment();
        nonLoginFragment = new NonLoginFragment();
        setFragment(0); //홈화면으로 지정

    }

    //프래크먼트 교체가 일어나는 곳
    private void setFragment(int menu){

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (menu){
            case 0:
                fragmentTransaction.replace(R.id.main_frame, homeFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.main_frame, classFragment);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.main_frame, clubFragment);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentTransaction.replace(R.id.main_frame, myPageFragment);
                fragmentTransaction.commit();
                break;
            case 4:
                fragmentTransaction.replace(R.id.main_frame, nonLoginFragment);
                fragmentTransaction.commit();
        }

    }

  /*  public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment).commit();
    }*/


  // 뒤로가기 버튼 입력시간이 담길 long 객체
    private long pressedTime = 0;

    // 리스너 생성
    public interface OnBackPressedListener {
        public void onBack();
    }

    // 리스너 객체 생성
    private OnBackPressedListener mBackListener;

    // 리스너 설정 메소드
    public void setOnBackPressedListener(OnBackPressedListener listener) {
        mBackListener = listener;
    }

    // 뒤로가기 버튼을 눌렀을 때의 오버라이드 메소드
    @Override
    public void onBackPressed() {

        /*// 다른 Fragment 에서 리스너를 설정했을 때 처리됩니다.
        if(mBackListener != null) {
            mBackListener.onBack();
        } else {
            if ( pressedTime == 0 ) {
                Snackbar.make(findViewById(R.id.main_frame),
                        " 한 번 더 누르면 종료됩니다." , Snackbar.LENGTH_LONG).show();
                pressedTime = System.currentTimeMillis();
            }
            else {
                int seconds = (int) (System.currentTimeMillis() - pressedTime);
                if ( seconds > 2000 ) {
                    Snackbar.make(findViewById(R.id.main_frame),
                            " 한 번 더 누르면 종료됩니다." , Snackbar.LENGTH_LONG).show();
                    pressedTime = 0 ;
                }
                else {
                    super.onBackPressed();
                    finish();
                }
            }
        }*/
        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("종료하시겠습니까?");

        // "예" 버튼을 누르면 실행되는 리스너
        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // 현재 액티비티를 종료한다. (MainActivity에서 작동하기 때문에 애플리케이션을 종료한다.)
            }
        });
        // "아니오" 버튼을 누르면 실행되는 리스너
        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return; // 아무런 작업도 하지 않고 돌아간다
            }
        });

        alBuilder.setTitle("서울은 배우는중");
        alBuilder.setIcon(R.drawable.seoul_is_learning); //아이콘 설정
        alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.


    }



}
