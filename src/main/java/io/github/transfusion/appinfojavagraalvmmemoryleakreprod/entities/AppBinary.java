package io.github.transfusion.appinfojavagraalvmmemoryleakreprod.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public abstract class AppBinary {

    private UUID id;

    private String version;

    private String build;

    private Instant uploadDate;

    private String name;

    private Instant lastInstallDate;

    private String identifier;

    private Boolean assetsOnFrontPage = false;

    private BigDecimal sizeBytes;

    private String fileName;

    private UUID storageCredential;

    private UUID userId;

    private UUID organizationId;

    private String description;

    private Boolean available = false;

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getStorageCredential() {
        return storageCredential;
    }

    public void setStorageCredential(UUID storageCredential) {
        this.storageCredential = storageCredential;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public Instant getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Instant uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getLastInstallDate() {
        return lastInstallDate;
    }

    public void setLastInstallDate(Instant lastInstallDate) {
        this.lastInstallDate = lastInstallDate;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Boolean getAssetsOnFrontPage() {
        return assetsOnFrontPage;
    }

    public void setAssetsOnFrontPage(Boolean assetsOnFrontPage) {
        this.assetsOnFrontPage = assetsOnFrontPage;
    }

    public BigDecimal getSizeBytes() {
        return sizeBytes;
    }

    public void setSizeBytes(BigDecimal sizeBytes) {
        this.sizeBytes = sizeBytes;
    }

}