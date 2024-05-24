package com.hbd.SimpanMuat;

import com.hbd.SimpanMuat.Language.Language;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Scholar {

    public List<Object> loadClasses(String pathToJar) throws IOException {

        List<Object> result = new ArrayList<>();

        JarFile jarFile = new JarFile(pathToJar);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = {new URL("jar:file:" + pathToJar + "!/")};
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if (je.isDirectory() || !je.getName().endsWith(".class")) {
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0, je.getName().length() - 6);
            className = className.replace('/', '.');
            if (className.startsWith("org")) {
                continue;
            }
            try {
                Class c = cl.loadClass(className);
                Object o = c.newInstance();
                result.add(o);
            } catch (NoClassDefFoundError | InstantiationException | ClassNotFoundException | IllegalAccessException err) {/* */}
        }
        return result;
    }
}
