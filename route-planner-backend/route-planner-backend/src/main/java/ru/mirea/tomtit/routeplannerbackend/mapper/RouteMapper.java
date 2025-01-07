package ru.mirea.tomtit.routeplannerbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mirea.tomtit.routeplannerbackend.dto.PointDto;
import ru.mirea.tomtit.routeplannerbackend.dto.RouteDto;
import ru.mirea.tomtit.routeplannerbackend.entity.PointEntity;
import ru.mirea.tomtit.routeplannerbackend.entity.RouteEntity;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RouteMapper implements TwoSideMapper<RouteEntity, RouteDto> {

    private final PointMapper pointMapper;

    @Override
    public RouteDto map(RouteEntity routeEntity) {
        RouteDto routeDto = new RouteDto();

        routeDto.setId(routeEntity.getId());
        routeDto.setName(routeEntity.getName());
        routeDto.setOwner(routeEntity.getOwner().getUsername());

        List<PointDto> points = routeEntity.getPoints()
                .stream()
                .sorted(Comparator.comparing(PointEntity::getPosition))
                .map(pointMapper::map)
                .toList();
        routeDto.setPoints(points);

        List<Integer> elevations = routeEntity.getPoints()
                .stream()
                .sorted(Comparator.comparing(PointEntity::getPosition))
                .map(PointEntity::getElevation)
                .toList();
        routeDto.setElevations(elevations);

        return routeDto;
    }

    @Override
    public RouteEntity mapBack(RouteDto routeDto) {
        RouteEntity routeEntity = new RouteEntity();

        routeEntity.setName(routeDto.getName());

        Set<PointEntity> pointEntities = new HashSet<>();
        for (int i = 0; i < routeDto.getPoints().size(); i++) {
            PointEntity pointEntity = pointMapper.mapBack(routeDto.getPoints().get(i));
            pointEntity.setElevation(routeDto.getElevations().get(i));
            pointEntity.setPosition(i);
            pointEntities.add(pointEntity);
        }

        routeEntity.setPoints(pointEntities);

        return routeEntity;
    }
}
