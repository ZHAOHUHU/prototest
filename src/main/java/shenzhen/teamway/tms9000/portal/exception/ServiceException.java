package shenzhen.teamway.tms9000.portal.exception;

public class ServiceException extends java.lang.RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int code;

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public ServiceException(int code, Throwable e) {
		super(String.valueOf(code), e);
		this.code = code;
	}

	public ServiceException(int code, String msg, Throwable e) {
		super(code + ":" + msg, e);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
