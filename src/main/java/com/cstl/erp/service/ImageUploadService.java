package com.cstl.erp.service;

import com.cstl.erp.domain.Employee;
import com.cstl.erp.domain.ImageUpload;
import com.cstl.erp.repository.ImageUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageUploadService {

    @Autowired
    private ImageUploadRepository imageUploadRepository;

    public void imageSaved(ImageUpload img)
    {
        imageUploadRepository.save(img);
    }

    public void imageWithMaxId(Employee employee)
    {
        Long  id = imageUploadRepository.findMaxId();
        ImageUpload img = imageUploadRepository.findImageByMaxId(id);
        img.setName(employee.getEmpId());

        imageUploadRepository.save(img);

    }
}
