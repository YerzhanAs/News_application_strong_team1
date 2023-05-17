package kz.news.controller;

import kz.news.dto.SourceDto;
import kz.news.entity.Source;
import kz.news.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/news-source")
public class SourceController {

    private final SourceService service;


    @Autowired
    public SourceController(SourceService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SourceDto>> getAllNewsSources() {
        List<SourceDto> sourcesList = service.findAllSource();
        return  ResponseEntity.ok(sourcesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SourceDto> getNewsSourceById(@PathVariable("id") Long id) {
        Optional<SourceDto> source = service.findById(id);
        return source.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createNewsSource(@RequestBody SourceDto sourceDto) {
        service.saveSource(sourceDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SourceDto> updateSource(@PathVariable("id") Long id, @RequestBody SourceDto source) {
        Optional<SourceDto> updatedSource = service.updateSource(id, source);
        return updatedSource.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSource(@PathVariable Long id) {
        boolean deleted = service.deleteSource(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
