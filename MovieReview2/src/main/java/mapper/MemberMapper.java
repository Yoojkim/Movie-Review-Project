package mapper;

import domain.MemberDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {
    MemberDTO getMemberToEmail(String email);
    boolean emailExist(String email);
    boolean nickNameExist(String nickName);
    void signUp(MemberDTO memberDTO);
    Long getIdToEmail(String email);
    void setSalt(String salt,Long id);
    String getSalt(Long id);
}
