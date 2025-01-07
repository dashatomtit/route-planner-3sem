package ru.mirea.tomtit.routeplannerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.tomtit.routeplannerbackend.dto.auth.AuthRqDto;
import ru.mirea.tomtit.routeplannerbackend.service.AuthorizationService;

import java.util.NoSuchElementException;

import static ru.mirea.tomtit.routeplannerbackend.utils.ResponseUtils.createResponseEntity;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/login")
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.OPTIONS})
    public ResponseEntity<?> login(@RequestBody AuthRqDto authRqDto) {
        try {
            boolean success = authorizationService.login(authRqDto.getUsername(), authRqDto.getPassword());
            return success ? createResponseEntity(null, HttpStatus.OK) : createResponseEntity(null, HttpStatus.FORBIDDEN);
        } catch (NoSuchElementException e) {
            return createResponseEntity(null, HttpStatus.FORBIDDEN);
        }
        catch (Exception e) {
            return createResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
