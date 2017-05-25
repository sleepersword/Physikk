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

import java.util.Locale;

/**
 * Desribes a 3-tupel of doubles.
 * @author sleepersword
 */
public final class Vector 
{
    /**
     * Defines when a double is equal to another one.
     */
    private static final double DOUBLE_PRECISION = 0.0001;
    
    /**
     * The vector (0,0,0)
     * @return 
     */
    public static final Vector NULLVECTOR() { return new Vector(0,0,0); }
    /**
     * The vector (1,1,1)
     * @return 
     */
    public static final Vector UNITVECTOR() { return new Vector(1,1,1); }
    /**
     * The vector (1,0,0)
     * @return 
     */
    public static final Vector UNITVECTOR_X() { return new Vector(1,0,0); }
    /**
     * The vector (0,1,0)
     * @return 
     */
    public static final Vector UNITVECTOR_Y() { return new Vector(0,1,0); }
    /**
     * The vector (0,0,1)
     * @return 
     */
    public static final Vector UNITVECTOR_Z() { return new Vector(0,0,1); }
    
    /**
     * The X coordinate.
     */
    public double X;
    /**
     * The Y coordinate.
     */
    public double Y;
    /**
     * The Z coordinate.
     */
    public double Z;
    
    // Constructors
    
    /**
     * Initializes the vector with the given 3 coordinates.
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param z The Z coordinate.
     */
    public Vector(double x, double y, double z) {
        X = x;
        Y = y;
        Z = z;
    }
    
    /**
     * Initializes the vector with the given 2 coordinates, the remaining is set to 0.
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    public Vector(double x, double y) {
        this(x, y, 0);
    }
    
    /**
     * Initializes the vector with the given coordinate, the remaining are set to 0.
     * @param x The X coordinate.
     */
    public Vector(double x) {
        this(x, 0, 0);
    }
    
    // Methods
    
    /**
     * Calculates the norm of this vector.
     * @return The norm of this vector.
     */
    public double getNorm() {
        double self_scalar = this.scalarMultiply(this);
        
        return Math.sqrt(self_scalar);
    }
    
    /**
     * Returns the normalized vector of this vector.
     * @return Returns the normalized vector of this vector. If this is the null vector, returns the null vector.
     */
    public Vector getNormalizedVector() {        
        if( this.equals(Vector.NULLVECTOR()) ) return Vector.NULLVECTOR();
        
        return this.scale(1/getNorm() );
    }
    
    // Overrides
    
    /**
     * Creates a String representation of this vector (x, y, z).
     * @return The String representation of this vector.
     */
    @Override
    public String toString() {
        return String.format(Locale.US, "(%.2f, %.2f, %.2f)", X, Y, Z);
    }
    
    /**
     * Checks if the given object equals this vector.
     * @param o The object.
     * @return True if both vectors have the same coordinates. False if o isn't a vector or isn't equal to this.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Vector) {
            Vector v = (Vector)o;
            
            boolean check_X = equalDoubles(this.X, v.X);
            boolean check_Y = equalDoubles(this.Y, v.Y);
            boolean check_Z = equalDoubles(this.Z, v.Z);
            
            return check_X && check_Y && check_Z;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.X) ^ (Double.doubleToLongBits(this.X) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.Y) ^ (Double.doubleToLongBits(this.Y) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.Z) ^ (Double.doubleToLongBits(this.Z) >>> 32));
        return hash;
    }
    
    /// Operations
   
    /**
     * Calculates the sum of two vectors.
     * @param v The right vector.
     * @return The sum of this + v2.
     */
    public Vector sum(Vector v) {
        return new Vector(this.X + v.X, this.Y + v.Y, this.Z + v.Z);
    }
    
    /**
     * Calculates the subtract of two vectors.
     * @param v The right vector.
     * @return The subtract of this - v.
     */
    public Vector subtract(Vector v) {
        return new Vector(this.X - v.X, this.Y - v.Y, this.Z - v.Z);
    }
    
    /**
     * Calculates the scalar product of two vectors
     * @param v The right vector.
     * @return The scalar product of this * v
     */
    public double scalarMultiply(Vector v) {
        return this.X*v.X + this.Y*v.Y + this.Z*v.Z;
    }
    
    /**
     * Calculates the vector product of two vectors.
     * @param v The right vector.
     * @return The vector product this x v
     */
    public Vector vectorMultiply(Vector v) {
        double x = this.Y*v.Z - this.Z*v.Y;
        double y = this.Z*v.X - this.X*v.Z;
        double z = this.X*v.Y - this.Y*v.X;
        
        return new Vector(x, y, z);
    }
    
    /**
     * Calculates the triple product of three vectors.
     * @param v1 The middle vector
     * @param v2 The right vector.
     * @return The triple product (this x v1) * v2
     */
    public double tripleMultiply(Vector v1, Vector v2) {
        return this.vectorMultiply(v1).scalarMultiply(v2);
    }
    
    /**
     * Calculates the vector scaled by the given factor.
     * @param s The scale factor.
     * @return The scaled vector s * this.
     */
    public Vector scale(double s) {
        return new Vector(s*this.X, s*this.Y, s*this.Z);
    }
    
    /**
     * Checks if the two vectors are parallel.
     * @param v The right vector.
     * @return True if this || v.
     */
    public boolean isParallel(Vector v) {
        double sigX = this.X / v.X;
        double sigY = this.Y / v.Y;
        double sigZ = this.Z / v.Z;
        
        return equalDoubles(sigX, sigY) && equalDoubles(sigX, sigZ) && equalDoubles(sigY, sigZ);
    }
    
    /// Private Help Methods
    
    private static boolean equalDoubles(double a, double b){
        return Math.abs(a - b) < DOUBLE_PRECISION;
    }
    
}
