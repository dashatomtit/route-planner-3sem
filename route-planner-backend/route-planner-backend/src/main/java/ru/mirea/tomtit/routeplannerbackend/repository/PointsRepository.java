package ru.mirea.tomtit.routeplannerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.tomtit.routeplannerbackend.entity.PointEntity;

@Repository
public interface PointsRepository extends JpaRepository<PointEntity, Long> {

}
