// 代码生成时间: 2025-09-22 11:02:15
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.List;

/**
 * PerformanceMonitoringApplication demonstrates how to create a system performance monitoring tool
 * using Spring Boot and Spring Cloud.
 */
@SpringBootApplication
@RestController
public class PerformanceMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerformanceMonitoringApplication.class, args);
    }

    /**
     * Retrieves system performance metrics and returns them as JSON.
     *
     * @return a JSON representation of system performance metrics
     */
    @GetMapping("/performance")
    public PerformanceMetrics performanceMetrics() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long upTime = runtimeMXBean.getUptime();
        long threadCount = threadMXBean.getThreadCount();
        long deadlockedThreads = threadMXBean.findDeadlockedThreads() == null ? 0 : threadMXBean.findDeadlockedThreads().length;
        List<ThreadInfo> threadInfos = threadMXBean.dumpAllThreads(true, true);

        PerformanceMetrics metrics = new PerformanceMetrics();
        metrics.setUpTime(upTime);
        metrics.setThreadCount(threadCount);
        metrics.setDeadlockedThreads(deadlockedThreads);
        metrics.setThreadInfos(threadInfos);

        return metrics;
    }
}

/**
 * Holds system performance metrics.
 */
class PerformanceMetrics {
    private long upTime;
    private long threadCount;
    private long deadlockedThreads;
    private List<ThreadInfo> threadInfos;

    // Getters and setters
    public long getUpTime() { return upTime; }
    public void setUpTime(long upTime) { this.upTime = upTime; }
    public long getThreadCount() { return threadCount; }
    public void setThreadCount(long threadCount) { this.threadCount = threadCount; }
    public long getDeadlockedThreads() { return deadlockedThreads; }
    public void setDeadlockedThreads(long deadlockedThreads) { this.deadlockedThreads = deadlockedThreads; }
    public List<ThreadInfo> getThreadInfos() { return threadInfos; }
    public void setThreadInfos(List<ThreadInfo> threadInfos) { this.threadInfos = threadInfos; }
}
