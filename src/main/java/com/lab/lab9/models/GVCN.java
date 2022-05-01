package com.lab.lab9.models;

public class GVCN {
    private int classID;
    private String className;
    private String gvcnName;
    private String gvcnUsername;

    public GVCN(int classID, String className, String gvcnUsername, String gvcnName) {
        this.classID = classID;
        this.className = className;
        this.gvcnName = gvcnName;
        this.gvcnUsername = gvcnUsername;
    }

    public int getClassID() {
        return classID;
    }

    public String getClassName() {
        return className;
    }

    public String getGvcnName() {
        return gvcnName;
    }

    public String getGvcnUsername() {
        return gvcnUsername;
    }
}
