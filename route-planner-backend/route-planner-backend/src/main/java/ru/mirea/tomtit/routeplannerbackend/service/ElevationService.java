package ru.mirea.tomtit.routeplannerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.mirea.tomtit.routeplannerbackend.dto.elevation.ElevationRsDto;
import ru.mirea.tomtit.routeplannerbackend.dto.elevation.OpenMeteoRs;

@Service
@RequiredArgsConstructor
public class ElevationService {

    private final static String ELEVATION_API_HOST = "https://api.open-meteo.com/v1/elevation?latitude=%s&longitude=%s";

    private final WebClient webClient;

    public ElevationRsDto getElevation(String latitude, String longitude) {
        String url = String.format(ELEVATION_API_HOST, latitude, longitude);

        OpenMeteoRs openMeteoRs = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(OpenMeteoRs.class)
                .block();

        ElevationRsDto elevationRsDto = new ElevationRsDto();
        elevationRsDto.setElevation(Math.round(openMeteoRs.getElevation().get(0)));
//        elevationRsDto.setElevation(123);
        return elevationRsDto;
    }

}
