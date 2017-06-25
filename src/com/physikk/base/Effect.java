/*
 * Copyright (C) 2017 Sleepersword
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.physikk.base;

import java.util.HashMap;

/**
 * Describes a physical effect that can relate on the properties of objects or the time.
 * Call protected constructor in your implementation!
 * @author Sleepersword
 * @param <T>
 */
public abstract class Effect<T extends PhysicObject> extends Activatable
{
    protected String name;
    protected HashMap<String, T> attachedObjects;
    private SystemManager manager;
    
    protected Effect(String name, boolean isActive) {
        this.name = name;
        this.active = isActive;
        this.attachedObjects = new HashMap<>();
    }
    
    /**
     * Adds a new PhysicObject or replaces an existing one.
     * @param object The new object.
     */
    public void setObject(T object) {
        attachedObjects.put(object.name, object);
    }    
    
    /**
     * Removes the given PhysicObject, if it exists.
     * @param object The PhysicObject to be removed.
     */
    public void removeObject(T object) {        
        attachedObjects.remove(object.name);
    }
        
    /**
     * Gets called internally by a SystemManager
     */
    protected abstract void update(); 
    protected abstract void updateSecond();    
    
    /**
     * Gets called if you call SystemManager.addEffect
     */
    final void setSystemManager(SystemManager manager) {
        this.manager = manager;
    }
    
    final void setObjectsFromSystemManager(HashMap<String, T> list) {
        attachedObjects = list;
    }
}
