package ru.mirea.tomtit.routeplannerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.tomtit.routeplannerbackend.entity.RouteEntity;

@Repository
public interface RoutesRepository extends JpaRepository<RouteEntity, Long> {

}
