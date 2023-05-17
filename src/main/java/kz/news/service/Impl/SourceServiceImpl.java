package kz.news.service.Impl;


import kz.news.dto.SourceDto;
import kz.news.entity.Source;
import kz.news.repository.SourceRepository;
import kz.news.service.SourceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SourceServiceImpl implements SourceService {

    private final SourceRepository sourceRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SourceServiceImpl(SourceRepository sourceRepository, ModelMapper modelMapper) {
        this.sourceRepository = sourceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveSource(SourceDto sourceDto) {
       sourceRepository.save(convertToSource(sourceDto));
    }

    @Override
    public Optional<SourceDto> findById(Long id) {
//        return sourceRepository.findById(id);

        Optional<Source> optionalSource = sourceRepository.findById(id);

        if (optionalSource.isPresent()) {
            Source existingSource =  optionalSource.get();

            return Optional.of(convertToSourceDTO(existingSource));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public List<SourceDto> findAllSource() {
        return sourceRepository.findAll().stream().map(this::convertToSourceDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<SourceDto> updateSource(Long id, SourceDto sourceDto) {
        Optional<Source> optionalSource = sourceRepository.findById(id);

        if (optionalSource.isPresent()) {
            Source existingSource = optionalSource.get();
            existingSource.setName(sourceDto.getName());
            existingSource.setUrl(sourceDto.getUrl());

            Source updatedSource = sourceRepository.save(existingSource);
            return Optional.of(convertToSourceDTO(updatedSource));
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteSource(Long id) {
        if (sourceRepository.existsById(id)) {
            sourceRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private SourceDto convertToSourceDTO(Source source){
        return modelMapper.map(source, SourceDto.class);
    }

    private Source convertToSource(SourceDto sourceDto) {
        return modelMapper.map(sourceDto, Source.class);
    }
}
