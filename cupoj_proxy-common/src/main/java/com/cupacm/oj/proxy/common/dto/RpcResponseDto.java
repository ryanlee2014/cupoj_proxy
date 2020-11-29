package com.cupacm.oj.proxy.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RpcResponseDto<T> {
    private T data;
    private boolean success;
    private String exception;
}
