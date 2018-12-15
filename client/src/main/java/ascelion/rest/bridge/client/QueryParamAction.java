
package ascelion.rest.bridge.client;

import javax.ws.rs.QueryParam;

class QueryParamAction
extends AnnotationAction<QueryParam>
{

	QueryParamAction( QueryParam a, ActionParam p )
	{
		super( p, a );
	}

	@Override
	public void execute( RestRequest cx )
	{
		visitCollection( cx );
	}

	@Override
	void visitElement( RestRequest cx, Object v )
	{
		cx.target = cx.target.queryParam( this.annotation.value(), this.param.converter.apply( v ) );
	}
}
