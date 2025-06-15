package com.hzbhd.util;

import android.os.IBinder;

import java.lang.reflect.Method;

public class ServiceManagerReflection {
    public static IBinder getService(String serviceName) {
        IBinder binder = null;
        try {
            // Get the ServiceManager class
            Class<?> serviceManagerClass = Class.forName("android.os.ServiceManager");

            // Get the getService method
            Method getServiceMethod = serviceManagerClass.getMethod("getService", String.class);

            // Invoke the getService method
            Object result = getServiceMethod.invoke(null, serviceName); // invoke on null for static method

            if (result != null) {
                binder = (IBinder) result;
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: ServiceManager class not found. " + e.getMessage());
            // Log.e("ServiceHelper", "ServiceManager class not found.", e); // Use Android Log for actual app
        } catch (NoSuchMethodException e) {
            System.err.println("Error: getService method not found. " + e.getMessage());
            // Log.e("ServiceHelper", "getService method not found.", e);
        } catch (Exception e) { // Catch broader exceptions for reflection issues
            System.err.println("Error invoking getService method. " + e.getMessage());
            // Log.e("ServiceHelper", "Error invoking getService method.", e);
        }
        return binder;
    }

    public static void addService(String serviceName, IBinder service) {
        try {
            // Get the ServiceManager class
            Class<?> serviceManagerClass = Class.forName("android.os.ServiceManager");

            // Get the addService method
            Method addServiceMethod = serviceManagerClass.getMethod("addService", String.class, IBinder.class);

            // Invoke the addService method to register the service
            addServiceMethod.invoke(null, serviceName, service); // invoke on null for static method
        } catch (ClassNotFoundException e) {
            System.err.println("Error: ServiceManager class not found. " + e.getMessage());
        } catch (NoSuchMethodException e) {
            System.err.println("Error: addService method not found. " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error invoking addService method. " + e.getMessage());
        }
    }
}
