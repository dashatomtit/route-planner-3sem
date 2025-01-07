package ru.mirea.tomtit.routeplannerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.tomtit.routeplannerbackend.dto.AllRoutesDto;
import ru.mirea.tomtit.routeplannerbackend.dto.RouteDto;
import ru.mirea.tomtit.routeplannerbackend.entity.PointEntity;
import ru.mirea.tomtit.routeplannerbackend.entity.RouteEntity;
import ru.mirea.tomtit.routeplannerbackend.entity.UserEntity;
import ru.mirea.tomtit.routeplannerbackend.mapper.RouteMapper;
import ru.mirea.tomtit.routeplannerbackend.repository.PointsRepository;
import ru.mirea.tomtit.routeplannerbackend.repository.RoutesRepository;
import ru.mirea.tomtit.routeplannerbackend.repository.UsersRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteMapper routeMapper;
    private final RoutesRepository routesRepository;
    private final UsersRepository usersRepository;
    private final PointsRepository pointsRepository;

    public AllRoutesDto getAllRoutes() {
        List<RouteEntity> routes = routesRepository.findAll();

        List<RouteDto> routeDtos = routes.stream()
                .map(routeMapper::map)
                .toList();

        AllRoutesDto allRoutesDto = new AllRoutesDto();
        allRoutesDto.setRoutes(routeDtos);

        return allRoutesDto;
    }

    public RouteDto getRouteById(Long id) {
        Optional<RouteEntity> route = routesRepository.findById(id);
        RouteEntity routeEntity = route.orElseThrow();
        return routeMapper.map(routeEntity);
    }

    public RouteDto createRoute(RouteDto routeDto)
    {
        Optional<UserEntity> userEntityByUsername = usersRepository.findUserEntityByUsername(routeDto.getOwner());
        UserEntity ownerEntity = userEntityByUsername.orElseThrow();

        RouteEntity routeEntity = routeMapper.mapBack(routeDto);
        routeEntity.setOwner(ownerEntity);

        pointsRepository.saveAll(routeEntity.getPoints());
        routesRepository.save(routeEntity);
        return routeDto;
    }

    public void deleteRouteById(Long id)
    {
        RouteEntity routeEntity = routesRepository.findById(id).orElseThrow();
        Set<PointEntity> points = routeEntity.getPoints();

        routesRepository.delete(routeEntity);
        pointsRepository.deleteAll(points);
    }

}
