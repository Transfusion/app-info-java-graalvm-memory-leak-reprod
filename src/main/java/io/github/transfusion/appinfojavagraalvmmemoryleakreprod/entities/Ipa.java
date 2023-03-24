package io.github.transfusion.appinfojavagraalvmmemoryleakreprod.entities;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.Instant;
import java.util.List;

public class Ipa extends AppBinary {

    private String minSdkVersion;


    private Boolean iphone = false;

    private Boolean ipad = false;

    private Boolean universal = false;

    private String deviceType;

    private String displayName;

    private String releaseType;

    private String buildType;

    private Instant expiredDate;

    private String teamName;

    private List<String> archs;

    private List<String> devices;

    public List<String> getDevices() {
        return devices;
    }

    public void setDevices(List<String> devices) {
        this.devices = devices;
    }

    public List<String> getArchs() {
        return archs;
    }

    public void setArchs(List<String> archs) {
        this.archs = archs;
    }

    public String getMinSdkVersion() {
        return minSdkVersion;
    }

    public void setMinSdkVersion(String minSdkVersion) {
        this.minSdkVersion = minSdkVersion;
    }

    public Boolean getIphone() {
        return iphone;
    }

    public void setIphone(Boolean iphone) {
        this.iphone = iphone;
    }

    public Boolean getIpad() {
        return ipad;
    }

    public void setIpad(Boolean ipad) {
        this.ipad = ipad;
    }

    public Boolean getUniversal() {
        return universal;
    }

    public void setUniversal(Boolean universal) {
        this.universal = universal;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(String releaseType) {
        this.releaseType = releaseType;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Instant getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Instant expiredDate) {
        this.expiredDate = expiredDate;
    }

   /* public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/



    private JsonNode plistJson;

    public void setPlistJson(JsonNode plistJson) {
        this.plistJson = plistJson;
    }

    public JsonNode getPlistJson() {
        return plistJson;
    }

}