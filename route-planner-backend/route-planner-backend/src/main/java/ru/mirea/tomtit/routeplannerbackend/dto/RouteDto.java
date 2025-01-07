package ru.mirea.tomtit.routeplannerbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class RouteDto {

    private Long id;

    private String name;

    private String owner;

    private List<PointDto> points;

    private List<Integer> elevations;

}
