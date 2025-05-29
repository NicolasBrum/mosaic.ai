package com.mosaicai.api.controllers;

import com.mosaicai.api.models.UserDtoTest;
import com.mosaicai.api.services.EmailDomainValidatorService;
import com.mosaicai.api.services.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/mosaic")
@RestController
@Log4j2
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000", "http://127.0.0.1:5500"})
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
        try {
            boolean isValid = emailValidationService.isValidEmailDomain(email);
            if (isValid) {
                return ResponseEntity.ok("Your email is correct!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Invalid email domain.");
            }
        } catch (Exception e) {
            log.error("Error during email domain validation: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }
}
