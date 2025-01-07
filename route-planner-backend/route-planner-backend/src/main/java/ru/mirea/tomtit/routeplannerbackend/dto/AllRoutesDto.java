package ru.mirea.tomtit.routeplannerbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class AllRoutesDto {

    private List<RouteDto> routes;

}
