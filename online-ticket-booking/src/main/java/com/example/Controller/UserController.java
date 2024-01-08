package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.AuthRequestDTO;
import com.example.DTO.JwtResponseDTO;
import com.example.DTO.OtpVerificationRequest;
import com.example.Entity.UserInfo;
import com.example.Service.EmailService;
import com.example.Service.JwtService;
import com.example.Service.OtpService;
import com.example.Service.RegisterService;

@RestController
public class UserController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private RegisterService registerService;
	@Autowired
	private OtpService otpService;
	@Autowired
	private PasswordEncoder passwordencoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;

	
	@PostMapping("/userregistration")
    public ResponseEntity<String> register(@RequestBody UserInfo user) {
       return registerService.saveuser(user);
    }
	
 @PostMapping("/user/sendotp")
	public ResponseEntity<String> sendOtp(@RequestBody UserInfo passenger) {
		String userEmail=passenger.getEmail();
		UserInfo ui=registerService.findByEmail(userEmail);
		if(ui==null) {
			String otp=otpService.generateOtp(userEmail);
			emailService.sendOtpEmail(userEmail, otp);
			return ResponseEntity.ok("OTP sent to your email");
		} else {
			return ResponseEntity.badRequest().body("Email is already registered.");
			
		}
		
		}
 
	@PostMapping("/user/verify-otp")
		    public ResponseEntity<String> verifyOtp( @RequestBody  OtpVerificationRequest verificationRequest) {
		        String otp=verificationRequest.getOtp();
		        String email=verificationRequest.getEmail();
		        System.out.println(otp+email);
	 
		        if (otpService.validateOtp(email, otp)) {
		            return ResponseEntity.ok("OTP verified successfully");
		        } else {
		            return ResponseEntity.badRequest().body("Incorrect OTP.");
		        }
	 
		    }
	@PostMapping("/user/login")
	public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
	
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
	    if(authentication.isAuthenticated()){
	    	return JwtResponseDTO.builder().accessToken(jwtService.GenerateToken(authRequestDTO.getUsername())).build();
	    } else {
	        throw new UsernameNotFoundException("invalid user request..!!");
	    }
	    
	}
	@GetMapping("/user/getmsg")
    public String test() {
        try {
            return "Successfully Login Welcome to online ticket booking";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    } 
	

}
