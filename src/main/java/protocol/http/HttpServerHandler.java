package protocol.http;

import framework.ProtocolObject;
import framework.URL;
import org.apache.commons.io.IOUtils;
import register.Register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhongbo on 2018/12/21.
 */
public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        try {
            //反序列化协议对象
            InputStream inputStream = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            ProtocolObject protocolObject = (ProtocolObject) ois.readObject();

            //找到实现类，开始调用接口
            String interfacename = protocolObject.getInterfaceName();

            URL url = new URL("localhost", 8080);
            Class clazz = Register.getClass(interfacename, url);

            Method method = clazz.getMethod(protocolObject.getMethodName(), protocolObject.getParamType());
            String result = (String) method.invoke(clazz.newInstance(), protocolObject.getParams());

            OutputStream outputStream = resp.getOutputStream();
            IOUtils.write(result, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
