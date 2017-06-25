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

import java.util.Objects;

/**
 * Describes a vector object with an additional string for identification.
 * @author Sleepersword
 */
public class NamedVector extends Vector
{    
    public final String name;
    
    public NamedVector(String name, double x, double y, double z) {
        super(x,y,z);
        this.name = name;
    }
    
    public NamedVector(String name, Vector v) {
        this(name, v.x, v.y, v.z);
    }
    
    public NamedVector(String name, double x, double y) {
        this(name, x, y, 0);
    }
    
    public NamedVector(String name, double x) {
        this(name, x, 0, 0);
    }
    
    public NamedVector(String name) {
        this(name, 0, 0, 0);
    }
    
    @Override
    public String toString() {
        return name + ":" + super.toString();
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof NamedVector) {
            NamedVector v = (NamedVector)o;
            
            boolean check_X = Utils.equalDoubles(this.x, v.x);
            boolean check_Y = Utils.equalDoubles(this.y, v.y);
            boolean check_Z = Utils.equalDoubles(this.z, v.z);
            
            return check_X && check_Y && check_Z && this.name.equals(v.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 41 * hash + Objects.hashCode(this.name);
        return hash;
    }
}
