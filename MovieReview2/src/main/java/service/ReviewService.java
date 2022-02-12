package service;

/*리뷰 작성, 확인, 아이디로 확인*/

import domain.ReviewDTO;
import domain.MovieWrapper;
import response.BaseResponse;

import java.util.ArrayList;

public interface ReviewService {
    BaseResponse createReview(MovieWrapper movieWrapper) throws Exception;
    ArrayList<ReviewDTO> getReviewsByMid(Long mid);
    ArrayList<ReviewDTO> getReviewsByUid(Long uid);
    BaseResponse deleteReview(Long rid);
    BaseResponse likeReview(Long rid) throws Exception;
    BaseResponse updateReview(Long rid, String review) throws Exception;
}
