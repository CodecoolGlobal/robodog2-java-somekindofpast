package com.codecool.robodog2.DTO;

public class PedigreeDTO {

    private long momId;
    private long dadId;
    private long puppyId;

    public PedigreeDTO(long momId, long dadId, long puppyId) {
        this.momId = momId;
        this.dadId = dadId;
        this.puppyId = puppyId;
    }

    public PedigreeDTO() {
    }

    public long getMomId() {
        return momId;
    }

    public void setMomId(long momId) {
        this.momId = momId;
    }

    public long getDadId() {
        return dadId;
    }

    public void setDadId(long dadId) {
        this.dadId = dadId;
    }

    public long getPuppyId() {
        return puppyId;
    }

    public void setPuppyId(long puppyId) {
        this.puppyId = puppyId;
    }
}
