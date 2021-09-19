package slkjava.jvm.a;

/**
 * 自定义类加载器
 * <p>
 * AppClassLoader和ExtClassLoader均继承ClassLoader（一个抽象类）
 */
public class CClassLoaderSelf extends ClassLoader {

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
