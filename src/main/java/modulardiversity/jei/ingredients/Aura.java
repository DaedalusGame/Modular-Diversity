package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Aura implements IFakeIngredient {
    private float visMin, visMax;
    private float fluxMin, fluxMax;
    private float vis;
    private float flux;

    public Aura(float visMin, float visMax, float fluxMin, float fluxMax, float vis, float flux) {
        this.visMin = visMin;
        this.visMax = visMax;
        this.fluxMin = fluxMin;
        this.fluxMax = fluxMax;
        this.vis = vis;
        this.flux = flux;
    }

    public float getVisMin() {
        return visMin;
    }

    public float getVisMax() {
        return visMax;
    }

    public float getFluxMin() {
        return fluxMin;
    }

    public float getFluxMax() {
        return fluxMax;
    }

    public float getVis() {
        return vis;
    }

    public float getFlux() {
        return flux;
    }

    @Override
    public String getDisplayName() {
        return "Aura";
    }

    @Override
    public String getUniqueID() {
        return "vis";
    }
}
