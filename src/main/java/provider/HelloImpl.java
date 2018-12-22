package provider;

/**
 * Created by zhongbo on 2018/12/21.
 */
public class HelloImpl implements Hello {
    public String sayHello(String userName) {
        return "hello "+userName;
    }
}
