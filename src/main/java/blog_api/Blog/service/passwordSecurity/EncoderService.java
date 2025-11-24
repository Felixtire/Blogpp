package blog_api.Blog.service.passwordSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncoderService {


    @Autowired
    private PasswordEncoder encoder;


    public String encodePassword(String senha){
        return encoder.encode(senha);
    }


}
