package com.tcc.park.api.exception;

/**
 * 类 <code>DAOException</code> 异常处理类
 *
 */
public class DAOException extends BaseException {


   private static final long serialVersionUID = 2067544392639375267L;

   public DAOException() {
      super();     
   }

   /**
    * @param message
    */
   public DAOException(String message) {
      super(message);
   }

   /**
    * @param message 错误信息
    * @param cause
    */
   public DAOException(String message, Throwable cause) {
      super(message, cause);
   }

   /**
    * @param cause 
    */
   public DAOException(Throwable cause) {
      super(cause);
   }
}
