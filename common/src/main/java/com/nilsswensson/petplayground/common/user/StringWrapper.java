package com.nilsswensson.petplayground.common.user;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class StringWrapper {
    private String content;
}
