package modulardiversity.util;

public interface ICraftingResourceHolder<V extends IResourceToken> {
    boolean consume(V token);

    boolean generate(V token);
}
