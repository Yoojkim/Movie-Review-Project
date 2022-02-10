package domain;

import annotation.ValidationGroups;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

@Getter
@Setter
public class MemberDTO {
    @ApiParam(value = "사용자 ID", required = false)
    @ApiModelProperty(hidden = true)
    private Long id;

    @Email(message="이메일의 형식이 아닙니다.")
    @NotNull(message="이메일이 입력되지 않았습니다.")
    @ApiParam(value = "사용자 email", required = true)
    private String email;

    @NotNull(message="비밀번호가 입력되지 않았습니다.")
    @ApiParam(value = "사용자 password", required = true)
    private String password;

    //로그인 시에 -> nickName 불필요 / ApiModelProperty groups 지원 x
    //그냥 null값으로 받음
    @NotNull(groups = {ValidationGroups.signUp.class},message="닉네임이 입력되지 않았습니다.")
    @ApiParam(value = "사용자 nickname")
    private String nickName;
}
