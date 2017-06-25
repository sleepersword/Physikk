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

/**
 * Describes a basic activatable object.
 * @author Sleepersword
 */
public abstract class Activatable 
{
    protected boolean active; 
    
    /**
     * Returns true if this object is active.
     * @return Whether is active.
     */
    public final boolean isActive() {
        return active;
    }
       
    /**
     * Activates this object.
     */
    public final void activate() {
        active = true;
    }
    
    /**
     * Deactivates this object.
     */
    public final void deactivate() {
        active = false;
    }
}
