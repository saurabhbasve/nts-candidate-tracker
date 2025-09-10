public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public void method1() {
        logger.info("Entering method1");
        // method1 implementation
        logger.info("Exiting method1");
    }

    public void method2(String param) {
        logger.info("Entering method2 with param: {}", param);
        // method2 implementation
        logger.info("Exiting method2");
    }

    public int method3(int num) {
        logger.info("Entering method3 with num: {}", num);
        int result = num * 2;
        logger.info("Exiting method3 with result: {}", result);
        return result;
    }
}