package com.tcc.park.api.exception;


public class ServiceException extends DAOException {
	
   private static final long serialVersionUID = -73436737258614584L;

   public ServiceException() {
      super();
   }

   /**
    * @param s	error message
    */
   public ServiceException(String s) {
      super(s);
   }

   /**
    * @param s  error message
    * @param e	exception class
    */
   public ServiceException(String s, Throwable e) {
      super(s, e);
   }

   /**
    * @param e exception
    */
   public ServiceException(Throwable e) {
      super(e);
   }
}
