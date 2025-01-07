package ru.mirea.tomtit.routeplannerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.tomtit.routeplannerbackend.dto.elevation.ElevationRsDto;
import ru.mirea.tomtit.routeplannerbackend.service.ElevationService;
import ru.mirea.tomtit.routeplannerbackend.utils.ResponseUtils;

@RestController
@RequiredArgsConstructor
public class ElevationController {

    private final ElevationService elevationService;

    @GetMapping("/elevation")
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.OPTIONS})
    public ResponseEntity<?> getElevation(
            @RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude) {
        try {
            ElevationRsDto elevationRsDto = elevationService.getElevation(latitude, longitude);
            return ResponseUtils.createResponseEntity(elevationRsDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.createResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
