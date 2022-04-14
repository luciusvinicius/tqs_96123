package ies.hw.hw1;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ies.hw.hw1.http.WeakConcurrentHashMap;
import ies.hw.hw1.models.Cache;

import static org.hamcrest.core.Is.is;
import static org.awaitility.Awaitility.await;

public class CacheTest {

    private long ttl = 1000; // (1 second) ttl only for the caching cache
    private WeakConcurrentHashMap<String, ResponseEntity<String>> cache;
    private final CountDownLatch waiter = new CountDownLatch(1);
    
    @BeforeEach
    void setUp() {
        cache = new WeakConcurrentHashMap<>(ttl);
    }

    @Test
    void testCreation() {
        assertTrue(cache.isEmpty());
        assertThat(cache.size(), is(0));
    }

    @Test
    void testInsertion() {
        ResponseEntity<String> response = new ResponseEntity<>("Response Entity", HttpStatus.OK);
        String uri = "teste insertion 1";
        cache.put(uri, response);
        assertTrue(cache.containsKey(uri));
        assertTrue(cache.contains(response));
        assertThat(cache.get(uri), is(response));
        assertThat(cache.size(), is(1));
        assertFalse(cache.isEmpty());

        ResponseEntity<String> response2 = new ResponseEntity<>("Response Entity 2", HttpStatus.OK);
        String uri2 = "teste insertion 2";
        cache.put(uri2, response2);
        assertThat(cache.size(), is(2));
    }

    @Test
    void testStats() {
        ResponseEntity<String> response = new ResponseEntity<>("Response Entity", HttpStatus.OK);
        String uri = "teste stats";

        Cache insideCache = cache.getCache();

        assertThat(insideCache.getNumberOfHits(), is(0));
        assertThat(insideCache.getNumberOfMisses(), is(0));
        assertThat(insideCache.getNumberOfRequests(), is(0));


        assertFalse(cache.hitOrMiss(uri)); // every call of this function represents a request
        cache.put(uri, response);
        assertThat(cache.get(uri), is(response));
        assertThat(insideCache.getNumberOfHits(), is(0));
        assertThat(insideCache.getNumberOfMisses(), is(1));
        assertThat(insideCache.getNumberOfRequests(), is(1));


        assertTrue(cache.hitOrMiss(uri));
        assertThat(insideCache.getNumberOfHits(), is(1));
        assertThat(insideCache.getNumberOfMisses(), is(1));
        assertThat(insideCache.getNumberOfRequests(), is(2));

    }

    @Test
    void testWrongGetShouldReturnNull() {
        assertNull(cache.get("RANDOM URI"));
    }

    @Test
    void testTTL() {
        ResponseEntity<String> response = new ResponseEntity<>("Response Entity", HttpStatus.OK);
        String uri = "teste ttl";
        cache.put(uri, response);
        assertTrue(cache.containsKey(uri));
        assertTrue(cache.contains(response));
        assertThat(cache.get(uri), is(response));

        await().until(requestIsExpired(uri));

        assertTrue(cache.isEmpty());
        assertNull(cache.get(uri));
    }


    private Callable<Boolean> requestIsExpired(String key) {
        return new Callable<Boolean>() {
            public Boolean call() {
                return !cache.containsKey(key);
            }
        };
    }
}
