package net.dantemc.civitascapes.cape;

public class CapeDefinition {
    private String id;
    private String displayName;
    private CapeType type;
    private String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public CapeType getType() {
        return type;
    }

    public void setType(CapeType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
