package ies.hw.hw1.http;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ies.hw.hw1.models.Cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WeakConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {

    private static final long serialVersionUID = 1L;

    private transient Map<K, Long> timeMap = new ConcurrentHashMap<>();
    private long expiryInMillis = 1000000;
    private Cache cache = new Cache(0, 0, expiryInMillis);

    private static final Logger logger = LogManager.getLogger(WeakConcurrentHashMap.class);

    public WeakConcurrentHashMap() {
        initialize();
    }

    public WeakConcurrentHashMap(long ttl) {
        expiryInMillis = ttl;
        initialize();
    }

    void initialize() {
        new CleanerThread().start();
    }

    @Override
    public V put(K key, V value) {
        Date date = new Date();
        timeMap.put(key, date.getTime());
        if (logger.isInfoEnabled())
            logger.info(MessageFormat.format("Inserting : {0} : on Cache", key));
        return super.put(key, value);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (!containsKey(key))
            return put(key, value);
        else
            return get(key);
    }

    public boolean hitOrMiss(K key) {
        if (containsKey(key)) {
            cache.setNumberOfHits(cache.getNumberOfHits() + 1);
            return true;
        }
        else {
            cache.setNumberOfMisses(cache.getNumberOfMisses() + 1);
            return false;
        }
    }

    public Cache getCache() {
        return cache;
    }

    class CleanerThread extends Thread {
        @Override
        public void run() {
            if (logger.isInfoEnabled())
                logger.info("Initiating Cleaner Thread...");
            while (true) {
                cleanMap();
                try {
                    Thread.sleep(expiryInMillis / 2);
                } catch (InterruptedException e) {
                    if (logger.isInfoEnabled())
                        logger.error(e);
                }
                
            }
        }

        private void cleanMap() {
            long currentTime = new Date().getTime();
            for (Map.Entry<K, Long> entry : timeMap.entrySet()) {
                K key = entry.getKey();
                if (currentTime > (entry.getValue() + expiryInMillis)) {
                    remove(key);
                    timeMap.remove(key);
                    if (logger.isInfoEnabled())
                        logger.info(MessageFormat.format("Removing : {0} : from Cache", key));
                }
            }
        }
    }
}