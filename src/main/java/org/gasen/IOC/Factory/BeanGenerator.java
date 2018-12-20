package org.gasen.IOC.Factory;

import org.gasen.IOC.Annotation.Controller;

import java.util.ArrayList;
import java.util.List;

public class BeanGenerator {

    public static List<Bean> generate(String className){
        try{
            Class c = Class.forName(className);
            String[] IDs = generateIDs(c);

            if(IDs == null){
                return null;
            }

            List<Bean> beans = new ArrayList<>();
            for(String ID : IDs){
                beans.add(new Bean(ID, c));
            }

            return beans;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String[] generateIDs(Class c){
        String[] IDs = null;
        if(c.isAnnotationPresent(Controller.class)){
            IDs = new String[]{c.getName()};
        }

        return IDs;
    }

}
