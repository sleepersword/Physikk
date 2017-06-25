/*
 * Copyright (C) 2017 sleepersword
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

//TODO: Create updateSecond()

/**
 * The base class for all physical objects.
 * Call protected constructor in your implementation!
 * @author sleepersword
 */
public abstract class PhysicObject extends Activatable
{    
    protected String name;
    protected Vector position;
    protected Vector velocity;
    protected HashMap<String, NamedVector> forces;
    protected double mass;
    
    private SystemManager manager;
    
    protected PhysicObject(String name, boolean isActive) {
        this.name = name;
        this.active = isActive;
        this.forces = new HashMap<>();
    }
    
    /// Full implemented
    
    /**
     * Calculates the total force on this object by superposition.
     * @return The total force.
     */
    public final NamedVector getTotalForce() {
        Vector res = Vector.NULLVECTOR();
        
        for(Vector f : forces.values()){
            res = res.sum(f);
        }
        
        return new NamedVector("total_force", res);
    }
    
    /**
     * Adds a new force or replaces an existing one.
     * @param force The new force.
     */
    public void setForce(NamedVector force) {
        forces.put(force.name, force);
    }    
    
    /**
     * Removes the given force, if it exists.
     * @param force The force to be removed.
     */
    public void removeForce(NamedVector force) {        
        forces.remove(force.name);
    }    
    
    /**
     * Returns the name of this object.
     * @return The name.
     */
    public final String getName() {
        return name;
    }
    
    /**
     * Returns the position of this object.
     * @return The position.
     */
    public Vector getPosition() {
        return position;
    }
    
    /**
     * Returns the velocity of this object.
     * @return The velocity.
     */
    public Vector getVelocity() {
        return velocity;
    }
        
    /**
     * Returns the mass of this object.
     * @return The mass.
     */
    public Double getMass() {
        return mass;
    }
    
    /**
     * Forces a manual update.
     */
    public void manualUpdate() {
        this.update();
    }
    
    /**
     * Gets called internally by a SystemManager
     */
    final void update() {        
        // Call specific method tick()
        this.tick();
        
        // Calculate acceleration
        Vector acceleration = getTotalForce().scale(1.0 / getMass() );
        
        // Calculate the change of the velocity
        Vector delta_velo = acceleration.scale( Utils.SECONDS_PER_TICK );
        this.velocity = this.velocity.sum(delta_velo);
        
        // Calculate the change of the position
        Vector delta_pos = getVelocity().scale( Utils.SECONDS_PER_TICK );
        this.position = this.position.sum(delta_pos);
    }
    
    /**
     * Gets called internally by a SystemManager
     */
    final void updateSecond() {
        tickSecond();
    }
        
    /**
     * Gets called if you call SystemManager.addObject
     */
    final void setSystemManager(SystemManager manager) {
        this.manager = manager;
    }
    /// Abstract
    
    protected abstract void tick();
    protected abstract void tickSecond();
    
    // Oberrides
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("[PhysicObject:"); sb.append(getName()); sb.append("]");
        sb.append("\n  Mass="); sb.append(getMass().toString());
        sb.append("\n  Position="); sb.append(getPosition().toString());
        sb.append("\n  Velocity="); sb.append(getVelocity().toString());
        
        return sb.toString();
    }
}
