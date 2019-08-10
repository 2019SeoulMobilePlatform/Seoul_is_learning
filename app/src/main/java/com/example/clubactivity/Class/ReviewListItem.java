package com.example.clubactivity.Class;

import android.graphics.Bitmap;

/**
 * 리뷰 리스트 아이템 클래스
 */
public class ReviewListItem {

    private Bitmap profile_image;
    private String nickName;  // 유저 닉네임
    private float star;  // 별점
    private String reviewContent;   // 리뷰 내용
    private String classPlace;    // 캠핑장 종류

    //리뷰 이미지뷰


    // 생성자
    public ReviewListItem(Bitmap profile_image, String campingPlace, String nickName, float star, Bitmap reviewImage, String reviewContent, String classPlace) {
        this.profile_image = profile_image;
        this.classPlace = classPlace;
        this.nickName = nickName;
        this.star = star;
        this.reviewContent = reviewContent;
    }


    public String getNickName() {
        return nickName;
    }

    public float getStar() {
        return star;
    }

    public String getReviewContent() {return reviewContent;}

    public String getClassPlace() {return classPlace;}

    public Bitmap getProfile_image() { return profile_image;}
}
