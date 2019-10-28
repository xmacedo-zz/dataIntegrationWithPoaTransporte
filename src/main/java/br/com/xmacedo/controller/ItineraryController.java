package br.com.xmacedo.controller;

import java.util.List;
import java.util.Optional;

import br.com.xmacedo.model.ItineraryDto;
import br.com.xmacedo.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itinerario")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    @GetMapping
    public ResponseEntity<List<ItineraryDto>> listItinerary(@RequestParam Optional<Long> idLinha) {

        if (idLinha.isPresent()) {
            return ResponseEntity.ok(this.itineraryService.findItineraryByIdLinha(idLinha.get()));
        }

        return ResponseEntity.ok(this.itineraryService.findAll());
    }

    @PostMapping
    public ResponseEntity<List<ItineraryDto>> createOrUpdateItinerary(@RequestBody ItineraryDto form) {
        return ResponseEntity.ok(this.itineraryService.createOrUpdateItinerary(form));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteItineraryById(@RequestParam Long idLinha, @RequestParam Long idLocalizacao) {
        return ResponseEntity.ok(this.itineraryService.deleteItinerary(idLinha, idLocalizacao));
    }
}
