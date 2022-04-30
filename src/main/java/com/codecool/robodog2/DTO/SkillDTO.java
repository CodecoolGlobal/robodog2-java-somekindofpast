package com.codecool.robodog2.DTO;

public class SkillDTO {

    private long dogId;
    private long trickId;
    private int level;

    public SkillDTO(long dogId, long trickId, int level) {
        this.dogId = dogId;
        this.trickId = trickId;
        this.level = level;
    }

    public SkillDTO() {
    }

    public long getDogId() {
        return dogId;
    }

    public void setDogId(long dogId) {
        this.dogId = dogId;
    }

    public long getTrickId() {
        return trickId;
    }

    public void setTrickId(long trickId) {
        this.trickId = trickId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
