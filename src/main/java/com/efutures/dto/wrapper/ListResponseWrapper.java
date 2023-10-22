package com.efuturesworld.openaiclient.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
@Builder
public class ListResponseWrapper <T>{

    public List<T> content;

}
