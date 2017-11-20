package com.xf.tpm.core.info;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xufeng on 2017/11/8
 */
public class MultiThreadPoolInfo {

    private Map<String,ThreadPoolInfo> threadPoolMap = new HashMap<>(10);

    private final String DEFAULT = "default";

    public MultiThreadPoolInfo () {
        addThreadPool(createDefaultPool());
    }

    public void addThreadPool(ThreadPoolInfo threadPool) {
        if (threadPool == null) {
            throw new NullPointerException("threadPoolInfo can not be null");
        }
        this.threadPoolMap.put(threadPool.getName(),threadPool);
    }

    public void addThreadPools(Collection<ThreadPoolInfo> threadPools) {

        if (threadPools == null) {
            throw new NullPointerException("threadPools can not be null");
        }
        for (ThreadPoolInfo threadPoolInfo : threadPools) {
            addThreadPool(threadPoolInfo);
        }
    }

    public Collection<ThreadPoolInfo> values() {
        return threadPoolMap.values();
    }

    public void setDefault(ThreadPoolInfo threadPoolInfo) {
        if (Objects.equals(threadPoolInfo.getName(),DEFAULT)) {
            throw new IllegalArgumentException("thread pool must named 'default'");
        }
        this.threadPoolMap.put(threadPoolInfo.getName(),threadPoolInfo);
    }

    private ThreadPoolInfo createDefaultPool() {
        ThreadPoolInfo threadPoolInfo = new ThreadPoolInfo();
        threadPoolInfo.setName(DEFAULT);
        threadPoolInfo.setCoreSize(2);
        threadPoolInfo.setMaxSize(2);
        threadPoolInfo.setQueueSize(100);
        threadPoolInfo.setThreadKeepAliveTime(30);
        return threadPoolInfo;
    }

}
