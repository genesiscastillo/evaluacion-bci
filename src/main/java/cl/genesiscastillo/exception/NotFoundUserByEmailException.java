package cl.genesiscastillo.exception;

public class NotFoundUserByEmailException extends Exception	{

	private static final long serialVersionUID = 1L;
	
	public NotFoundUserByEmailException(String email) {
        super(String.format("Not Found User By Email %s",email));
    }
}
