package name.tangyang.aa.chapter2aa;

public class TimeAnalysis {
    public static void main(String[] args) {
        long sum = 0;
        int n = 1000;
        int i,j,k;
        long time0 = System.currentTimeMillis();
        // no  n=400 n=500   n=1000
        // 1   0     0       0
        for (i = 0; i < n; i++)
            sum++;
        long time1 = System.currentTimeMillis();
        // 2   2     3       5
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                sum++;
        long time2 = System.currentTimeMillis();
        // 3   8     18      54
        for (i = 0; i < n; i++)
            for (j = 0; j < n * n; j++)
                sum++;
        long time3 = System.currentTimeMillis();
        // 4   0     0      0
        for (i = 0; i < n; i++)
            for (j = 0; j < i; j++)
                sum++;
        long time4 = System.currentTimeMillis();
        // 5   19746  60175 1907133
        for (i = 0; i < n; i++)
            for (j = 0; j < i * i; j++)
                for (k = 0; k < j; k++)
                    sum++;
        long time5 = System.currentTimeMillis();
        // 6   124     273  3351
        for (i = 0; i < n; i++)
            for (j = 0; j < i * i; j++)
                if (j % i == 0)
                    for (k = 0; k < j; k++)
                        sum++;
        long time6 = System.currentTimeMillis();
        System.out.println("1time-" + (time1 - time0) +
                " 2time-" + (time2 - time1) +
                " 3time-" + (time3 - time2) +
                " 4time-" + (time4 - time3) +
                " 5time-" + (time5 - time4) +
                " 6time-" + (time6 - time5) );
    }
}
