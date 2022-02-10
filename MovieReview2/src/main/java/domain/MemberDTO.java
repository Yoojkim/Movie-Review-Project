package domain;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

@Getter
@Setter
public class MemberDTO {
    @ApiParam(value = "사용자 ID", required = false)
    private Long id;

    @Email
    @NotNull(message="이메일이 입력되지 않았습니다.")
    @ApiParam(value = "사용자 email", required = true)


    @NotNull(message="비밀번호가 입력되지 않았습니다.")
    private String email;
    @ApiParam(value = "사용자 password", required = true)
    private String password;

    //로그인 시, 사용자 닉네임은 필요 없음
    @ApiParam(value = "사용자 nickname")
    private String nickName;
}
