package com.angelis.tera.game.network;

import com.angelis.tera.game.models.WorldPosition;

public class TeleportLocations {
    public static WorldPosition getStartingPoint() {
        WorldPosition worldLocation = new WorldPosition(13, 93492.742188F, -88216.640625F, -4523.007324F, (short) -32767);
        return worldLocation;
    }
}