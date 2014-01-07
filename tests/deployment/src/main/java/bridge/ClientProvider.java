
package bridge;

import java.net.URI;

public interface ClientProvider
{

	<T> T createClient( URI target, Class<T> cls );
}
