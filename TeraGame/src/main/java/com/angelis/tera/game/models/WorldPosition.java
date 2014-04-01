package com.angelis.tera.game.models;

public final class WorldPosition {
    
    private int mapId;
    private float x;
    private float y;
    private float z;
    private short heading;
    
    public WorldPosition() {
        super();
    }
    
    public WorldPosition(int mapId, float x, float y, float z) {
        super();
        this.mapId = mapId;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public WorldPosition(int mapId, float x, float y, float z, short heading) {
        super();
        this.mapId = mapId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.heading = heading;
    }
    
    public double distanceTo(float x, float y) {
        double a = x - this.x;
        double b = y - this.y;

        return Math.sqrt(a*a + b*b);
    }
    
    public double distanceTo(float x, float y, float z) {
        double a = x - this.x;
        double b = y - this.y;
        double c = z - this.z;

        return Math.sqrt(a*a + b*b + c*c);
    }
    
    public double distanceTo(WorldPosition otherWorldPosition) {
        if (otherWorldPosition == null) {
            return Double.MAX_VALUE;
        }
        
        return this.distanceTo(otherWorldPosition.getX(), otherWorldPosition.getY(), otherWorldPosition.getZ());
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
    
    public void setXYZ(float x, float y, float z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    public short getHeading() {
        return heading;
    }

    public void setHeading(short heading) {
        this.heading = heading;
    }
}
