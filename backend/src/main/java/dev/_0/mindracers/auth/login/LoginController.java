package dev._0.mindracers.auth.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import dev._0.mindracers.user.UserRepository;
import dev._0.mindracers.user.User;

@CrossOrigin
@RestController
@RequestMapping(path = "/auth")

public class LoginController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping(path = "/login")
  public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password){
    User user = userRepository.findByEmail(email);

    //Successful login
    if(user != null && user.getPassword().equals(password)){
      //return instance of a user
      return ResponseEntity.ok(user);
    }

    //Failed login
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\\\"success\\\":0, \\\"message\\\":\\\"Invalid email or password\\\"}");
  
  }

}
