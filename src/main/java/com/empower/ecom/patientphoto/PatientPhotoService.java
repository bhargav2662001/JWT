package com.empower.ecom.patientphoto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PatientPhotoService {

    @Autowired
    private PatientPhotoRepository photoRepository;

    public PatientPhoto storeOrUpdatePhoto(PatientPhoto data) throws IOException {
        // Check if a photo already exists with the same email ID
        List<PatientPhoto> existingPhotos = photoRepository.findAllByemailId(data.getemailId());

        if (!existingPhotos.isEmpty()) {
            // If existing photos are found, update the first one
            PatientPhoto existingPhoto = existingPhotos.get(0);
            existingPhoto.setfileName(data.getfileName());
            existingPhoto.setfileType(data.getfileType());
            existingPhoto.setdata(data.getdata());
            // Save the updated photo
            return photoRepository.save(existingPhoto);
        } else {
            // If no existing photos, create a new entry
            return photoRepository.save(data);
        }
    }

    public List<PatientPhoto> getAllUploadedPhotos() {
        return photoRepository.findAll(); // Assuming findAll() fetches all records from repository
    }

    public List<PatientPhoto> getUploadedPhotosByEmail(String emailId) {
        return photoRepository.findAllByemailId(emailId); // Fetch photos by email ID
    }

    public PatientPhoto updatePhotos(Long OID, PatientPhoto updatedPhotoDetails) throws IOException {
        Optional<PatientPhoto> existingFileOptional = photoRepository.findById(OID);

        if (existingFileOptional.isPresent()) {
            PatientPhoto existingPhoto = existingFileOptional.get();

            // Update the file details
            existingPhoto.setemailId(updatedPhotoDetails.getemailId());
            existingPhoto.setfileName(updatedPhotoDetails.getfileName());
            existingPhoto.setfileType(updatedPhotoDetails.getfileType());
            existingPhoto.setdata(updatedPhotoDetails.getdata());
            // Add other fields as necessary

            // Save the updated file back to the repository
            return photoRepository.save(existingPhoto);
        } else {
            // File not found, handle as needed
            return null;
        }
    }
}
