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
package com.physikk.mechanic.effects;

import com.physikk.base.Effect;
import com.physikk.base.NamedVector;
import com.physikk.base.PhysicObject;
import com.physikk.base.Utils;
import com.physikk.base.Vector;

/**
 * Describes the gravitation by Newton's law of universal gravitation.
 * @author Sleepersword
 */
public class GravitationEffect extends Effect<PhysicObject>
{
    public GravitationEffect(PhysicObject... initialObjects) {
        super("Gravitation", true);
        
        for(PhysicObject o : initialObjects) {
            this.attachedObjects.put(o.getName(), o);
        }
    }

    @Override
    public void update() {
        //Calculate force on each object separatly
        for( PhysicObject mainObject : this.attachedObjects.values() ) {
            Vector result = Vector.NULLVECTOR();
            
            //Iterate over all masses
            for( PhysicObject currentObject : this.attachedObjects.values() ) {
                if(currentObject == mainObject) continue;
                //Ri - R1
                Vector line = currentObject.getPosition().subtract(mainObject.getPosition());
                //|Ri - R1|
                double norm = line.getNorm();
                // Mi * (Ri - R1/|Ri - R1|^3)
                line = line.scale( currentObject.getMass() / Math.pow(norm, 3) );
                
                result = result.sum( line );
            }
            
            //G * M1
            double mainFactor = Utils.GRAVITATIONAL_CONSTANT * mainObject.getMass();
            mainObject.setForce( new NamedVector("GravitationForce", result.scale(mainFactor) ) );
        }
        
    }

    @Override
    protected void updateSecond() {
        System.out.println("[FX:" + name + "] Effected Objects=" + attachedObjects.values().size() );
    }
    
}
