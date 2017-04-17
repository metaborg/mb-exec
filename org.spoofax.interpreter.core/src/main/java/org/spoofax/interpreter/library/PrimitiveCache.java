package org.spoofax.interpreter.library;

public class PrimitiveCache {

    private final int levels;
    private final int entries;

    private final String names[][];
    private final AbstractPrimitive primitives[][];

    public PrimitiveCache(int levels, int entries) {
        if(levels <= 0) {
            throw new IllegalArgumentException("Levels must be at least 1.");
        }
        if(entries <= 0) {
            throw new IllegalArgumentException("Entries must be at least 1.");
        }
        this.levels = levels;
        this.entries = entries;
        this.names = new String[levels][entries];
        this.primitives = new AbstractPrimitive[levels][entries];
    }

    /**
     * Get a primitive from the cache, if present.
     * 
     * @param The name of the primitive.
     * @return The primitive, or null if not found.
     */
    public AbstractPrimitive get(final String name) {
        final int entry = Integer.remainderUnsigned(name.hashCode(), entries);
        for(int level = 0; level < levels; level++) {
            final String entryName = names[level][entry];
            if(entryName == null) { // lower levels will be null as well
                return null;
            }
            if(name.equals(entryName)) {
                final AbstractPrimitive primitive = primitives[level][entry];
                if(level == 0) { // no need to put it on level 0
                    return primitive;
                }
                for(int l = 1; l <= level; l++) {
                    names[l][entry] = names[l - 1][entry];
                    primitives[l][entry] = primitives[l - 1][entry];
                }
                names[0][entry] = name;
                primitives[0][entry] = primitive;
                return primitive;
            }
        }
        return null;
    }

    /**
     * Add an entry to the cache.
     * 
     * This method does not check whether the entry is already there.
     *
     * @param The name of the primitive.
     * @param The primitive.
     */
    public void put(final String name, final AbstractPrimitive primitive) {
        final int entry = Integer.remainderUnsigned(name.hashCode(), entries);
        for(int level = 1; level < levels; level++) {
            names[level][entry] = names[level - 1][entry];
            primitives[level][entry] = primitives[level - 1][entry];
        }
        names[0][entry] = name;
        primitives[0][entry] = primitive;
    }

}