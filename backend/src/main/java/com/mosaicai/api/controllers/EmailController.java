package com.mosaicai.api.controllers;

import com.mosaicai.api.models.UserDtoTest;
import com.mosaicai.api.services.EmailDomainValidatorService;
import com.mosaicai.api.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/mosaic")
@RestController
public class EmailController {
    private final EmailService emailService;
    private final EmailDomainValidatorService emailValidationService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody UserDtoTest personInfo) {
        try {
            emailService.enviarEmail(personInfo);
            return ResponseEntity.ok("Your email was successfully sent!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/validate-domain")
    public ResponseEntity<String> validateEmailDomain(@RequestParam String email) {
        try{
            emailValidationService.isValidEmailDomain(email);
            return ResponseEntity.ok("Your email is correct!");
        } catch (Exception e){
            return ResponseEntity.status(400).body("Unexpected error: " + e.getMessage());
        }
    }
}
