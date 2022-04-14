package ies.hw.hw1;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ies.hw.hw1.http.WeakConcurrentHashMap;
import ies.hw.hw1.models.Cache;

import static org.hamcrest.core.Is.is;

public class CacheTest {

    private long ttl = 5000;
    private WeakConcurrentHashMap<String, ResponseEntity<String>> cache;
    
    @BeforeEach
    public void setUp() {
        cache = new WeakConcurrentHashMap<>(ttl);
    }

    @Test
    public void testCreation() {
        assertTrue(cache.isEmpty());
        assertThat(cache.size(), is(0));
    }

    @Test
    public void testInsertion() {
        ResponseEntity<String> response = new ResponseEntity<>("Response Entity", HttpStatus.OK);
        String uri = "teste";
        cache.put(uri, response);
        assertTrue(cache.containsKey(uri));
        assertTrue(cache.contains(response));
        assertThat(cache.get(uri), is(response));
        assertThat(cache.size(), is(1));
        assertFalse(cache.isEmpty());

        ResponseEntity<String> response2 = new ResponseEntity<>("Response Entity 2", HttpStatus.OK);
        String uri2 = "teste2";
        cache.put(uri2, response2);
        assertThat(cache.size(), is(2));
    }

    @Test
    public void testStats() {
        ResponseEntity<String> response = new ResponseEntity<>("Response Entity", HttpStatus.OK);
        String uri = "teste";

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
}
