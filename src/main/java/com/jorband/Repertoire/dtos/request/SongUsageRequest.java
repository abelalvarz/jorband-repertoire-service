package com.jorband.Repertoire.dtos.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SongUsageRequest {
    List<Long> songIds;
}
