package cache;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author leon
 * @param <T>
 */
public abstract class ThreadLocals<T> {

    public HashMap<Long, T> cache = new HashMap<>();

    public ReentrantReadWriteLock.ReadLock readLock = new ReentrantReadWriteLock().readLock();

    public ReentrantReadWriteLock.WriteLock writeLock = new ReentrantReadWriteLock().writeLock();

    public void remove() {
        long id = Thread.currentThread().getId();
        writeLock.lock();
        try {
            cache.remove(id);
        } finally {
            writeLock.unlock();
        }
    }

    public T set(T value) {
        long currentId = Thread.currentThread().getId();
        try {
            writeLock.lock();
            T oldValue = this.cache.put(currentId, value);
            return oldValue;
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        writeLock.lock();
        try {
            this.cache.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public T get() {
        try {
            readLock.lock();
            long id = Thread.currentThread().getId();
            if (cache.containsKey(id)) {
                return cache.get(id);
            }
        } finally {
            readLock.unlock();
        }
        writeLock.lock();
        try {
            T value = initialValue();
            cache.put(Thread.currentThread().getId(), value);
            return value;
        } finally {
            writeLock.unlock();
        }
    }

    public abstract T initialValue();

}
