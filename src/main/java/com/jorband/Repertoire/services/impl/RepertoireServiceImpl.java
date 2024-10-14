package com.jorband.Repertoire.services.impl;

import com.jorband.Repertoire.dtos.request.SongRequest;
import com.jorband.Repertoire.dtos.request.SongUsageRequest;
import com.jorband.Repertoire.entities.SongEntity;
import com.jorband.Repertoire.exceptions.ResourceNotFoundException;
import com.jorband.Repertoire.repositories.SongRepository;
import com.jorband.Repertoire.services.RepertoireService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepertoireServiceImpl implements RepertoireService {

    private final SongRepository songRepository;
    @Override
    public List<SongEntity> getRepertoire() {
        return songRepository.findAll();
    }

    @Override
    public SongEntity getSingleSong(Long id) {
        return songRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Song was not found with id: "+id));
    }

    @Override
    public SongEntity addSong(SongRequest request) {
        SongEntity song = SongEntity.builder()
                .name(request.getName())
                .chord(request.getChord())
                .usage(0)
                .build();
        return songRepository.save(song);
    }

    @Override
    public void updateSongUsage(SongUsageRequest request) {
        List<SongEntity> songs = request.getSongIds()
                .stream()
                .map(id -> {
                    SongEntity song = getSingleSong(id);
                    song.setUsage(song.getUsage()+1);
                    return song;
                }).collect(Collectors.toList());
        songRepository.saveAll(songs);
    }
}
