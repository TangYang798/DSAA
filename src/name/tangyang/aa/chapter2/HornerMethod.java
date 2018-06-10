package name.tangyang.aa.chapter2;

import java.util.HashMap;
import java.util.Map;

/**
 * horner method
 * 霍纳法则求解多项式，似乎没毛病
 */
public class HornerMethod {
    public static void main(String[] args) {
        String pn = "5x^5 + 4x^4 + x^3 + x + 4";
        pn = "3";
        System.out.println(getPolynomialSum(pn, 2));
    }

    public  static long getPolynomialSum(String pn, int x) {
        if (!pn.contains("x")) return Integer.parseInt(pn);
        pn = pn.replaceAll(" ","");
        String[] singles = pn.split("\\+");
        for (int i=0; i<singles.length; i++) {
            if (singles[i].startsWith("x")) {
                singles[i] = 1 + singles[i];
            }
            if (singles[i].endsWith("x")) {
                singles[i] += "^1";
            }
        }
        int maxExp = Integer.parseInt(singles[0].split("\\^")[1]);

        int[] prefix = new int[maxExp + 1];

        Map<Integer, Integer> prefixMap = new HashMap();
        for (int i=singles.length - 1; i>-1; i--) {
            if (singles[i].contains("^")) {
                prefixMap.put(Integer.parseInt("0" + singles[i].split("\\^")[1]),
                        Integer.parseInt("0" + singles[i].split("x")[0]));
                prefix[Integer.parseInt("0" + singles[i].split("\\^")[1])] =
                        Integer.parseInt("0" + singles[i].split("x")[0]);
            } else {
                prefixMap.put(0, Integer.parseInt("0" + singles[i].split("x")[0]));
                prefix[0] = Integer.parseInt("0" + singles[i].split("x")[0]);
            }
        }

        long poly = 0;
        long poly1 = 0;
        for (int i=maxExp; i>=0; i--) {
            poly = poly * x + prefixMap.getOrDefault(i, 0);
            poly1 = poly1 * x + prefix[i];
        }

        return poly1;
    }
}
