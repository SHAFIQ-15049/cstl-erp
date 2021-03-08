package com.cstl.erp.resources;

import com.cstl.erp.domain.Employee;
import com.cstl.erp.domain.ImageUpload;
import com.cstl.erp.repository.EmployeeRepository;
import com.cstl.erp.repository.ImageUploadRepository;
import com.cstl.erp.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("api/image_store")
public class ImageUploadResource {

    @Autowired
    private ImageUploadRepository imageUploadRepository;
    @Autowired
    private ImageUploadService imageUploadService;


    @PostMapping("/saveimage")
    public ResponseEntity.BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        ImageUpload img = new ImageUpload(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        imageUploadService.imageSaved(img);
        return ResponseEntity.status(HttpStatus.OK);
    }

    @GetMapping(path = { "/getimage/{imageName}" })
    public ImageUpload getImage(@PathVariable("imageName") String imageName) throws IOException {

        final Optional<ImageUpload> retrievedImage = imageUploadRepository.findByName(imageName);
        ImageUpload img = new ImageUpload(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }


}
