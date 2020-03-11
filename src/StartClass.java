import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class StartClass {

    static void start(Class className) {
        int beforeSuiteCount = 0;
        int afterSuiteCount = 0;
        final int MIN_PRIORITY = 0;
        final int MAX_PRIORITY = 11;

        HashMap<Method, Integer> methodsByPriority = new HashMap<>();
        Method[] methods = className.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (++beforeSuiteCount > 1) throw new RuntimeException("Duplicate BeforeSuite section");
                methodsByPriority.put(m, m.getAnnotation(BeforeSuite.class).priority());
            }

            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (++afterSuiteCount > 1) throw new RuntimeException("Duplicate AfterSuite section");
                methodsByPriority.put(m, m.getAnnotation(AfterSuite.class).priority());
            }

            if (m.isAnnotationPresent(Test.class))
                methodsByPriority.put(m, m.getAnnotation(Test.class).priority());

        }

        for (int p = MIN_PRIORITY; p < MAX_PRIORITY + 1; p++) {
            for (Method m : methodsByPriority.keySet()) {
                if (methodsByPriority.get(m) == p) {
                    try {
                        m.invoke(className);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static void start(String classNameString) {
        try {
            start(Class.forName(classNameString));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
