package ru.mirea.tomtit.routeplannerbackend.mapper;

public interface TwoSideMapper<A, B> extends Mapper<A, B> {

    A mapBack(B b);

}
