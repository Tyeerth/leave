package cn.jxust.leave.security;

import cn.jxust.leave.utils.MD5Utils;
import cn.jxust.leave.utils.StringUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 定义md5的加密方式
 * @author tyeerth
 * @date 2020/5/1 - 21:23
 */
@Component
public class PasswordEncoderService implements PasswordEncoder {
    /**
     * encode()方法对明文进行加密。
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Utils.md5(rawPassword.toString());
    }

    /**
     * matches()方法对明文加密后和密文进行比较
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String result = MD5Utils.md5(rawPassword.toString());
        return Objects.equals(result, encodedPassword);
    }
}
