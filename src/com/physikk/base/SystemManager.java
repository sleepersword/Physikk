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
 * Static class that handles all PhysicObjects and Effects of a closed System.
 * @author Sleepersword
 */
public class SystemManager
{
    protected HashMap<String, PhysicObject> objects;
    protected HashMap<String, Effect> effects;
    
    private boolean isRunning;
    
    public SystemManager() {
        objects = new HashMap<>();
        effects = new HashMap<>();
        isRunning = false;       
    }
    
    public void addObject(PhysicObject obj) {
        objects.put(obj.name, obj);
        obj.setSystemManager(this);
    }
    
    public void addObjects(PhysicObject... objs) {
        for(PhysicObject o : objs) {
            addObject(o);
        }
    }
    
    public void addEffect(Effect fx, boolean useManagerObjects) {
        effects.put(fx.name, fx);
        fx.setSystemManager(this);
        if(useManagerObjects) fx.setObjectsFromSystemManager(objects);
    }
    
    public void addEffects(boolean useManagerObjects, Effect... fxs) {
        for(Effect f : fxs) {
            addEffect(f, useManagerObjects);
        }
    }
    
    public void start() {
        if(isRunning) {
            return;
        }
        isRunning = true;
        run();
    }
    
    public void stop() {
        if(!isRunning) {
            return;
        }
        isRunning = false;
    }
        
    protected void run() {
        long initialTime = System.currentTimeMillis();
        long currentTime;
        double delta = 0.0;
        int ticks = 0;
        
        while(isRunning) {
            currentTime = System.currentTimeMillis();
            delta += (currentTime - initialTime) / (Utils.SECONDS_PER_TICK * 1000);
            initialTime = currentTime;
                     
            if(delta >= 1) {
                //Update here  
                effects.values().forEach( (fx) -> { fx.update(); } );
                objects.values().forEach( (obj) -> { obj.update(); } );
                //
                delta--;
                ticks++;
            }
                  
            if(ticks == Utils.TICKS_PER_SECOND) {
                //Update here  
                effects.values().forEach( (fx) -> { fx.updateSecond(); } );
                objects.values().forEach( (obj) -> { obj.updateSecond(); } );
                //
                ticks = 0;
                System.out.println("-----------------------------------------------------------------");
            }
            
        }
    }
    
}
