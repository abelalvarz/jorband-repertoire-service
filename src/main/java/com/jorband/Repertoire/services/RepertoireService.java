package com.jorband.Repertoire.services;

import com.jorband.Repertoire.dtos.request.SongRequest;
import com.jorband.Repertoire.dtos.request.SongUsageRequest;
import com.jorband.Repertoire.entities.SongEntity;

import java.util.List;

public interface RepertoireService {

    List<SongEntity> getRepertoire();
    SongEntity getSingleSong(Long id);
    SongEntity addSong(SongRequest request);
    void updateSongUsage(SongUsageRequest request);
}
