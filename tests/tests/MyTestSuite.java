package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ tree.general.tests.NodeTest.class,
    tree.general.tests.TreeTest.class, tree.binary.tests.NodeTest.class,
    tree.binary.tests.TreeTest.class, FloorPlanReaderTest.class,
})
public class MyTestSuite {
}
