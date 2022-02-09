package serviceImpl;

import domain.Movie;
import domain.ReviewDTO;
import mapper.MemberMapper;
import mapper.MovieMapper;
import mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import response.BaseResponse;
import service.MemberService;
import service.ReviewService;
import util.Jwt;

import java.util.ArrayList;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    Jwt jwt;

    @Autowired
    MemberService memberService;

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    MovieMapper movieMapper;

    @Autowired
    MemberMapper memberMapper;


    @Override
    public BaseResponse createReview(Movie movie, String review) throws Exception {
        //jwt로 uid 확보
        Long uid=memberService.getLoginId();

        if(!movieMapper.linkExist(movie.getLink())){
            //영화 저장x
            movieMapper.registerMovie(movie);
        }

        Long mid= movieMapper.getMid(movie);
        String nickName=memberMapper.getNickName(uid);

        reviewMapper.registerReview(mid,uid,nickName,review);
        return new BaseResponse("리뷰등록 성공", HttpStatus.OK);
    }

    @Override
    public ArrayList<ReviewDTO> getReviewsByMid(Long mid) {
        return reviewMapper.getReviewsByMid(mid);
    }

    @Override
    public ArrayList<ReviewDTO> getReviewsByUid(Long uid) {
        return reviewMapper.getReviewsByUid(uid);
    }

    @Override
    public BaseResponse deleteReview(Long rid) {
        reviewMapper.deleteReview(rid);
        return new BaseResponse("리뷰삭제 성공",HttpStatus.OK);
    }


}
