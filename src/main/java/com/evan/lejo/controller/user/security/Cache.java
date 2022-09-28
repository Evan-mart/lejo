package com.evan.lejo.controller.user.security;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
@Service( "UserCache" )
public class Cache {
    /**
     * <code>Map[Type of class, Map[Resource id, entrepreneur id]]</code>
     */
    private final Map< Class, Map< Long, List< Long > > > cache = new HashMap<>();


    public < T > boolean isContains( Class< T > type, Long resourceId, Long userId ) {
        return cache.containsKey( type )
                && cache.get( type ).containsKey( resourceId )
                && cache.get( type ).get( resourceId ).contains( userId );
    }


    public synchronized < T > void write( Class< T > type, Long resourceId, Long userId ) {
        if ( !cache.containsKey( type ) ) {
            cache.put( type, new HashMap<>() );
        }

        if ( !cache.get( type ).containsKey( resourceId ) ) {
            cache.get( type ).put( resourceId, new ArrayList<>() );
        }

        cache.get( type ).get( resourceId ).add( userId );
    }
}
