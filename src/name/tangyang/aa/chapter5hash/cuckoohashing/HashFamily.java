package name.tangyang.aa.chapter5hash.cuckoohashing;

public interface HashFamily<AnyType> {
    int hash(AnyType x, int which);
    int getNumberOfFunctions();
    void generateNewFunctions();
}
