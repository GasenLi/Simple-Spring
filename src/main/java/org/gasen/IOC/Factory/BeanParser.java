package org.gasen.IOC.Factory;

import org.gasen.IOC.Factory.Interface.BeanRegisterInterface;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class BeanParser {
    //扫描包的Key
    public static final  String SCAN_PACKAGE = "scanPackage";

    //容器注册对象
    private BeanRegisterInterface register;


    public BeanParser(BeanRegisterInterface register){
        this.register = register;
    }

    public void parse(Properties properties){
        //扫描包
        String packageName = properties.getProperty(SCAN_PACKAGE);

        doRegister(packageName);
    }

    private void doRegister(String packageName){
        //获取此包的绝对路径
        URL url = getClass().getClassLoader().getResource("./" + packageName.replaceAll("\\.","/"));
        File packageFile = new File(url.getFile());

        for(File file : packageFile.listFiles()){
            if(file.isDirectory()){
                doRegister(packageName + "." + file.getName());
            }else {
                String className = packageName + "." + file.getName().replaceAll(".class","").trim();

                List<Bean> beans = BeanGenerator.generate(className);

                if(beans == null){
                    continue;
                }else {
                    this.register.registerBean(beans);
                }
            }
        }
    }
}
