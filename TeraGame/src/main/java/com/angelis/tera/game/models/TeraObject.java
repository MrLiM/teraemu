package com.angelis.tera.game.models;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javolution.util.FastSet;

import com.angelis.tera.common.lang.IsObservable;
import com.angelis.tera.common.lang.IsObserver;
import com.angelis.tera.common.model.AbstractModel;
import com.angelis.tera.common.model.HasUid;
import com.angelis.tera.game.controllers.Controller;

public abstract class TeraObject extends AbstractModel implements IsObservable<CreatureEventTypeEnum>, IsObserver<CreatureEventTypeEnum>, HasUid {
    
    /** OBSERVERS */
    private final Set<IsObserver<CreatureEventTypeEnum>> observers = Collections.synchronizedSet(new FastSet<IsObserver<CreatureEventTypeEnum>>());
    
    protected final Controller<? extends TeraObject> controller;
    private WorldPosition worldPosition;
    
    public TeraObject(Integer id, int uid, Controller<? extends TeraObject> controller) {
        super(id, uid);
        this.controller = controller;
    }
    
    public TeraObject(int uid, Controller<? extends TeraObject> controller) {
        this(null, uid, controller);
    }
    
    public WorldPosition getWorldPosition() {
        return worldPosition;
    }

    public void setWorldPosition(WorldPosition worldPosition) {
        this.worldPosition = worldPosition;
    }
    
    public abstract Controller<? extends TeraObject> getController();
    
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
