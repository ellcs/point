package com.ellcs.point.graphics.nodes;

import java.util.Set;

/**
 * Created by alex on 12/26/15.
 */
public class AbstractNode implements Node {

    Set<Node> children;

    @Override
    public boolean addChild(Node node) {
        return children.add(node);
    }

    @Override
    public void tick(double timeDelta) {
        for (Node child : children) {
            child.tick(timeDelta);
        }
    }
}
