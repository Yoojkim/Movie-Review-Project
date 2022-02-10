package mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMapper {
    boolean likeExist(@Param("uid")Long uid, @Param("rid")Long rid);
    void deleteLike(@Param("uid")Long uid, @Param("rid")Long rid);
    void likeReview(@Param("uid")Long uid, @Param("rid")Long rid);
    int countLikes(Long rid);
}
