/**
 * IDbWcfService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package DbWcfServiceReference;

public interface IDbWcfService extends java.rmi.Remote {
    public DbWcfServiceReference.DatabaseDto getDatabase(java.lang.String dbName) throws java.rmi.RemoteException;
    public java.lang.String[] getDatabasesNames() throws java.rmi.RemoteException;
    public DbWcfServiceReference.TableDto getTable(java.lang.String dbName, java.lang.String tableName) throws java.rmi.RemoteException;
    public DbWcfServiceReference.TableDto getTableProjection(java.lang.String dbName, java.lang.String tableName, java.lang.String[] attributesNames) throws java.rmi.RemoteException;
}
