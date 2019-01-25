
package ascelion.rest.bridge.client;

import javax.ws.rs.MatrixParam;

final class INTMatrixParam extends INTParamBase<MatrixParam>
{

	INTMatrixParam( MatrixParam annotation, RestParam param )
	{
		super( annotation, param );
	}

	@Override
	void visitAnnotationValue( RestRequestContextImpl rc, Object v )
	{
		rc.matrix( this.annotation.value(), this.param.cvt.toString( v ) );
	}
}
