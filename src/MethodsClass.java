public class MethodsClass {
    @Test(priority = 1)
    static void test1() {
        System.out.println("Test1, priority1");
    }

    @Test(priority = 2)
    static void test2() {
        System.out.println("Test2, priority2");
    }

    @Test(priority = 2)
    static void test3() {
        System.out.println("Test3, priority2");
    }

    @Test(priority = 3)
    static void test4() {
        System.out.println("Test4, priority3");
    }

    @BeforeSuite
    static void testBeforeSuite() {
        System.out.println("Before suite");
    }

//    @BeforeSuite
//    static void testBeforeSuiteDouble() {
//        System.out.println("BeforeSuite double");
//    }

    @AfterSuite
    static void testAfterSuite() {
        System.out.println("AfterSuite");
    }
}
