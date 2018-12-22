package protocol.http;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * Created by zhongbo on 2018/12/21.
 */
public class HttpServer {
    public void start(String host,Integer port){
        Tomcat tomcat=new Tomcat();
        Server server=tomcat.getServer();
        Service service=server.findService("Tomcat");

        Connector connector=new Connector();
        connector.setPort(port);

        Engine engine=new StandardEngine();
        engine.setDefaultHost(host);

        Host host1=new StandardHost();
        host1.setName(host);

        String path="";
        Context context=new StandardContext();
        context.setPath(path);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host1.addChild(context);
        engine.addChild(host1);
        service.setContainer(engine);
        service.addConnector(connector);

        tomcat.addServlet(path,"dispatcherServlet",new DispatcherServlet());
        context.addServletMappingDecoded("/*","dispatcherServlet");

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
