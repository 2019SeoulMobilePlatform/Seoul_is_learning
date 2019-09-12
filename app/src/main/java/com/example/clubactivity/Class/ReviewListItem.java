package com.example.clubactivity.Class;

/**
 * 리뷰 리스트 아이템 클래스
 */
public class ReviewListItem {

    private byte[] profile_image;
    private String nickName;  // 유저 닉네임
    private float star;  // 별점
    private String reviewContent;   // 리뷰 내용


    // 생성자
    public ReviewListItem(byte[] profile_image, String nickName, float star, String reviewContent) {
        this.profile_image = profile_image;
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

    public byte[] getProfile_image() { return profile_image;}
}
