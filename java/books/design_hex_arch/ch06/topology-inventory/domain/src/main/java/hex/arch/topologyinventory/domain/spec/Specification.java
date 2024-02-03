package hex.arch.topologyinventory.domain.spec;

public interface Specification<T> {

    boolean isSatisfiedBy(T t);

    void check(T t);

}
