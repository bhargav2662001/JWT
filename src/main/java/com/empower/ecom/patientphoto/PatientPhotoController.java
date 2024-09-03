package com.empower.ecom.patientphoto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PatientPhotoController {

    @Autowired
    private PatientPhotoService patientPhotoService;

    @PostMapping("/upload")
    public ResponseEntity<PatientPhoto> uploadFile(@RequestBody PatientPhoto data) {
        try {
            // Call the service to store or update the file
            PatientPhoto savedFile = patientPhotoService.storeOrUpdatePhoto(data);
            return new ResponseEntity<>(savedFile, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/photos")
    public ResponseEntity<List<PatientPhoto>> getAllUploadedPhotos() {
        try {
            List<PatientPhoto> photos = patientPhotoService.getAllUploadedPhotos();
            return new ResponseEntity<>(photos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/email/{emailId}")
    public ResponseEntity<List<PatientPhoto>> getUploadedPhotosByEmail(@PathVariable String emailId) {
        List<PatientPhoto> files = patientPhotoService.getUploadedPhotosByEmail(emailId);
        if (files.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(files, HttpStatus.OK);
        }
    }

    @PutMapping("/photos/{OID}")
    public ResponseEntity<PatientPhoto> updateUploadedFile(@PathVariable("OID") Long OID, @RequestBody PatientPhoto updatedPhotoDetails) {
        try {
            PatientPhoto updatedFile = patientPhotoService.updatePhotos(OID, updatedPhotoDetails);
            if (updatedFile != null) {
                return new ResponseEntity<>(updatedFile, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
