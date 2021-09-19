package slkjava.jvm.a;

import com.sun.crypto.provider.DESKeyFactory;

/**
 * 启动类加载器、扩展类加载器：分别加载JRE lib下的核心库、扩展库
 * 应用程序类加载器：ClassPath路径下的包
 * <p>
 * 自定义类加载器
 * <p>
 * classPath: .../target/classes/
 */
public class B类加载器 {
    public static void main(String[] args) {

        // 启动类加载器是native的C语言开发的: 打印输出null
        System.out.println(String.class.getClassLoader());
        // ext包: Launcher$ExtClassLoader
        System.out.println(DESKeyFactory.class.getClassLoader());
        // Launcher$AppClassLoader
        System.out.println(B类加载器.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getClass().getName());
    }
}
