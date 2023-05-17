package kz.news.service;

import kz.news.dto.SourceDto;
import kz.news.entity.Source;

import java.util.List;
import java.util.Optional;

public interface SourceService {

    void saveSource(SourceDto sourceDto);

    List<SourceDto> findAllSource();

    Optional<SourceDto> updateSource(Long id, SourceDto sourceDto);

    boolean deleteSource(Long id);

    Optional<SourceDto> findById(Long id);
}
