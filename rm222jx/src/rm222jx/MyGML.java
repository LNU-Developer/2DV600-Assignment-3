package rm222jx;

import graphs.DirectedGraph;
import graphs.GML;

public class MyGML<E> extends GML<E> {
    public MyGML(DirectedGraph<E> dg) {
        super(dg);
    }

    /**
     * The GML mark-up string constructor.
     */
    public String toGML() {
        return "test";
    }
}
