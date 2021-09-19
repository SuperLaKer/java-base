package slkjava.jvm.directives;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 常见的一些指令：
 *
 * jps: 查看运行中的java进程号
 * jmap -histo 进程号
 * jmap -heap 进程号
 * jvisualvm
 *
 *
 */
@Configuration
public class TheDirectives {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TheDirectives.class);
        TheDirectives directives = ac.getBean(TheDirectives.class);
        System.out.println(directives);
        TimeUnit.SECONDS.sleep(1000);
    }
}
