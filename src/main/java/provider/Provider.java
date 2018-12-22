package provider;

import framework.URL;
import protocol.http.HttpServer;
import register.Register;

/**
 * Created by zhongbo on 2018/12/21.
 */
public class Provider {
    public static void main(String[] args) {
        //服务注册
        URL url=new URL("localhost",8080);
        Register.regist(url,"hello",HelloImpl.class);

        //启动tomcat
        new HttpServer().start(url.getHost(),url.getPort());
        System.out.println("tomcat启动完毕");
    }
}
