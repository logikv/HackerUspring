package util.json;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
    private static ApplicationContext context;

    private AppContext(){
    }

    public static synchronized ApplicationContext getContext(){
        if (context == null){
            context = new ClassPathXmlApplicationContext("applicationContext.xml");

        }
        return context;
    }
}
