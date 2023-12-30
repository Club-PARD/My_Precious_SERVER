package com.myprecious.moneyglove.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
    private boolean result;
    private String message;  // 성공 실패 메세지
    private D data;          // data 넣을거임
    private int total;

    public static <D> ResponseDto<D> setSuccess(String message, D data) {
        return ResponseDto.set(true, message, data, 0);
    }

    public static <D> ResponseDto<D> setSuccess(String message, D data, int total) {
        return ResponseDto.set(true, message, data, total);
    }

    public static <D> ResponseDto<D> setFailed(String message) {
        return ResponseDto.set(false, message, null, 0);
    }

    public static <D> ResponseDto<D> setFailed(String message, D data) {
        return ResponseDto.set(false, message, data, 0);
    }
}
