package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;
import java.util.stream.Stream;

@Service
public class InfoService {
    private static final Logger LOG = LoggerFactory.getLogger(InfoService.class);
    @Value("${server.port}")
    private Integer port;

    public Integer getPort() {
        LOG.debug("was invoking method getPort");
        return port;
    }


    public Long testStream() {
        long start = System.currentTimeMillis();

        long sum = Stream.iterate(1L, a -> a + 1)
                .limit(1_000_000)
                .reduce(0L, Long::sum);
        long finish = System.currentTimeMillis();
        long time = finish - start;
        LOG.debug("time = " +  time);
        return sum;
    }


    public Long testStreamParallel() {
        long start = System.currentTimeMillis();

        long sum = LongStream.rangeClosed(1, 1_000_000)
                .parallel()
                .reduce(0, Long::sum);

        long finish = System.currentTimeMillis();
        long time = finish - start;
        LOG.debug("time = " + time);
        return sum;
    }
}
