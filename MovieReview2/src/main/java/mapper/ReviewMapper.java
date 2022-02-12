package mapper;


import domain.ReviewDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ReviewMapper {
    void registerReview(@Param("mid") Long mid, @Param("uid")Long uid, @Param("nickName")String nickName,@Param("review")String review);
    ArrayList<ReviewDTO> getReviewsByMid(@Param("mid")Long mid);
    ArrayList<ReviewDTO> getReviewsByUid(@Param("uid")Long uid);
    void deleteReview(Long rid);
    void updateReview(@Param("rid")Long rid, @Param("review")String review);
}
