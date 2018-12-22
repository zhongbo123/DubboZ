package register;

import framework.URL;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhongbo on 2018/12/21.
 */
public class Register {
    private static Map<String,Map<URL,Class>> REGISTER=new HashMap<String,Map<URL,Class>>();
    public static void regist(URL url,String interfaceName,Class implClazz){
        Map<URL,Class> map=new HashMap<URL,Class>();
        map.put(url,implClazz);
        REGISTER.put(interfaceName,map);
    }

    public static Class getClass(String interfaceName,URL url){
        return REGISTER.get(interfaceName).get(url);
    }

    public static Map<URL,Class> getInfo(String interfaceName){
        return REGISTER.get(interfaceName);
    }

    public static URL getURL(String interfaceName){
        Set<URL> set=REGISTER.get(interfaceName).keySet();
        Iterator<URL> iterator=set.iterator();
        if (iterator.hasNext()){
            return iterator.next();
        }
        return null;
    }
}
