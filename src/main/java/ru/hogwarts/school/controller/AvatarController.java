package ru.hogwarts.school.controller;

import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.record.AvatarRecord;
import ru.hogwarts.school.service.AvatarService;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AvatarRecord addAvatar(@RequestParam MultipartFile avatar,
                                  @RequestParam Long studentId) throws IOException {
        return avatarService.addAvatar(avatar, studentId);

    }

    @GetMapping("/from-fs")
    public ResponseEntity<byte[]> findFromFs(@PathVariable long id) throws IOException {
        Pair<byte[], String> pair = avatarService.findFromFs(id);

        return find(pair);

    }

    @GetMapping("/from-db")
    public ResponseEntity<byte[]> findFromDb(@PathVariable long id) throws IOException {
        Pair<byte[], String> pair = avatarService.findFromDb(id);

        return find(pair);
    }

    private ResponseEntity<byte[]> find(Pair<byte[], String> pair) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentLength(pair.getFirst().length)
                .contentType(MediaType.parseMediaType(pair.getSecond()))
                .body(pair.getFirst());
    }

    @GetMapping
    public ResponseEntity<Collection<AvatarRecord>> getAllByPage(@RequestParam int pageNumber,
                                                                 @RequestParam int pageSize) {
        return ResponseEntity.ok(avatarService.getAllByPage(pageNumber, pageSize));
    }


}
