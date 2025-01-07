package ru.mirea.tomtit.routeplannerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.tomtit.routeplannerbackend.entity.UserEntity;
import ru.mirea.tomtit.routeplannerbackend.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final UsersRepository usersRepository;

    public boolean login(String username, String password) {
        UserEntity userEntity = usersRepository.findUserEntityByUsername(username).orElseThrow();
        String correctPassword = userEntity.getPassword().trim();
        return password.trim().equals(correctPassword);
    }

}
