package protocol.http;

import framework.ProtocolObject;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhongbo on 2018/12/21.
 */
public class HttpClient {
    public String post(String host, Integer port, ProtocolObject protocolObject){
        try {
            URL url=new URL("http",host,port,"/");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream=httpURLConnection.getOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(outputStream);
            oos.writeObject(protocolObject);
            oos.flush();
            oos.close();

            InputStream inputStream=httpURLConnection.getInputStream();
            return IOUtils.toString(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
