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
package com.physikk.test;

import com.physikk.base.*;
import com.physikk.mechanic.*;
import com.physikk.mechanic.effects.GravitationEffect;

/**
 *
 * @author Sleepersword
 */
public class GeneralTest 
{
    
    public static void main(String[] args) {
        SystemManager manager = new SystemManager();
        
        MassPoint[] mps = new MassPoint[2];
//        for(Integer i = 0; i < mps.length; i++) {
//            mps[i] = new MassPoint(i.toString(), 10e3, Utils.randomVector1D(0, 10), Vector.NULLVECTOR() );
//            System.out.println(mps[i].toString());
//        }
        mps[0] = new MassPoint("mp1", 10e10, new Vector(0.0), new Vector(0.0) );
        mps[1] = new MassPoint("mp2", 10e3, new Vector(1.0), new Vector(0.0) );
        manager.addObjects(mps);
        manager.addEffect(new GravitationEffect(), true);
        
        manager.start();
    }
    
}
