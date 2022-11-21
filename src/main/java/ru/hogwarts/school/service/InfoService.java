package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class InfoService {
    private final Logger LOG = LoggerFactory.getLogger(InfoService.class);
    @Value("${server.port}")
    private Integer port;

    public Integer getPort(){
        LOG.debug("was invoking method getPort");
        return port;
    }


}
