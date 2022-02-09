package controller;

import domain.Movie;
import domain.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ReviewService;


@RequestMapping("/reviews")
@Controller
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    //리뷰 작성
    @RequestMapping(value="/write",method= RequestMethod.POST)
    public ResponseEntity createReview(Movie movie, String review) throws Exception {
        return new ResponseEntity(reviewService.createReview(movie,review), HttpStatus.OK);
    }

    //특정 영화 리뷰 조회 (review list를 body로 전달)
    @RequestMapping(value="/movie/{mid}",method = RequestMethod.GET)
    public ResponseEntity getMovieReviews(@PathVariable Long mid){
        return new ResponseEntity(reviewService.getReviewsByMid(mid), HttpStatus.OK);
    }

    //특정 member의 리뷰 조회
    @RequestMapping(value="/member/{uid}",method =RequestMethod.GET)
    public ResponseEntity getMemberReviews(@PathVariable Long uid){
        return new ResponseEntity(reviewService.getReviewsByUid(uid),HttpStatus.OK);
    }

    //리뷰 삭제
    @RequestMapping(value="/delete/{rid}",method=RequestMethod.GET)
    public ResponseEntity deleteReview(@PathVariable Long rid){
        return new ResponseEntity(reviewService.deleteReview(rid),HttpStatus.OK);
    }
}
