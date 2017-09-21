package org.wayne.misc;

public class Strings {
	
	static void p(String f, Object ... o) {
		System.out.printf(f, o);
	}
	
    /**
     * rolling hash is used for rabin karp search of long text,
     * with O(n) on avg, unless hash function is bad and hits all time.
     */
    static class RollingHash {
        int sizeWindow;
        int prvval;
        int curval;
        int prime;
        char prvchar;
        boolean isvalid = false;
        public RollingHash(int sizeStr) {
            sizeWindow = sizeStr;
            prvval = 0;
            curval = 0;
            prime = 31;
            prvchar = '\0';
        }
        public int computeHash(String strWindow) {
            if(strWindow.length() != sizeWindow) {
                return -1;
            }
            if(!isvalid) {
                curval = 0;
                int base = prime;
                for(int i = sizeWindow - 1; i >= 0; i--) {
                    char c = strWindow.charAt(i);
                    curval = curval * base + c * prime;
                    base = base * prime;
                    curval = curval % 0xffffffff;
                    base = base % 0xffffffff;
                }
                prvchar = strWindow.charAt(0);
                isvalid = true;
            } else {
                int base = pow(prime, sizeWindow);
                int valDelta = curval - base * prvchar;
                int c = strWindow.charAt(sizeWindow-1);
                curval = prime * (valDelta + c);
                prvchar = strWindow.charAt(0);
            }
            return curval;
        }
        int pow(int base, int pow) {
            int v = 1;
            if(pow <= 0) {
                return 1;
            }
            for(int i = 1; i <= pow; i++) {
                v = v * base;
                v = v % 0xffffffff;
            }
            return v;
        }
    }
    
    static class KMP {
        int [] table;
        char [] a;
        /*
         * 00 01 02 03 04 05
         *  a  b  a  b  a  a
         * -1 00 00 01 02 03
         */
        private void constructTable() {
            table[0] = -1;
            table[1] = 0;
            int pos = 2;
            int cnd = 0;
            int sz = table.length;
            while(pos < sz) {
                if(a[pos-1] == a[cnd]) {
                    table[pos++] = ++cnd;
                }
                else if(cnd > 0) {
                    cnd = table[cnd];
                }
                else {
                    table[pos] = 0;
                    pos++;
                }
            }
            for(int i = 0; i < sz; i++) {
                p("%02d ", i);
            }
            p("\n");
            for(int i = 0; i < sz; i++) {
                p("%2s ", a[i]);
            }
            p("\n");
            for(int i = 0; i < sz; i++) {
                p("%02d ", table[i]);
            }
            p("\n");
        }
        public KMP(String pat) {
            a = pat.toCharArray();
            table = new int[a.length];
            constructTable();
        }
        /*
         * 00 01 02 03 04 05
         *  a  b  a  b  a  a
         * -1 00 00 01 02 03
         */
        public int match(String str) {
            char [] astr = str.toCharArray();
            int szs = astr.length;
            int szp = a.length;
            {
                p("\n");
                for(int i = 0; i < szp; i++) {
                    p("%02d ", i);    // print idx
                }
                p("\n");
                for(int i = 0; i < szp; i++) {
                    p("%2s ", a[i]); // print pat
                }
                p("\n");
                for(int i = 0; i < szs; i++) {
                    p("%02d ", i);  // print idx
                }
                p("\n");
                for(int i = 0; i < szs; i++) {
                    p("%2s ", astr[i]); // print str
                }
                p("\n");
            }

            int m = 0;
            int i = 0;
            while((m + i) < szs) {
                if(a[i] == astr[m+i]) {
                    if(i == (szp-1)) {
                        return m;
                    }
                    i++;
                }
                else if(table[i] > -1) {
                    m = m + i - table[i];
                    i = table[i];
                    // print pattern shifted to m+i
                    for(int k = 0; k < m; k++) {
                        p("   ");
                    }
                    for(int k = 0; k < szp; k++) {
                        p("%2s ", a[k]); // print pat
                    }
                    p("\n");
                }
                else {
                    m++;
                    i = 0;
                    // print pattern shifted to m+i
                    for(int k = 0; k < m; k++) {
                        p("   ");
                    }
                    for(int k = 0; k < szp; k++) {
                        p("%2s ", a[k]); // print pat
                    }
                    p("\n");
                }
            }
            return -1;
        } 
    }
}
