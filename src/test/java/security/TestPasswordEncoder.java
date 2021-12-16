package security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestPasswordEncoder {

    @Test
    public void test01(){
      PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
      //给密码按照一定规则加密并返回加密后的结果
        System.out.println(passwordEncoder.encode("123456"));
    }

    @Test
    public void test02(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //判断原始密码是否和加密密码相等
        System.out.println(encoder.matches("123456","$2a$10$leZw3G9w5S.9u1mlnQfWd.V58U81Gl/FrfyAPB3W6OYbl61SFyPhO"));
        //判断加密的密码是否能够再次加密达到更安全的效果
        System.out.println(encoder.upgradeEncoding("$2a$10$FaUp8Rl9nabyoaG0mcuyj.XXUDAtCnUaU7xIY25dpnvOgiyTAC31m"));

    }

}
