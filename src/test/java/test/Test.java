package test;

import com.project.Dao.HuaweiDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author suat.erdogan
 */
public class Test {

    HuaweiDao huaweiDao;

    public Test() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("test.Test.tearDownClass()");

    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("test.Test.tearDownClass()");
    }

    @Before
    public void setUp() {
        huaweiDao= new HuaweiDao();
        huaweiDao.callTask();
        System.err.println("test up");
    }

    @After
    public void tearDown() {
        huaweiDao= new HuaweiDao();
        huaweiDao.callTask();
        System.err.println("test down");
    }

}
