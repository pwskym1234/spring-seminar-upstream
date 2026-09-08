package com.wafflestudio.spring2026.meeting.dto

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive

/**
 * 부분 수정(PATCH) 요청. 전달하지 않은 필드는 기존 값을 유지하므로 모두 nullable이다.
 *
 * title에 @NotBlank를 쓰지 않은 이유: @NotBlank는 null도 위반으로 보기 때문에
 * "title을 아예 보내지 않는" 정상 요청까지 400이 된다.
 * @Pattern은 null을 유효한 값으로 통과시키므로, 값이 있을 때만 공백 여부를 검사할 수 있다.
 * 정규식 "(?s).*\S.*"는 공백이 아닌 문자를 최소 하나 요구한다.
 */
data class MeetingUpdateRequest(
    @field:Pattern(
        regexp = "(?s).*\\S.*",
        message = "모임 제목은 비어 있을 수 없습니다.",
    )
    val title: String? = null,

    @field:Positive(message = "모임 정원은 1명 이상이어야 합니다.")
    val capacity: Int? = null,
)
