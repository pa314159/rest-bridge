
package bridge;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith( Suite.class )
@SuiteClasses( {
	HelloSuite.class,
	BeanResourceSuite.class,
} )
public class WholeSuite
{
}
