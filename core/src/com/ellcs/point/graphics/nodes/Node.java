package com.ellcs.point.graphics.nodes;

/**
 * Created by alex on 12/26/15.
 */
public interface Node {

    boolean addChild(Node node);

    void tick(double timeDelta);
}
