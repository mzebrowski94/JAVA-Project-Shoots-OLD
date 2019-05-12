package game.factories;

import game.gameobjects.map.MapMatrix;
import org.springframework.stereotype.Component;

/**
 * @author Mateusz Żebrowski
 */
@Component
public class MapMatrixFactory {

    //TODO: TUTAJ WCZYTYWANIE PROPERTIES

    private MapMatrixFactory(){
    }

    public MapMatrix getMapMatrix(){
        return null;
    }

    public MapMatrix generateMapMatrix(){
        return null;
    }
}
