package ru.hogwarts.school.controller;

import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.record.AvatarRecord;
import ru.hogwarts.school.service.AvatarService;

import java.io.IOException;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping
    public AvatarRecord create(@RequestParam MultipartFile avatar,
                               @RequestParam Long studentId) throws IOException {
        return avatarService.addAvatar(avatar, studentId);

    }

    @GetMapping("/from-fs")
    public ResponseEntity<byte[]> findFromFs(@PathVariable long id) {
        Pair<byte[], String> pair = avatarService.findFromFs(id);

        return ResponseEntity.ok()
                .contentLength(pair.getFirst().length)
                .contentType(MediaType.parseMediaType(pair.getSecond()))
                .body(pair.getFirst());

    }

    @GetMapping("/from-db")
    public ResponseEntity<byte[]> findFromDb(@PathVariable long id) {
        Pair<byte[], String> pair = avatarService.findFromDb(id);

        return ResponseEntity.ok()
                .contentLength(pair.getFirst().length)
                .contentType(MediaType.parseMediaType(pair.getSecond()))
                .body(pair.getFirst());

    }

}
