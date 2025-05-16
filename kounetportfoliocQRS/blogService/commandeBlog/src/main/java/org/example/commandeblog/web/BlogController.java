package org.example.commandeblog.web;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.example.commandeblog.service.ArticleCommandService;
import org.example.commandeblog.service.EventCommandService;
import org.example.commandeblog.service.ImageStorageService;
import org.example.commandeblog.service.NewsCommandService;

import org.example.polyinformatiquecoreapi.dto.ArticleDTO;
import org.example.polyinformatiquecoreapi.dto.EventDTO;
import org.example.polyinformatiquecoreapi.dto.NewsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping("/blog/command")
@CrossOrigin
public class BlogController {

    private final ArticleCommandService articleCommandService;
    private final NewsCommandService newsCommandService;
    private final EventCommandService eventCommandService;
    private final EventStore eventStore;
    private final ImageStorageService imageStorageService;

    public BlogController(
            ArticleCommandService articleCommandService,
            NewsCommandService newsCommandService,
            EventCommandService eventCommandService,
            EventStore eventStore, ImageStorageService imageStorageService
    ) {
        this.articleCommandService = articleCommandService;
        this.newsCommandService = newsCommandService;
        this.eventCommandService = eventCommandService;
        this.eventStore = eventStore;

        this.imageStorageService = imageStorageService;
    }

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<Void> createArticle(
            @RequestPart("article") @Valid ArticleDTO article,
            @RequestPart(value = "media", required = false) MultipartFile mediaFile
    ) {
        try {
            if (article.getId() == null || article.getId().isEmpty()) {
                article.setId(UUID.randomUUID().toString());
            }

            // Upload de l'image si présente
            if (mediaFile != null && !mediaFile.isEmpty()) {
                String imageUrl = imageStorageService.uploadImage(mediaFile);
                article.setUrlMedia(imageUrl); // supposons que ArticleDTO ait setUrlMedia()
            }

            articleCommandService.createArticle(article);
            return ResponseEntity.ok().build();

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/create-news")
    public ResponseEntity<Void> createNews(@Valid @RequestBody NewsDTO news) {
        if (news.getId() == null || news.getId().isEmpty()) {
            news.setId(UUID.randomUUID().toString());
        }
        newsCommandService.createNews(news);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-event")
    public ResponseEntity<Void> createEvent(@Valid @RequestBody EventDTO event) {
        if (event.getId() == null || event.getId().isEmpty()) {
            event.setId(UUID.randomUUID().toString());
        }
        eventCommandService.createEvent(event);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-article/{id}")
    public ResponseEntity<Void> updateArticle(@PathVariable String id, @Valid @RequestBody ArticleDTO article) {
        articleCommandService.updateArticle(id, article);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-news/{id}")
    public ResponseEntity<Void> updateNews(@PathVariable String id, @Valid @RequestBody NewsDTO news) {
        newsCommandService.updateNews(id, news);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-event/{id}")
    public ResponseEntity<Void> updateEvent(@PathVariable String id, @Valid @RequestBody EventDTO event) {
        eventCommandService.updateEvent(id, event);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-article/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable String id) {
        articleCommandService.deleteArticle(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-news/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable String id) {
        newsCommandService.deleteNews(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-event/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        eventCommandService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/events/{aggregateId}")
    public Stream<?> eventsStream(@PathVariable String aggregateId) {
        return eventStore.readEvents(aggregateId).asStream();
    }
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageStorageService.uploadImage(file);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Image upload failed: " + e.getMessage());
        }
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("⚠️ Error: " + exception.getMessage());
    }
}