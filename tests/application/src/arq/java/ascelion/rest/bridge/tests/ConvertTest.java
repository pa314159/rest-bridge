
package ascelion.rest.bridge.tests;

import java.time.LocalDate;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import ascelion.rest.bridge.tests.api.API;
import ascelion.rest.bridge.tests.api.Convert;
import ascelion.rest.micro.tests.shared.LocalDateConverterProvider;

import static ascelion.rest.micro.tests.shared.LocalDateConverterProvider.DATE_FORMAT;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ConvertTest
extends AbstractTestCase<Convert>
{

	@Override
	public void setUp() throws Exception
	{
		TestClientProvider.getInstance()
			.getBuilder()
			.register( LocalDateConverterProvider.class );

		super.setUp();
	}

	@Test
	public void getByClient()
	{
		final String now = LocalDate.now().format( DATE_FORMAT );
		final WebTarget w = TestClientProvider.getInstance()
			.getBuilder()
			.build()
			.target( this.target )
			.path( API.BASE )
			.path( "convert" )
			.path( "formatDate" )
			.queryParam( "value", now );
		;

		final Response rsp = w.request().get();

		assertThat( rsp.getStatus(), is( Response.Status.OK.getStatusCode() ) );
		assertThat( rsp.readEntity( String.class ), is( now ) );
	}

	@Test
	public void get()
	{
		final LocalDate now = LocalDate.now();
		final String fmt = this.client.format( now );

		assertThat( fmt, is( now.format( DATE_FORMAT ) ) );
	}

	@Test
	public void post()
	{
		final LocalDate now = LocalDate.now();
		final String fmt = this.client.formatPost( now );

		assertThat( fmt, is( now.format( DATE_FORMAT ) ) );
	}

}
