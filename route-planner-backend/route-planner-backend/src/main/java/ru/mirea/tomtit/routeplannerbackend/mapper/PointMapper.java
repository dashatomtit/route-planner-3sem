package ru.mirea.tomtit.routeplannerbackend.mapper;

import org.springframework.stereotype.Component;
import ru.mirea.tomtit.routeplannerbackend.dto.PointDto;
import ru.mirea.tomtit.routeplannerbackend.entity.PointEntity;

@Component
public class PointMapper implements TwoSideMapper<PointEntity, PointDto> {

    @Override
    public PointDto map(PointEntity pointEntity) {
        PointDto pointDto = new PointDto();

        pointDto.setId(pointEntity.getId());
        pointDto.setLatitude(pointEntity.getLatitude());
        pointDto.setLongitude(pointEntity.getLongitude());

        return pointDto;
    }

    @Override
    public PointEntity mapBack(PointDto pointDto) {
        PointEntity pointEntity = new PointEntity();

        pointEntity.setLatitude(pointDto.getLatitude());
        pointEntity.setLongitude(pointDto.getLongitude());

        return pointEntity;
    }
}
