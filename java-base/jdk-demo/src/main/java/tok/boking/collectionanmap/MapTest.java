package tok.boking.collectionanmap;

import tok.boking.enumdemo.TestEnum;

@SuppressWarnings("warning")
public class MapTest {
    /**
     * empty
     */
    public void get() {

    }

    public void get(TestEnum testEnum) {

    }

    public String get(String s) {
        return s;
    }

    public boolean get(Boolean b, String s) {
        return b;
    }

    public static void main(String[] args) {
        MapTest mapTest = new MapTest();
        mapTest.get(TestEnum.A);
        mapTest.get(TestEnum.B);
        mapTest.get(TestEnum.C);
    }
}
