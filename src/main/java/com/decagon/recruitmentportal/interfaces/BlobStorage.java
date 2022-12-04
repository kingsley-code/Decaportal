package com.decagon.recruitmentportal.interfaces;
import com.decagon.recruitmentportal.pojos.APIResponse;
import org.springframework.web.multipart.MultipartFile;

public interface BlobStorage {

    APIResponse upload (MultipartFile file, String preFix);
    void delete (String identifier);
}
