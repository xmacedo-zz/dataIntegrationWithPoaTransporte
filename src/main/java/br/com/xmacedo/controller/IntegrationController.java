package br.com.xmacedo.controller;

import java.io.IOException;

import br.com.xmacedo.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/integracao")
public class IntegrationController {

    @Autowired
    private IntegrationService integrationService;

    @GetMapping("")
    public ResponseEntity<HttpStatus> loadedFromAPI() throws IOException {
        this.integrationService.loadFromAPI();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
