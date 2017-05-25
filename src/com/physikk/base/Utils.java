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
    
    public static double round(double value, int numberOfDigits) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(numberOfDigits,BigDecimal.ROUND_HALF_UP);
        
        return bd.doubleValue();
    }
}
