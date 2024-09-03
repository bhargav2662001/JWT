package com.empower.ecom.patientphoto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;

import java.io.IOException;
import java.util.Base64;

@Entity
public class PatientPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name = "person_seq", sequenceName = "PERSON_SEQ", allocationSize = 1)
    private Long OID;

    private String emailId;

    private String fileName;

    private String fileType;

    @Lob
    @JsonDeserialize(using = Base64Deserializer.class)
    private byte[] data; // Ensure this is mapped as a byte array to avoid OID issues

    public Long getOID() {
        return OID;
    }

    public void setOID(Long OID) {
        this.OID = OID;
    }

    public String getemailId() {
        return emailId;
    }

    public void setemailId(String emailId) {
        this.emailId = emailId;
    }

    public String getfileName() {
        return fileName;
    }

    public void setfileName(String fileName) {
        this.fileName = fileName;
    }

    public String getfileType() {
        return fileType;
    }

    public void setfileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getdata() {
        return data;
    }

    public void setdata(byte[] data) {
        this.data = data;
    }


    public static class Base64Deserializer extends JsonDeserializer<byte[]> {
        @Override
        public byte[] deserialize(JsonParser jsonParser, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            String base64String = jsonParser.getText();
            return Base64.getDecoder().decode(base64String);
        }
    }
}
