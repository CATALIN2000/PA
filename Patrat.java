import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

class Solver
{

    public static int n, i;
    public static long k;
    public static long[] arr = new long[50001];

    /**
     * citeste numerele si le sorteaza
     *
     * @throws FileNotFoundException
     */
    public static void read() throws FileNotFoundException
    {
        Scanner fin = new Scanner(new File("patrat.in"));
        n = fin.nextInt();
        k = fin.nextLong();
        for (i = 0; i < n; i++)
            arr[i] = fin.nextLong();
        fin.close();
    }

    /**
     * @param mSum suma de referinta
     * @return numarul de sume mai mici ca mSum
     */
    public static long count(long mSum)
    {
        long cnt = 0;
        int i = 0, j = n - 1;

        while (i < n && mSum > arr[i])
        {
            cnt++;
            while (j >= 0 && mSum < arr[i] + arr[j])
                j--;
            cnt += j;
            i++;
        }
        return cnt;
    }

    /**
     *
     * @return a k suma
     */
    public static long compute()
    {
        long leftSum = arr[0] * 2;
        long rightSum = arr[n - 1] * 2;
        long mSum = 0, kSum = 0;

        while (leftSum <= rightSum)
        {
            mSum = (leftSum + rightSum) / 2;
            if (k <= count(mSum))
            {
                kSum = mSum;
                rightSum = mSum - 1;
            } else
                leftSum = mSum + 1;
        }
        return kSum;
    }

    static void write(long kSum) throws FileNotFoundException
    {
        PrintWriter fout = new PrintWriter("patrat.out");
        fout.println(kSum);
        fout.close();
    }
}

public class Patrat
{

    public static void main(String[] args) throws FileNotFoundException
    {
        Solver.read();
        Arrays.sort(Solver.arr, 0, Solver.n);
        long kSum = Solver.compute();
        Solver.write(kSum);
    }
}
