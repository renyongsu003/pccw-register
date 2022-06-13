package com.pccw.register.domain.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class VelocityUtils {


    private VelocityEngine ve ;

    public VelocityUtils(){
        ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        //处理中文问题
        ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        ve.init();
    }

    public String getString(String path,String name){
        try {
            Template template = ve.getTemplate(path);
            //获取上下文
            VelocityContext root = new VelocityContext();
            //把数据填入上下文
            root.put("name", name);

            StringWriter writer = new StringWriter();
            template.merge(root, writer);
            writer.flush();
            String result = writer.toString();
            writer.close();
            return result;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
