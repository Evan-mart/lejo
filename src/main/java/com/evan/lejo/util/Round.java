package com.evan.lejo.util;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
public class Round {

    public static double round( double toRound ) {
        String unityStr = String.valueOf( toRound );
        String decimals = unityStr.split( "\\." )[ 1 ];

        if ( decimals.length() > 2 ) {
            decimals = decimals.substring( 0, 1 );
        }

        if ( decimals.length() == 0 ) {
            return Double.parseDouble( unityStr.split( "\\." )[ 0 ] );
        }

        return Double.parseDouble( unityStr.split( "\\." )[ 0 ] + "." + decimals );
    }


    public static float round( float toRound ) {
        String unityStr = String.valueOf( toRound );
        String decimals = unityStr.split( "\\." )[ 1 ];

        if ( decimals.length() > 2 ) {
            decimals = decimals.substring( 0, 1 );
        }

        if ( decimals.length() == 0 ) {
            return Float.parseFloat( unityStr.split( "\\." )[ 0 ] );
        }

        return Float.parseFloat( unityStr.split( "\\." )[ 0 ] + "." + decimals );
    }
}
