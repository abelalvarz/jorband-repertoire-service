package com.jorband.Repertoire.controllers;

import com.jorband.Repertoire.converters.ResponseConverter;
import com.jorband.Repertoire.dtos.request.SongRequest;
import com.jorband.Repertoire.dtos.response.Response;
import com.jorband.Repertoire.services.RepertoireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.jorband.Repertoire.utils.Constants.*;

@RestController
@RequestMapping(BASE_URL + "/repertoire")
@RequiredArgsConstructor
public class RepertoireController {

    private final ResponseConverter responseConverter;
    private final RepertoireService repertoireService;

    @PostMapping
    public ResponseEntity<Response> addSong(@RequestBody SongRequest request){
        return ResponseEntity.ok(responseConverter.convert(repertoireService.addSong(request)));
    }

    @GetMapping
    public ResponseEntity<Response> getRepertoire(){
        return ResponseEntity.ok(responseConverter.convert(repertoireService.getRepertoire()));
    }
    @GetMapping("/single/{songId}")
    public ResponseEntity<Response> getSingleSong(@PathVariable("songId") Long id){
        return ResponseEntity.ok(responseConverter.convert(repertoireService.getSingleSong(id)));
    }


}
