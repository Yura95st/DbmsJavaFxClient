/**
 * DbWcfServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package DbWcfServiceReference;

public class DbWcfServiceLocator extends org.apache.axis.client.Service implements DbWcfServiceReference.DbWcfService {

    public DbWcfServiceLocator() {
    }


    public DbWcfServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DbWcfServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IDbWcfService
    private java.lang.String BasicHttpBinding_IDbWcfService_address = "http://localhost:51013/DbWcfService.svc";

    public java.lang.String getBasicHttpBinding_IDbWcfServiceAddress() {
        return BasicHttpBinding_IDbWcfService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_IDbWcfServiceWSDDServiceName = "BasicHttpBinding_IDbWcfService";

    public java.lang.String getBasicHttpBinding_IDbWcfServiceWSDDServiceName() {
        return BasicHttpBinding_IDbWcfServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_IDbWcfServiceWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_IDbWcfServiceWSDDServiceName = name;
    }

    public DbWcfServiceReference.IDbWcfService getBasicHttpBinding_IDbWcfService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IDbWcfService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IDbWcfService(endpoint);
    }

    public DbWcfServiceReference.IDbWcfService getBasicHttpBinding_IDbWcfService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            DbWcfServiceReference.BasicHttpBinding_IDbWcfServiceStub _stub = new DbWcfServiceReference.BasicHttpBinding_IDbWcfServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IDbWcfServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IDbWcfServiceEndpointAddress(java.lang.String address) {
        BasicHttpBinding_IDbWcfService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (DbWcfServiceReference.IDbWcfService.class.isAssignableFrom(serviceEndpointInterface)) {
                DbWcfServiceReference.BasicHttpBinding_IDbWcfServiceStub _stub = new DbWcfServiceReference.BasicHttpBinding_IDbWcfServiceStub(new java.net.URL(BasicHttpBinding_IDbWcfService_address), this);
                _stub.setPortName(getBasicHttpBinding_IDbWcfServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BasicHttpBinding_IDbWcfService".equals(inputPortName)) {
            return getBasicHttpBinding_IDbWcfService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "DbWcfService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_IDbWcfService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_IDbWcfService".equals(portName)) {
            setBasicHttpBinding_IDbWcfServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
