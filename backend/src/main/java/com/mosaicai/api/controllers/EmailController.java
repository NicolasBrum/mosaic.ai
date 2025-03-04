package com.mosaicai.api.controllers;

import com.mosaicai.api.models.UserDtoTest;
import com.mosaicai.api.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/mosaic")
@RestController
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody UserDtoTest personInfo) {
        try {
            emailService.enviarEmail(personInfo);
            return ResponseEntity.ok("Your email was successfully sent!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Unexpected error: " + e.getMessage());
        }
    }
}
