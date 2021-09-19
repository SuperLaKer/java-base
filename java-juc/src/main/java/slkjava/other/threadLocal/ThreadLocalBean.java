package slkjava.other.threadLocal;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ThreadLocalBean {

    ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public ThreadLocal<Map<String, Object>> getThreadLocal() {
        return threadLocal;
    }

    public void setThreadLocal(ThreadLocal<Map<String, Object>> threadLocal) {
        this.threadLocal = threadLocal;
    }
}
