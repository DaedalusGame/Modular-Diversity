package modulardiversity.util;

public interface ICraftingResourceHolder<V extends IResourceToken> {
    boolean consume(V token, boolean doConsume);

    boolean generate(V token, boolean doGenerate);

    String getInputProblem(V token);

    String getOutputProblem(V token);
}
