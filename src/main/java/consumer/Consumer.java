package consumer;

import framework.ProtocolObject;
import framework.URL;
import protocol.http.HttpClient;
import provider.Hello;
import register.Register;

import java.util.Map;

/**
 * Created by zhongbo on 2018/12/21.
 */
public class Consumer {
    public static void main(String[] args) {
        HttpClient httpClient=new HttpClient();
        ProtocolObject protocolObject=new ProtocolObject("hello","sayHello",new Object[]{"zhangsan"},new Class[]{String.class});
        String result=httpClient.post("localhost",8080,protocolObject);
        System.out.println(result);
    }
}
