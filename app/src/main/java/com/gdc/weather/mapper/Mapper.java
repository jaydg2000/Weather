package com.gdc.weather.mapper;

/**
 * Created by jaydg on 7/6/2017.
 */

public interface Mapper<TSource, TDestination> {
    TDestination map(TSource source);
}
