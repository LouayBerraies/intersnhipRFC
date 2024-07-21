
package com.example.finale.controller;

import com.example.finale.dto.AuthenticationRequest;
import com.example.finale.dto.AuthenticationResponse;
import com.example.finale.entities.User;
import com.example.finale.enums.UserRole;
import com.example.finale.repository.UserRepository;
import com.example.finale.service.jwt.user.UserService;
import com.example.finale.utils.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.parser.Authorization;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil ;
    public static final String TOKEN_PREFIX ="bearer";
    public static final String HEADER_STRING ="Authorization";


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest , HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException, JSONException, ServletException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        }
        catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException("Incorrect username or password !!! ");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "User is not activated");
            return ;

        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        User user = userRepository.findFirstByEmail(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(authenticationRequest.getEmail());

        if (user != null) {

            // Vérifiez si l'utilisateur existe
            response.getWriter().write(new JSONObject()
                    .put("userID", user.getId())
                    .put("token", jwt)
                    .put("UserRole", user.getUserRole())

                    .toString()
            );

        }

        response.addHeader("access-control-expose-headers","Authorization");
        response.setHeader("Access-control-allow-Headers", "Authorization,X-PINGOTHER,X-Requested-With,Content-Type,Accept,X-Custom-header");
        response.setHeader(HEADER_STRING,TOKEN_PREFIX+jwt);

    }


    @GetMapping("/userList")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<Boolean> isUserAdmin(@PathVariable long userId) {
        boolean isAdmin = userService.isUserAdmin(userId);
        return ResponseEntity.ok(isAdmin);
    }



}
