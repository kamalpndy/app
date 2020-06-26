package com.example.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Project: service-engine
 * Package: com.optum.riptide.ServiceEngine.service
 * <p>
 * User: jsticha
 * Date: 4/10/17
 * Time: 2:06 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Service
public class OptumApiRateLimiter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    private RateLimiter limiter;
    private DateFormat dateFormat_yyyyMMddHHmmsszz = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss zz");

    @PostConstruct
    private void init() {
        this.limiter = RateLimiter.create(100);
        //logger.info("OptumApiRateLimiter(" + propertiesBeanOptumApi.apiOptumServiceEffectiveRateLimitDouble + ") Initialized with Effective Rate :: " + dateFormat_yyyyMMddHHmmsszz.format(System.currentTimeMillis()));
    }

    /**
     * This method will try and get a lock
     *
     * @return boolean. True if lock is available, False if failed to get lock.
     */
    public boolean tryAquireProviderApiLock() {
        return limiter.tryAcquire();
    }

    /**
     * This method will block the thread until it get's a lock.
     */
    public void waitForLock() {
//        logger.trace("Trying to waitForLock a permit :: " + dateFormat_yyyyMMddHHmmsszz.format(System.currentTimeMillis()));
        double secondsWaited = limiter.acquire();
        System.out.println("Acquired in " + secondsWaited + " seconds :: " + dateFormat_yyyyMMddHHmmsszz.format(System.currentTimeMillis()));
    }
}
