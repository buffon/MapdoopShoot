package com.harry.mapreduce;

import com.harry.mapreduce.bean.Html;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenyehui
 * Date: 14-3-12
 * Time: 下午9:01
 * To change this template use File | Settings | File Templates.
 */
public class XmlShoot {

    private static List<Class> list = new ArrayList<Class>();

    static {
        list.add(String.class);
        list.add(Integer.class);
        list.add(Boolean.class);
        list.add(Character.class);
        list.add(Enum.class);
        list.add(int.class);
        list.add(char.class);
        list.add(boolean.class);
        list.add(long.class);
    }

    public static void main(String[] args) {
        String xmlString = "<html>" + "<head>" + "<title>dom4j解析一个例子</title>"
                + "<username>yangrong</username>"
                + "<password>123456</password>" + "</head>"
                + "<body>" + "<result>0</result>"
                + "<banlce>1000</banlce>" + "<subID>36242519880716</subID>"
                + "</body>" + "<shoot>123</shoot>" + "</html>";

        read(xmlString, Html.class);

    }

    private static <T> T read(String xml, Class<T> clazz) {

        Document document = null;

        T o = null;

        try {
            document = DocumentHelper.parseText(xml);
            Field[] fields = clazz.getDeclaredFields();
            Element rootElement = document.getRootElement();
            o = clazz.newInstance();


            for (Field field : fields) {
                Element element = rootElement.element(field.getName());

                Object k = null;

                if (!list.contains(field.getType())) {
                    k = field.getType().newInstance();
                    Field[] f = field.getType().getDeclaredFields();

                    for (Field subfield : f) {
                        try {
                            Method m = field.getType().getDeclaredMethod(getMethod(subfield.getName()), subfield.getType());
                            m.invoke(k, element.elementText(subfield.getName()));
                        } catch (Exception e) {
                        }
                    }

                } else {
                    k = rootElement.elementText(field.getName());
                }

                //k = transfer(k, field, element);

                Method m = clazz.getDeclaredMethod(getMethod(field.getName()), field.getType());
                m.invoke(o, k);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    private static Object transfer(Object k, Field field, Element element) throws Exception {
        if (!field.getType().equals(String.class)) {
            Field[] f = field.getType().getDeclaredFields();
            for (Field subfield : f) {
//                subfield.getA
                Method m = field.getType().getDeclaredMethod(getMethod(subfield.getName()), subfield.getType());
                m.invoke(k, element.elementText(subfield.getName()));
            }
        } else {

        }
        return k;

    }

    private static String getMethod(String args) {
        return "set" + args.substring(0, 1).toUpperCase() + args.substring(1);
    }
}
