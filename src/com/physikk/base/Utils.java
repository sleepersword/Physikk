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

import java.math.BigDecimal;

/**
 *
 * @author sleepersword
 */
public final class Utils 
{
    // Override default constructor
    private Utils() {}
    
    /**
     * Defines how often the tick() method will be called per second.
     */
    public static final int TICKS_PER_SECOND = 60;
    
    /**
     * Defines how much time elapses between two tick() method calls.
     */
    public static final double TIME_PER_TICK = 1.0 / TICKS_PER_SECOND;
    
    /**
     * Defines when a double is equal to another one.
     */
    public static final double DOUBLE_PRECISION = 0.0001;
    
    
    public static double round(double value, int numberOfDigits) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(numberOfDigits,BigDecimal.ROUND_HALF_UP);
        
        return bd.doubleValue();
    }
    
    public static boolean equalDoubles(double a, double b){
        return Math.abs(a - b) < DOUBLE_PRECISION;
    }
}
