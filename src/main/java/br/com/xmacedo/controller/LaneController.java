package br.com.xmacedo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import br.com.xmacedo.model.LaneDto;
import br.com.xmacedo.service.LaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/linha")
public class LaneController {

    @Autowired
    private LaneService laneService;

    @GetMapping
    public ResponseEntity<List<LaneDto>> listLanes(@RequestParam Optional<String> nome) {
        if (nome.isPresent()) {
            return ResponseEntity.ok(this.laneService.findByName(nome.get()));
        }

        return ResponseEntity.ok(this.laneService.findAll());
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<Optional<LaneDto>> findLaneById(@RequestParam(required = false) Optional<Long> id,
        @RequestParam Optional<Long> idLinha) {

        if (id.isPresent()) {
            return ResponseEntity.ok(this.laneService.findLaneDtoById(id.get()));
        }

        if (idLinha.isPresent()) {
            return ResponseEntity.ok(this.laneService.findLaneDtoByIdLane(idLinha.get()));
        }
        //Todo melhorar respostas de erro
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/raio")
    public ResponseEntity<List<LaneDto>> findLanesByRadius(@RequestParam Double latitude, Double longitude, Double raio)
        throws IOException {

        return ResponseEntity.ok(this.laneService.findLanesByRadius(latitude, longitude, raio));
    }

    @PostMapping
    public ResponseEntity<LaneDto> createOrUpdateLane(@RequestBody LaneDto form) throws IOException {
        return ResponseEntity.ok(this.laneService.createOrUpdateLane(form));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLaneById(@RequestParam Long id) {
        return ResponseEntity.ok(this.laneService.deleteLaneById(id));
    }

}
