package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.component.RecordMapper;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.exception.AvatarNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.record.AvatarRecord;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvatarService {
    private static final Logger LOG = LoggerFactory.getLogger(AvatarService.class);
    @Value("${application.avatar.store}")
    private String avatarDir;
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private final RecordMapper recordMapper;

    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository, RecordMapper recordMapper) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
        this.recordMapper = recordMapper;
    }

    public AvatarRecord addAvatar(MultipartFile avatarFile, long studentId) throws IOException {
        LOG.debug("Method addAvatar was invoked");
        Student student = studentRepository.findById(studentId).orElseThrow(() -> {
            LOG.error("There is not student with id = " + studentId);
            throw new StudentNotFoundException(studentId);
        });

        String extension = Optional.ofNullable(avatarFile.getOriginalFilename())
                .map(f -> f.substring(f.lastIndexOf(".")))
                .orElse("");
        Path path = Path.of(avatarDir, student + extension);

        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
        Files.write(path, avatarFile.getBytes());

        Avatar avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setStudent(student);
        avatar.setFilePath(path.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        return recordMapper.toRecord(avatarRepository.save(avatar));

    }

    public Pair<byte[], String> findFromDb(long id) {
        LOG.debug("Was invoked method for findFromDb");
        Avatar avatar = avatarRepository.findById(id).orElseThrow(() -> {
            LOG.error("There is not avatar with id = " + id);
            throw new AvatarNotFoundException(id);
        });
        return Pair.of(avatar.getData(), avatar.getMediaType());
    }

    public Pair<byte[], String> findFromFs(long id) throws IOException {
        LOG.debug("Was invoked method for findFromFs");
        Avatar avatar = avatarRepository.findById(id).orElseThrow(() -> {
            LOG.error("There is not avatar with id = " + id);
            throw new AvatarNotFoundException(id);
        });
        Path path = Path.of(avatar.getFilePath());
        return Pair.of(Files.readAllBytes(path), avatar.getMediaType());
    }

    public Collection<AvatarRecord> getAllByPage(int pageNumber, int pageSize) {
        LOG.debug("Was invoked method for getAllByPage");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent().stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }


}

