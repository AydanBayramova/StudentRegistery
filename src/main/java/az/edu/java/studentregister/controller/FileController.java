package az.edu.java.studentregister.controller;

import az.edu.java.studentregister.service.MinioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final MinioService minioService;

    @Operation(summary = "Upload a file")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(
            @Parameter(description = "File to upload", required = true)
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        minioService.uploadFile(
                file.getOriginalFilename(),
                file.getInputStream(),
                file.getSize(),
                file.getContentType()
        );
        return "File uploaded: " + file.getOriginalFilename();
    }
    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename) throws Exception {
        InputStream fileStream = minioService.downloadFile(filename);
        byte[] bytes = fileStream.readAllBytes();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(bytes);
    }
}
