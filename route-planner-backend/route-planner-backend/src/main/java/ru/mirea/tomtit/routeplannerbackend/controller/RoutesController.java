package ru.mirea.tomtit.routeplannerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.tomtit.routeplannerbackend.dto.AllRoutesDto;
import ru.mirea.tomtit.routeplannerbackend.dto.RouteDto;
import ru.mirea.tomtit.routeplannerbackend.service.RouteService;
import ru.mirea.tomtit.routeplannerbackend.utils.ResponseUtils;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class RoutesController {

    private final RouteService routesService;

    @GetMapping("/routes")
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.OPTIONS})
    public ResponseEntity<?> getAllRoutes() {
        try {
            AllRoutesDto allRoutes = routesService.getAllRoutes();
            return ResponseUtils.createResponseEntity(allRoutes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.createResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/routes/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.OPTIONS})
    public ResponseEntity<?> getRouteById(@PathVariable("id") Long id) {
        try {
            RouteDto routeDto = routesService.getRouteById(id);
            return ResponseUtils.createResponseEntity(routeDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseUtils.createResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.createResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/routes")
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.OPTIONS})
    public ResponseEntity<?> createRoute(@RequestBody RouteDto routeDto) {
        try {
            RouteDto route = routesService.createRoute(routeDto);
            return ResponseUtils.createResponseEntity(route, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.createResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/routes/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.OPTIONS})
    public ResponseEntity<?> deleteRoute(@PathVariable("id") Long id) {
        try {
            routesService.deleteRouteById(id);
            return ResponseUtils.createResponseEntity(null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.createResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
