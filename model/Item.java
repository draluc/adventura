package model;


public class Item
{
    private String name;
    private String description;
    private boolean moveable;
    
    public Item(String name, String description) {
        this(name, description, true);
    }
    
    public Item(String name, String description, boolean moveable) {
        this.name = name;
        this.description = description;
        this.moveable = moveable;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isMoveable() {
        return moveable;
    }
    
    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }
}
