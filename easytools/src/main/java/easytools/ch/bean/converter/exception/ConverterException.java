package easytools.ch.bean.converter.exception;

/**
 * 
 * @author CH
 *
 */
public class ConverterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6750619190206488221L;

	public ConverterException() {
		super();
	}

	public ConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConverterException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConverterException(String message) {
		super(message);
	}

	public ConverterException(Throwable cause) {
		super(cause);
	}

	
	
}
