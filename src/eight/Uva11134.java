package eight;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: eight
 * author: Chris
 * date: 2018/5/24 17:28
 */
public class Uva11134 {
//    static class Rook {
//        int lx;
//        int ly;
//        int rx;
//        int ry;
//        int id;
//        int selectX;
//        int selectY;
//        boolean used = false;
//
//        Rook(int x1, int y1, int x2, int y2, int id) {
//            lx = x1;
//            ly = y1;
//            rx = x2;
//            ry = y2;
//            this.id = id;
//        }
//
//        boolean place(int x, int y) {
//            return !used && x >= lx && x <= rx && y >= ly && y <= ry;
//        }
//
//        @Override
//        public String toString() {
////            return lx + " " + ly + " " + rx + " " + ry;
//            return selectX + " " + selectY;
//        }
//    }
//
//    static boolean build(Rook[] rooks, int n) {
//        Arrays.sort(rooks, (o1, o2) -> {
//            if (o1.lx == o2.lx) {
//                return o1.rx - o2.rx;
//            }
//            return o1.lx - o2.lx;
//        });
//        for (int i = 1; i <= n; i++) {
//            if (rooks[i].lx != i || rooks[i].lx > rooks[i].rx) {
//                return false;
//            }
//            rooks[i].selectX = i;
//            for (int j = i + 1; j <= n; j++) {
//                if (rooks[j].lx == i) {
//                    rooks[j].lx++;
//                } else {
//                    break;
//                }
//            }
//            Arrays.sort(rooks, (o1, o2) -> {
//                if (o1.lx == o2.lx) {
//                    return o1.rx - o2.rx;
//                }
//                return o1.lx - o2.lx;
//            });
//
//        }
//        Arrays.sort(rooks, (o1, o2) -> {
//            if (o1.ly == o2.ly) {
//                return o1.ry - o2.ry;
//            }
//            return o1.ly - o2.ly;
//        });
//        for (int i = 1; i <= n; i++) {
//            if (rooks[i].ly != i || rooks[i].ly > rooks[i].ry) {
//                return false;
//            }
//            rooks[i].selectY = i;
//            for (int j = i + 1; j <= n; j++) {
//                if (rooks[j].ly == i) {
//                    rooks[j].ly++;
//                } else {
//                    break;
//                }
//            }
//            Arrays.sort(rooks, (o1, o2) -> {
//                if (o1.ly == o2.ly) {
//                    return o1.ry - o2.ry;
//                }
//                return o1.ly - o2.ly;
//            });
//        }
//
//        return true;
//    }
//
//    public static void main(String args[]) {
//        Scanner sc = new Scanner(System.in);
//
//        while (true) {
//            int n = Integer.parseInt(sc.nextLine());
//            if (n == 0) {
//                break;
//            }
//            Rook[] rooks = new Rook[n + 1];
//            rooks[0] = new Rook(-1, -1, -1, -1, -1);
//            for (int i = 1; i <= n; i++) {
//                rooks[i] = new Rook(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), i);
//                sc.nextLine();
//            }
//
//            if (!build(rooks, n)) {
//                System.out.println("IMPOSSIBLE");
//            } else {
//                Arrays.sort(rooks, Comparator.comparingInt(o -> o.id));
//                for (int i = 1; i <= n; i++) {
//                    System.out.println(rooks[i]);
//                }
//            }
//        }
//    }
}
