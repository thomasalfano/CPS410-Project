package dev._0.mindracers.auth.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev._0.mindracers.example.testdatamysql.User;
import dev._0.mindracers.example.testdatamysql.UserRepository;

@CrossOrigin
@RestController
@RequestMapping(path="/auth")
public class SignUpController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/signup")
    public @ResponseBody String addNewUser(@RequestParam String email ,
    @RequestParam String username , @RequestParam String password) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    if (userRepository.findByEmail(email) != null) {
        return "{\"success\":0}";
    }

    User n = new User();
    n.setEmail(email);
    n.setUsername(username);
    n.setPassword(password);
    userRepository.save(n);
    return "{\"success\":1}";
  }
}
