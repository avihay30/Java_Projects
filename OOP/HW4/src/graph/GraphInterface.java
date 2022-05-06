package graph;

import java.util.Collection;

// interface that whom implements it, need to define a method that
// enables to return all neighbours of given v
public interface GraphInterface<V> {
    public Collection<V> neighbours(V v);
}
