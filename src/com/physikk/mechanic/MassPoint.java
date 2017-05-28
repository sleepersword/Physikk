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
package com.physikk.mechanic;

import com.physikk.base.*;

/**
 *
 * @author Sleepersword
 */
public class MassPoint extends PhysicObject
{
    public MassPoint(double mass, Vector initialPos, Vector initialVelo, NamedVector... initialForces) {
        super(true);
        this.mass = mass;
        this.position = initialPos;
        this.velocity = initialVelo;
        
        for(NamedVector f : initialForces) {
            this.forces.put(f.name, f);
        }
    }
    
    @Override
    protected void tick() {
        //System.out.print(".");
    }

    @Override
    protected void tickSecond() {
        System.out.println("\nPosition= " + this.position + "\tVelocity= " + this.velocity + "\tTotal Force= " + this.getTotalForce() );
    }
    
    public void setForce(NamedVector force) {
        forces.put(force.name, force);
    }    
    
    public void removeForce(NamedVector force) {
        if(!forces.containsKey(force.name)) return;
        
        forces.remove(force.name);
    }    
    
}
