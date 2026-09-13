package it.uniroma3.siw.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.dto.RecensioneDTO;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.RecensioneService;
import it.uniroma3.siw.service.UtenteService;

@RestController
@RequestMapping("/api")
public class RecensioneRestController {

    private final RecensioneService recensioneService;
    private final UtenteService utenteService;

    public RecensioneRestController(
            RecensioneService recensioneService,
            UtenteService utenteService) {

        this.recensioneService = recensioneService;
        this.utenteService = utenteService;
    }

    @GetMapping("/registered/{id}/recensioni")
    public ResponseEntity<List<RecensioneDTO>> getRecensioni(
            @PathVariable Long id) {

        Utente user = utenteService.findById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<RecensioneDTO> recensioni = user.getReviews()
                .stream()
                .map(rev -> new RecensioneDTO(
                        rev.getId(),
                        rev.getText(),
                        rev.getVote(),
                        rev.getDate(),
                        rev.getFilm() != null
                                ? rev.getFilm().getTitle()
                                : "Film sconosciuto"
                ))

                .collect(Collectors.toList());

        return ResponseEntity.ok(recensioni);
    }

    @DeleteMapping("/recensione/{id}")
    public ResponseEntity<Void> deleteRecensione(
            @PathVariable Long id) {

        Recensione recensione = recensioneService.findById(id);

        if (recensione == null) {
            return ResponseEntity.notFound().build();
        }

        recensioneService.delete(id);

        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/recensione/{id}")
    public ResponseEntity<RecensioneDTO> updateRecensione(
            @PathVariable Long id,
            @RequestBody RecensioneDTO dto) {

        Recensione recensione = recensioneService.updateRecensione(
                id,
                dto.getVote(),
                dto.getText()
        );

        if (recensione == null) {
            return ResponseEntity.notFound().build();
        }

        RecensioneDTO response = new RecensioneDTO(
                recensione.getId(),
                recensione.getText(),
                recensione.getVote(),
                recensione.getDate(),
                recensione.getFilm().getTitle()
        );

        return ResponseEntity.ok(response);
    }

}
