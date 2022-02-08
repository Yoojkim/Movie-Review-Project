package mapper;

import domain.MemberDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {
    MemberDTO getMemberToEmail(String email);
    boolean emailExist(String email);
    boolean nickNameExist(String nickName);
    void signUp(MemberDTO memberDTO);
    Long getIdToEmail(String email);
    void setSalt(@Param("salt")String salt,@Param("id")Long id);
    String getSalt(Long id);
    void deleteMember(Long id);
}
