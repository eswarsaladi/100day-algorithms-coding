import java.util.Scanner;

public class UnionFind {
    private int[] id;
    private int count;

    public UnionFind(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    // returns true if they are in the same component
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Quick find :
     * find takes 1 array access
     * union takes n+3 to 2n+1 array access
     * 
     * in worst case where there is only one component
     * it takes (n+3)(n-1) ~ n^2 array access
     * 
     * Thus it is quadratic
     */
    public int quickFind(int p) {
        return id[p];
    }

    public void quickFindUnion(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        // if both belong to the same component nothing to do
        if (pId == qId)
            return;

        // convert every instance that has pId to qId
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId)
                id[i] = qId;
        }
        count--;
    }

    /**
     * Quick Union:
     * best case:
     * find needs one array access
     * find takes 1+ depth of the tree
     * union or connected = 2 * find
     * 
     * quadratic
     */
    public int quickUnionFind(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

    public void quickUnion(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        id[pRoot] = qRoot;

        count--;
    }

    public int find(int p) {
        return quickFind(p);
    }

    public void union(int p, int q) {
        quickFindUnion(p, q);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        UnionFind uf = new UnionFind(N);
        while (scan.hasNext()) {
            int p = scan.nextInt();
            int q = scan.nextInt();
            if (uf.isConnected(p, q))
                continue; // Ignore if connected.
            uf.union(p, q); // Combine components
            System.out.println(p + " " + q); // and print connection.
        }
        System.out.println(uf.count() + " components");
        scan.close();
    }
}