package org.gasen.IOC.Factory;

import java.lang.reflect.Field;
import java.util.Map;

public class Implant {

    public void implant(Map<String, Object> instanceMapping){
        if(instanceMapping.isEmpty()){
            return;
        }

        for(Map.Entry<String, Object> entry : instanceMapping.entrySet()){
            Field[] fields = entry.getValue().getClass().getDeclaredFields();

            for(Field field : fields){
                // TODO: 2018/12/20


                String ID = "";

                try{
                    field.set(entry.getValue(), instanceMapping.get(ID));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
