package config;

/**
 * Created by Dan Figueras on 06/03/2016.
 * Belongs to project routecalculator.
 * All rights reserved.
 */
public abstract class configItems {
    public static String environment="Loc";

    public static boolean isLoc() {
        if (environment == "Loc") {
            return true;
        }
        return false;
    }
}
