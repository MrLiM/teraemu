package com.angelis.tera.game.models;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javolution.util.FastSet;

import com.angelis.tera.common.lang.IsObservable;
import com.angelis.tera.common.lang.IsObserver;
import com.angelis.tera.common.model.AbstractModel;

public abstract class TeraObject extends AbstractModel implements IsObservable<CreatureEventTypeEnum>, IsObserver<CreatureEventTypeEnum>, HasUid {
    private final Set<IsObserver<CreatureEventTypeEnum>> observers = Collections.synchronizedSet(new FastSet<IsObserver<CreatureEventTypeEnum>>());
    
    private final int uid;
    private WorldPosition worldPosition;
    
    public TeraObject(Integer id, int uid) {
        super(id);
        this.uid = uid;
    }
    
    public TeraObject(int uid) {
        super();
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }
    
    public WorldPosition getWorldPosition() {
        return worldPosition;
    }

    public void setWorldPosition(WorldPosition worldPosition) {
        this.worldPosition = worldPosition;
    }
    
    @Override
    public synchronized void addObserver(IsObserver<CreatureEventTypeEnum> observer) {
        synchronized (this.observers) {
            this.observers.add(observer);
        }
    }
    
    public synchronized void removeObserver(IsObserver<CreatureEventTypeEnum> observer) {
        synchronized (this.observers) {
            this.observers.remove(observer);
        }
    }

    @Override
    public synchronized void notifyObservers(CreatureEventTypeEnum event, Object... data) {
        synchronized (this.observers) {
            for (IsObserver<CreatureEventTypeEnum> observer : this.observers) {
                observer.onObservableUpdate(event, this, data);
            }
        }
    }
    
    @Override
    public synchronized Collection<IsObserver<CreatureEventTypeEnum>> getObservers() {
        return this.observers;
    }
}
