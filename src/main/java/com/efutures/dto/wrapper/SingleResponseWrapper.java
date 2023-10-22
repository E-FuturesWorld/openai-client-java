package com.efuturesworld.openaiclient.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SingleResponseWrapper<T> {

    public T content;


}
