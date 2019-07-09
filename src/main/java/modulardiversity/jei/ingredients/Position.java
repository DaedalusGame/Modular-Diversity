package modulardiversity.jei.ingredients;

import modulardiversity.components.requirements.AnchorType;
import modulardiversity.jei.IFakeIngredient;

public class Position implements IFakeIngredient {
    float xMin, xMax;
    float yMin, yMax;
    float zMin, zMax;
    float distanceMin, distanceMax;
    private AnchorType anchor;

    public Position(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax, float distanceMin, float distanceMax, AnchorType anchor) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.zMin = zMin;
        this.zMax = zMax;
        this.distanceMin = distanceMin;
        this.distanceMax = distanceMax;
        this.anchor = anchor;
    }

    public float getxMin() {
        return xMin;
    }

    public float getxMax() {
        return xMax;
    }

    public float getyMin() {
        return yMin;
    }

    public float getyMax() {
        return yMax;
    }

    public float getzMin() {
        return zMin;
    }

    public float getzMax() {
        return zMax;
    }

    public float getDistanceMin() {
        return distanceMin;
    }

    public float getDistanceMax() {
        return distanceMax;
    }

    public AnchorType getAnchor() {
        return anchor;
    }

    @Override
    public String getDisplayName() {
        return "Position";
    }

    @Override
    public String getUniqueID() {
        return "position";
    }
}
