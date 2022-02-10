package controller;

import domain.Movie;
import domain.ReviewDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ReviewService;


@RequestMapping("/reviews")
@Controller
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    //리뷰 작성
    @ApiOperation(value="리뷰 작성",notes="입력받은 영화 객체가 등록되어 있지 않으면 등록하고, 리뷰를 저장한다.")
    @RequestMapping(value="/write",method= RequestMethod.POST)
    public ResponseEntity createReview(@RequestBody Movie movie,@RequestBody String review) throws Exception {
        return new ResponseEntity(reviewService.createReview(movie,review), HttpStatus.OK);
    }

    //특정 영화 리뷰 조회 (review list를 body로 전달)
    @ApiOperation(value="특정 영화의 리뷰 조회",notes="영화의 id를 기준으로 해당 영화의 리뷰 list를 반환한다.")
    @RequestMapping(value="/movie/{mid}",method = RequestMethod.GET)
    public ResponseEntity getMovieReviews(@PathVariable Long mid){
        return new ResponseEntity(reviewService.getReviewsByMid(mid), HttpStatus.OK);
    }

    //특정 member의 리뷰 조회
    @ApiOperation(value="특정 사용자의 리뷰 조회",notes="사용자의 id를 기준으로 해당 사용자의 리뷰 list를 반환한다.")
    @RequestMapping(value="/member/{uid}",method =RequestMethod.GET)
    public ResponseEntity getMemberReviews(@PathVariable Long uid){
        return new ResponseEntity(reviewService.getReviewsByUid(uid),HttpStatus.OK);
    }

    //리뷰 삭제
    @ApiOperation(value="특정 리뷰 삭제",notes="리뷰의 id를 받아, 해당 리뷰를 삭제한다")
    @RequestMapping(value="/delete/{rid}",method=RequestMethod.GET)
    public ResponseEntity deleteReview(@PathVariable Long rid){
        return new ResponseEntity(reviewService.deleteReview(rid),HttpStatus.OK);
    }

    //리뷰에 좋아요 달기(자기 리뷰에 좋아요 달아도 됨)
    @ApiOperation(value="특정 리뷰 좋아요",notes="리뷰의 id를 받아, 현재 JWT 토큰으로 인증한 사용자가 좋아요 하도록 한다.(좋아요가 이미 되어있으면 취소하도록 한다.)")
    @RequestMapping(value="/like/{rid}",method=RequestMethod.GET)
    public ResponseEntity likeReview(@PathVariable Long rid) throws Exception {
        return new ResponseEntity(reviewService.likeReview(rid),HttpStatus.OK);
    }
}
