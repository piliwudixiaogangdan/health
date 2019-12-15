import jdk.nashorn.internal.ir.CallNode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String root = bCryptPasswordEncoder.encode("root");
        System.out.println(root);
    }
}
