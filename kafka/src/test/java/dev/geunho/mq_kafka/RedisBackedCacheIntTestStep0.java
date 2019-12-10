package dev.geunho.mq_kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.testcontainers.containers.*;

public class RedisBackedCacheIntTestStep0 {
    private RedisBackedCache underTest;
    static GenericContainer redis = new GenericContainer<>("redis:5.0.3-alpine").withExposedPorts(6379);

    @BeforeAll
    public void setUp() {
        redis.start();

        String address = redis.getContainerIpAddress();
        Integer port = redis.getFirstMappedPort();
        underTest = new RedisBackedCache(address, port);
    }

    @Test
    public void testSimplePutAndGet() {
        underTest.put("test", "example");

        String retrieved = underTest.get("test");
        assertEquals(retrieved, "example");
    }
}