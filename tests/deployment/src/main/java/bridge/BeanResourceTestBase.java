
package bridge;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import ascelion.rest.bridge.web.BeanInfo;
import ascelion.rest.bridge.web.BeanResource;
import ascelion.rest.bridge.web.RestApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class BeanResourceTestBase<P extends ClientProvider>
extends AbstractTestCase<BeanResource, P>
{

	static private void assertValid( final BeanInfo ent )
	{
		assertNotNull( ent );

		assertEquals( "path1", ent.getPathParam1() );
		assertEquals( "path2", ent.getPathParam2() );
		assertEquals( "query1", ent.getQueryParam1() );
		assertEquals( "query2", ent.getQueryParam2() );
		assertEquals( "header1", ent.getHeaderParam1() );
		assertEquals( "header2", ent.getHeaderParam2() );
	}

	static private BeanInfo createBean()
	{
		final BeanInfo request = new BeanInfo( "path2", "header2", "query2" );

		request.setPathParam1( "path1" );
		request.setHeaderParam1( "header1" );
		request.setQueryParam1( "query1" );

		return request;
	}

	@Test
	public void get()
	{
		assertValid( this.client.get( createBean() ) );
	}

	@Test
	public void getByClient()
	{
		final WebTarget w = ClientBuilder.newClient()
			.target( this.target )
			.path( RestApplication.BASE )
			.path( "beans" )
			.path( "{path1}/{path2}" )
			.resolveTemplate( "path1", "path1" )
			.resolveTemplate( "path2", "path2" )
			.queryParam( "query1", "query1" )
			.queryParam( "query2", "query2" )

		;

		final Builder b = w.request( MediaType.APPLICATION_JSON );

		b.header( "header1", "header1" );
		b.header( "header2", "header2" );

		final Response resp = b.method( "GET" );

		assertNotNull( resp );

		assertEquals( Response.Status.OK.getStatusCode(), resp.getStatus() );

		assertValid( resp.readEntity( BeanInfo.class ) );
	}
}
